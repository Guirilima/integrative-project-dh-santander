package br.com.xrpg.service.serviceImpl;

import javax.transaction.Transactional;

import br.com.xrpg.converter.UsuarioConverter;
import br.com.xrpg.entity.Role;
import br.com.xrpg.enumber.TipoUsuarioEnum;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.repository.RoleRepository;
import br.com.xrpg.vo.*;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.service.UsuarioService;
import br.com.xrpg.utils.Utils;

import java.math.BigInteger;
import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder pEnconder;

    private final UsuarioConverter usuarioConverter;

    private final RoleRepository roleRepository;

    @Override
    //@Transactional(rollbackOn = ErrorSalvamento.class)
    @Transactional
    public UsuarioEntity criarNovoUsuarioCadastro(DadosUsuarioVO dadosNewUser) throws ErrorSalvamento {

        String errorFields = "";

        //VALIDAÇÔES
        if (dadosNewUser.getEmailUsur() == null)                errorFields = "Email do usuário";
        if (dadosNewUser.getSenhaUsur() == null)                errorFields = "Senha";
        if (dadosNewUser.getNomeUsur() == null)                 errorFields = "Nome";
        if (dadosNewUser.getCpfUsur() == null)                  errorFields = "CPF";
        if (dadosNewUser.getCidadeUsur() == null)               errorFields = "Cidade";
        if (dadosNewUser.getEstadoUsur() == null)               errorFields = "Estado";
        if (dadosNewUser.getNascimentoUsur() == null)           errorFields = "Estado";
        if (dadosNewUser.getTelefoneUsur() == null)             errorFields = "Estado";
        if (dadosNewUser.getGeneroUsur() == null)               errorFields = "Genero";

        if (!errorFields.isEmpty()) throw new ErrorSalvamento("Os seguintes campos obrigatorios estão nulos: " + errorFields);

        //VALIDAÇÂO DADOS SENSIVEIS
        if (!Utils.isEmailValido(dadosNewUser.getEmailUsur()))  throw new ErrorSalvamento("Email não é valido");
        if (Utils.validaCPF(dadosNewUser.getCpfUsur()) == null) throw new ErrorSalvamento( "CPF não é valido");
        if (dadosNewUser.getGeneroUsur().length() > 1) throw new ErrorSalvamento("O tamanho do gênero é maior que 1. Favor considere as alternativas : Masculino : 'M', Feminino : 'F' ou Outro : 'O' .");
        if (usuarioRepository.findByCpfPessoal(dadosNewUser.getCpfUsur()) != null) throw new ErrorSalvamento("Já existe esse CPF em nosso banco de registro.");
        if (usuarioRepository.findByEmailUsuario(dadosNewUser.getEmailUsur()) != null) throw new ErrorSalvamento("Esse email foi encontrado em nosso sistema. Por favor faça o login.");

        Set<Role> roles = new HashSet<>();
        roles.add( roleRepository.findById(dadosNewUser.getRole()).get() );

        //TODO | REUNINDO OS ID'S CRIADOS E SALVANDO O USER
        UsuarioEntity newUser = new UsuarioEntity().builder()
                .flagAtivo(new BigInteger("1")) //Usuario Ativo
                .dataUltimoLogin(new Date())
                .nomePessoal(dadosNewUser.getNomeUsur())
                .sobrenomePessoal(dadosNewUser.getSobrenomeUsur())
                .cpfPessoal(dadosNewUser.getCpfUsur())
                .cidadePessoal(dadosNewUser.getCidadeUsur())
                .estadoPessoal(dadosNewUser.getEstadoUsur())
                .dataNascimento(dadosNewUser.getNascimentoUsur())
                .genero(dadosNewUser.getGeneroUsur())
                .telefone(dadosNewUser.getTelefoneUsur())
                .emailUsuario(dadosNewUser.getEmailUsur())

                .senha( this.pEnconder.encode(dadosNewUser.getSenhaUsur()) )
                .username( dadosNewUser.getEmailUsur() )
                .roles( roles )
                .build();

        usuarioRepository.save(newUser);
        return newUser;
    }

    @Override
    public UsuarioApresentacaoVO getUsuarioPorId(BigInteger idUsuario) throws ObjectNotFound {

        Optional.ofNullable(idUsuario).orElseThrow(() -> new ArgumentNotValid("idUsuario não pode ser nulo"));

        return usuarioConverter.entityToApresentacaoVO( this.usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ObjectNotFound("Não foi possivel encontrar o mestre de id: " + idUsuario)) );
    }

    @Override
    public HttpGenericPageableResponse getTodosUsuariosSemDadosSensiveis(int pagina, int qtdPagina) throws ObjectNotFound {
        List<UsuarioApresentacaoVO> listaUsuariosVO = new ArrayList<>();

        PageRequest pageable = PageRequest.of(pagina,qtdPagina, Sort.by("nomePessoal").ascending());

        Page<List<Object>> usuarios = usuarioRepository.getUsuariosAtivos(pageable);

        for (Object obj : usuarios.getContent()) {
            listaUsuariosVO.add(usuarioConverter.entityToApresentacaoVO( (UsuarioEntity) obj ));
        }

        HttpGenericPageableResponse resp = new HttpGenericPageableResponse();
        GenericPageRequestResponse pageRequest = new GenericPageRequestResponse(usuarios.getNumber(),
                usuarios.getSize(),usuarios.getTotalElements(),usuarios.getTotalPages(),
                usuarios.getSort().toString());
        resp.setPageRequestResponse(pageRequest);
        resp.setData(listaUsuariosVO);

        return resp;
    }

    @Override
    public DadosUsuarioVO editarDadosBasicosUsuario(BigInteger idUsuario, DadosUsuarioVO dadosUsuario) throws ObjectNotFound {

        Optional.ofNullable(idUsuario).orElseThrow(() -> new ArgumentNotValid("idUsuario não pode ser nulo"));

        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);

        if (usuarioEntity.isPresent()) {

            //Se tiver divergencia no BD e recebido
            usuarioEntity.get().setGenero( (!usuarioEntity.get().getGenero().equals(dadosUsuario.getGeneroUsur())) ? dadosUsuario.getGeneroUsur() : usuarioEntity.get().getGenero() );
            usuarioEntity.get().setCidadePessoal( (!usuarioEntity.get().getCidadePessoal().equals(dadosUsuario.getCidadeUsur())) ? dadosUsuario.getCidadeUsur() : usuarioEntity.get().getCidadePessoal() );
            usuarioEntity.get().setDataNascimento( (!usuarioEntity.get().getDataNascimento().equals(dadosUsuario.getNascimentoUsur())) ? dadosUsuario.getNascimentoUsur() : usuarioEntity.get().getDataNascimento() );
            usuarioEntity.get().setEmailUsuario( (!usuarioEntity.get().getEmailUsuario().equals(dadosUsuario.getEmailUsur())) ? dadosUsuario.getEmailUsur() : usuarioEntity.get().getEmailUsuario() );
            usuarioEntity.get().setNomePessoal( (!usuarioEntity.get().getNomePessoal().equals(dadosUsuario.getNomeUsur())) ? dadosUsuario.getNomeUsur() : usuarioEntity.get().getNomePessoal() );
            usuarioEntity.get().setSobrenomePessoal( (!usuarioEntity.get().getSobrenomePessoal().equals(dadosUsuario.getSobrenomeUsur())) ? dadosUsuario.getSobrenomeUsur() : usuarioEntity.get().getSobrenomePessoal() );
            usuarioEntity.get().setTelefone( (!usuarioEntity.get().getTelefone().equals(dadosUsuario.getTelefoneUsur())) ? dadosUsuario.getTelefoneUsur() : usuarioEntity.get().getTelefone() );

            usuarioRepository.save( usuarioEntity.get() );
        }else {
            throw new ObjectNotFound("Não foi encontrado em nosso sistema esse usuário.");
        }
        return null;
    }

    @Override
    public void inativaUsuario(BigInteger idUsuario) throws ObjectNotFound {

        Optional.ofNullable(idUsuario).orElseThrow(() -> new ArgumentNotValid("idUsuario não pode ser nulo"));

        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);

        if (usuarioEntity.isPresent()) {
            usuarioEntity.get().setFlagAtivo(new BigInteger("0")); //Alterando de ATIVO[ 1 ] | para INATIVO[ 0 ]
            usuarioRepository.save( usuarioEntity.get() );
        }else {
            throw new ObjectNotFound("Não foi encontrado em nosso sistema esse usuário.");
        }
    }

    public DadosUsuarioEnvioWhatsapp dadosEnvioWhatsapp(BigInteger idUsuario,String mensagemEnvio) {

        Optional.ofNullable(idUsuario).orElseThrow(() -> new ArgumentNotValid("idUsuario não pode ser nulo"));

        Optional<UsuarioEntity> usuarioEntity = this.usuarioRepository.findById(idUsuario);

        if (usuarioEntity.isPresent()) {

            try {
                String mensagemDefault = "Olá |NOME_USUARIO| ! Vi sua solicitação no site X-RPG e " +
                        "tenho interesse em jogar com você. Poderia me falar mais sobre a sua campanha ?";

                StringBuilder urlWhatsapp = new StringBuilder();

                urlWhatsapp.append("https://api.whatsapp.com/send?phone=55");
                urlWhatsapp.append(usuarioEntity.get().getTelefone());
                urlWhatsapp.append("&text=");

                if (mensagemEnvio == null) {
                    urlWhatsapp.append(mensagemDefault.replace("|NOME_USUARIO|",usuarioEntity.get().getNomePessoal()));
                } else {
                    urlWhatsapp.append(mensagemEnvio);
                }

                return DadosUsuarioEnvioWhatsapp.builder()
                        .idUsuario(usuarioEntity.get().getIdUsuario())
                        .nomeUsuario(usuarioEntity.get().getNomePessoal().concat(" ").concat(usuarioEntity.get().getSobrenomePessoal()))
                        .urlApiWhatsapp(urlWhatsapp.toString()).build();
            }catch (Exception e) {
                throw new RuntimeException("Erro durante a construção da url usada para a API Whatsapp");
            }
        }
        throw new ObjectNotFound("Usuário não encontrado.");
    }
}
