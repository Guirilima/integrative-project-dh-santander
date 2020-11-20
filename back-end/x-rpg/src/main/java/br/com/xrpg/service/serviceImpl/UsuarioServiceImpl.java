package br.com.xrpg.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UsuarioAutenticacaoEntity;
import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.entity.UsuarioPessoalEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.UsuarioAutenticacaoRepository;
import br.com.xrpg.repository.UsuarioPessoalRepository;
import br.com.xrpg.repository.UsuarioRepository;
import br.com.xrpg.service.UsuarioService;
import br.com.xrpg.utils.Utils;
import br.com.xrpg.vo.DadosUsuarioVO;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioAutenticacaoRepository usuarioAutenticacaoRepository;

    @Autowired
    UsuarioPessoalRepository usuarioPessoalRepository;

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
        if (usuarioPessoalRepository.findByCpfPessoal(dadosNewUser.getCpfUsur()) != null) throw new ErrorSalvamento("Já existe esse CPF em nosso banco de registro.");

        //TODO | SALVAMENTO USER_AUTH
        UsuarioAutenticacaoEntity newUserAuth = new UsuarioAutenticacaoEntity().builder()
                .emailUsuAutenticacao(dadosNewUser.getEmailUsur())
                .senhaUsuAutenticacao(Utils.criptografarString(dadosNewUser.getSenhaUsur()))
                .build();

        usuarioAutenticacaoRepository.save(newUserAuth);

        //TODO | SALVAMENTO USER_PERSONAL
        UsuarioPessoalEntity newUserPersonal = new UsuarioPessoalEntity().builder()
                .nomePessoal(dadosNewUser.getNomeUsur())
                .sobrenomePessoal(dadosNewUser.getSobrenomeUsur())
                .cpfPessoal(dadosNewUser.getCpfUsur())
                .cidadePessoal(dadosNewUser.getCidadeUsur())
                .estadoPessoal(dadosNewUser.getEstadoUsur())
                .dataNascimento(dadosNewUser.getNascimentoUsur())
                .genero(dadosNewUser.getGeneroUsur())
                .telefone(dadosNewUser.getTelefoneUsur())
                .build();

        usuarioPessoalRepository.save(newUserPersonal);

        //TODO | REUNINDO OS ID'S CRIADOS E SALVANDO O USER
        UsuarioEntity newUser = new UsuarioEntity().builder()
                .idUsuarioAutenticacao(newUserAuth.getIdUsuarioAutenticacao())
                .idUsuarioPessoal(newUserPersonal.getIdUsuarioPessoal())
                .flagAtivo(new BigInteger("1")) //Usuario Ativo
                .dataUltimoLogin(new Date())
                .build();

        usuarioRepository.save(newUser);

        return newUser;
    }

    @Override
    public DadosUsuarioVO getUsuarioPorId(BigInteger idUsuario) throws ObjectNotFound {
        return null;
    }

    @Override
    public Iterable<UsuarioEntity> getTodosUsuariosSemDadosSensiveis() throws ObjectNotFound {
        return usuarioRepository.findAll();
    }

    @Override
    public DadosUsuarioVO editarDadosBasicosUsuario(BigInteger idUsuario, DadosUsuarioVO dadosUsuario) throws ObjectNotFound {
        return null;
    }

    //Método Apenas para uso de DEV, pois retornará todos os dados do Usuario
    public HashMap<String, Object> getUsuarioPorId(DadosUsuarioVO dadosNewUser) throws ObjectNotFound {

        HashMap<String, Object> TodosDadosUsuario = new HashMap<>();

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        UsuarioAutenticacaoEntity usuarioAutenticacaoEntity = new UsuarioAutenticacaoEntity();
        UsuarioPessoalEntity usuarioPessoalEntity = new UsuarioPessoalEntity();

        return null;
    }

}
