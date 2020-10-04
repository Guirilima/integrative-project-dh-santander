package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.UserAuthEntity;
import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.entity.UserPersonalEntity;
import br.com.xrpg.repository.UserAuthRepository;
import br.com.xrpg.repository.UserPersonalRepository;
import br.com.xrpg.repository.UserRepository;
import br.com.xrpg.service.UserService;
import br.com.xrpg.vo.DadosUsuarioCadastramentoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserPersonalRepository userPersonalRepository;


    @Override
    public UserEntity criarNovoUsuarioCadastro(DadosUsuarioCadastramentoVO dadosNewUser) {
        //TODO | SERÁ NECESSARIO FAZER TRANSAÇÃO EM TRÊS TABELAS, SÃO ELAS>: USER_AUTH,USER_PERSONAL E USER
        //USER_PERSONAL = DADOS PESSOAIS
        //USER_AUTH = DADOS SENSÍVEIS PARA SEGURANÇA A CONTA
        //USER = CENTRO DE ENCONTRO PARA OUTRAS TABELAS EXTERNAS

        //TODO | SALVAMENTO USER_AUTH
        UserAuthEntity newUserAuth = new UserAuthEntity().builder()
                .emailUser(dadosNewUser.getEmailUser())
                .passwordUser(dadosNewUser.getPasswordUser())
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
        //TODO | È preciso implementar um Transictinal no método
    }
}
