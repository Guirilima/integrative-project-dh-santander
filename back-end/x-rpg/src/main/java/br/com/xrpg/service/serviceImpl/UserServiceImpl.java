package br.com.xrpg.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UserAuthEntity;
import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.entity.UserPersonalEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.UserAuthRepository;
import br.com.xrpg.repository.UserPersonalRepository;
import br.com.xrpg.repository.UserRepository;
import br.com.xrpg.service.UserService;
import br.com.xrpg.utils.Utils;
import br.com.xrpg.vo.DadosUsuarioCadastramentoVO;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserPersonalRepository userPersonalRepository;

    @Override
    @Transactional(rollbackOn = ErrorSalvamento.class)
    public UserEntity criarNovoUsuarioCadastro(DadosUsuarioCadastramentoVO dadosNewUser) throws ErrorSalvamento {

        //USER_PERSONAL = DADOS PESSOAIS
        //USER_AUTH = DADOS SENSÍVEIS PARA SEGURANÇA A CONTA
        //USER = CENTRO DE ENCONTRO PARA OUTRAS TABELAS EXTERNAS

        //VALIDAÇÔES
        if (dadosNewUser.getEmailUser() == null)                throw new ErrorSalvamento("Email do usuário nulo");
        if (!Utils.isEmailValido(dadosNewUser.getEmailUser()))  throw new ErrorSalvamento("Email não é valido");
        if (dadosNewUser.getPasswordUser() == null)             throw new ErrorSalvamento("Senha está nulo");
        if (dadosNewUser.getNameUser() == null)                 throw new ErrorSalvamento("Nome do usuário nulo");
        if (dadosNewUser.getCpfUser() == null)                  throw new ErrorSalvamento("CPF do usuário nulo");
        if (Utils.validaCPF(dadosNewUser.getCpfUser()) == null) throw new ErrorSalvamento("CPF não é valido");
        if (dadosNewUser.getCityUser() == null)                 throw new ErrorSalvamento("Cidade do usuário nulo");
        if (dadosNewUser.getStateUser() == null)                throw new ErrorSalvamento("Estado do usuário nulo");

        if (userPersonalRepository.findByCpfPersonal(dadosNewUser.getCpfUser()) != null) throw new ErrorSalvamento("Já existe esse CPF em nosso banco de registro.");

        //TODO | SALVAMENTO USER_AUTH
        UserAuthEntity newUserAuth = new UserAuthEntity().builder()
                .emailUser(dadosNewUser.getEmailUser())
                .passwordUser(Utils.criptografarString(dadosNewUser.getPasswordUser()))
                .build();

        userAuthRepository.save(newUserAuth);

        //TODO | SALVAMENTO USER_PERSONAL
        UserPersonalEntity newUserPersonal = new UserPersonalEntity().builder()
                .nomePersonal(dadosNewUser.getNameUser())
                .sobrenomePersonal(dadosNewUser.getLastNameUser())
                .cpfPersonal(dadosNewUser.getCpfUser())
                .cityPersonal(dadosNewUser.getCityUser())
                .statePersonal(dadosNewUser.getStateUser())
                .build();

        userPersonalRepository.save(newUserPersonal);

        //TODO | REUNINDO OS ID'S CRIADOS E SALVANDO O USER
        UserEntity newUser = new UserEntity().builder()
                .idUserAuth(newUserAuth.getIdUserAuth())
                .IdUserPersonal(newUserPersonal.getIdUserPersonal())
                .build();

        userRepository.save(newUser);

        return newUser;
    }
}
