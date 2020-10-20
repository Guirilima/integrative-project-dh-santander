package br.com.xrpg.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UserAuthEntity;
import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.entity.UserPersonalEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.UserAuthRepository;
import br.com.xrpg.repository.UserPersonalRepository;
import br.com.xrpg.repository.UserRepository;
import br.com.xrpg.service.UserService;
import br.com.xrpg.utils.Utils;
import br.com.xrpg.vo.DadosUsuarioCadastramentoVO;

import java.math.BigInteger;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserPersonalRepository userPersonalRepository;

    @Override
    //@Transactional(rollbackOn = ErrorSalvamento.class)
    @Transactional
    public UserEntity criarNovoUsuarioCadastro(DadosUsuarioCadastramentoVO dadosNewUser) throws ErrorSalvamento {

        //USER_PERSONAL = DADOS PESSOAIS
        //USER_AUTH = DADOS SENSÍVEIS PARA SEGURANÇA A CONTA
        //USER = CENTRO DE ENCONTRO PARA OUTRAS TABELAS EXTERNAS

        String errorFields = "";

        //VALIDAÇÔES
        if (dadosNewUser.getEmailUser() == null)                errorFields = "Email do usuário";
        if (dadosNewUser.getPasswordUser() == null)             errorFields = "Senha";
        if (dadosNewUser.getNameUser() == null)                 errorFields = "Nome";
        if (dadosNewUser.getCpfUser() == null)                  errorFields = "CPF";
        if (dadosNewUser.getCityUser() == null)                 errorFields = "Cidade";
        if (dadosNewUser.getStateUser() == null)                errorFields = "Estado";
        if (dadosNewUser.getDateOfBirth() == null)              errorFields = "Estado";
        if (dadosNewUser.getPhoneNumber() == null)              errorFields = "Estado";
        if (dadosNewUser.getGenero() == null)                   errorFields = "Genero";

        if (!errorFields.isEmpty()) throw new ErrorSalvamento("Os seguintes campos obrigatorios estão nulos: " + errorFields);

        //VALIDAÇÂO DADOS SENSIVEIS

        if (!Utils.isEmailValido(dadosNewUser.getEmailUser()))  throw new ErrorSalvamento("Email não é valido");
        if (Utils.validaCPF(dadosNewUser.getCpfUser()) == null) throw new ErrorSalvamento( "CPF não é valido");
        if (userPersonalRepository.findByCpfPessoal(dadosNewUser.getCpfUser()) != null) throw new ErrorSalvamento("Já existe esse CPF em nosso banco de registro.");

        //TODO | SALVAMENTO USER_AUTH
        UserAuthEntity newUserAuth = new UserAuthEntity().builder()
                .emailUser(dadosNewUser.getEmailUser())
                .passwordUser(Utils.criptografarString(dadosNewUser.getPasswordUser()))
                .flagActiveUser(new BigInteger("1")) //Usuario Ativo
                .build();

        userAuthRepository.save(newUserAuth);

        //TODO | SALVAMENTO USER_PERSONAL
        UserPersonalEntity newUserPersonal = new UserPersonalEntity().builder()
                .nomePessoal(dadosNewUser.getNameUser())
                .sobrenomePessoal(dadosNewUser.getLastNameUser())
                .cpfPessoal(dadosNewUser.getCpfUser())
                .cidadePessoal(dadosNewUser.getCityUser())
                .estadoPessoal(dadosNewUser.getStateUser())
                .dataNascimento(dadosNewUser.getDateOfBirth())
                .genero(dadosNewUser.getGenero())
                .telefone(dadosNewUser.getPhoneNumber())
                .build();

        userPersonalRepository.save(newUserPersonal);

        //TODO | REUNINDO OS ID'S CRIADOS E SALVANDO O USER
        UserEntity newUser = new UserEntity().builder()
                .idUserAuth(newUserAuth.getIdUserAuth())
                .IdUserPersonal(newUserPersonal.getIdUsuarioPessoal())
                .flagActive(new BigInteger("1")) //Usuario Ativo
                .build();

        userRepository.save(newUser);

        return newUser;
    }

    //Método Apenas para uso de DEV, pois retornará todos os dados do Usuario
    public HashMap<String, Object> getUsuarioPorId(DadosUsuarioCadastramentoVO dadosNewUser) throws ObjectNotFound {

        HashMap<String, Object> TodosDadosUsuario = new HashMap<>();

        UserEntity userEntity = new UserEntity();
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        UserPersonalEntity userPersonalEntity = new UserPersonalEntity();

        return null;
    }

}
