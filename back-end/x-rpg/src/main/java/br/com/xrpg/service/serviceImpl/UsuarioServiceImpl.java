package br.com.xrpg.service.serviceImpl;

import javax.transaction.Transactional;

import br.com.xrpg.converter.UsuarioConverter;
import br.com.xrpg.entity.UsuarioAutenticacao;
import br.com.xrpg.enumber.TipoUsuarioEnum;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.repository.UsuarioAutenticacaoRepository;
import br.com.xrpg.vo.UsuarioApresentacaoVO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.service.UsuarioService;
import br.com.xrpg.utils.Utils;
import br.com.xrpg.vo.DadosUsuarioVO;

import java.math.BigInteger;
import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioAutenticacaoRepository usuarioAutenticacaoRepository;

    private final BCryptPasswordEncoder pEnconder;

    private final UsuarioConverter usuarioConverter;

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

        //TODO | SALVANDO USUARIO AUTENTICAÇÂO
        UsuarioAutenticacao newUserAu = new UsuarioAutenticacao().builder()
                .role(TipoUsuarioEnum.fromId(dadosNewUser.getTipoUsuario()))
                .senha( this.pEnconder.encode(dadosNewUser.getSenhaUsur()) )
                .username(dadosNewUser.getNomeUsur())
                .build();
        usuarioAutenticacaoRepository.save(newUserAu);

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
                .tipoUsuarioEnum(dadosNewUser.getTipoUsuario())
                .emailUsuAutenticacao(dadosNewUser.getEmailUsur())
                .idUsuarioAutenticacao(newUserAu.getId())
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
    public List<UsuarioApresentacaoVO> getTodosUsuariosSemDadosSensiveis() throws ObjectNotFound {
        List<UsuarioApresentacaoVO> listaUsuariosVO = new ArrayList<>();

        for (UsuarioEntity usu : usuarioRepository.findAll()) {
            listaUsuariosVO.add( usuarioConverter.entityToApresentacaoVO( usu ) );
        }
        return listaUsuariosVO;
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
            usuarioEntity.get().setEmailUsuAutenticacao( (!usuarioEntity.get().getEmailUsuAutenticacao().equals(dadosUsuario.getEmailUsur())) ? dadosUsuario.getEmailUsur() : usuarioEntity.get().getEmailUsuAutenticacao() );
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
}
