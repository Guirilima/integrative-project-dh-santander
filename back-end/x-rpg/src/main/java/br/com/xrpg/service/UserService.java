package br.com.xrpg.service;

import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.vo.DadosUsuarioCadastramentoVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity criarNovoUsuarioCadastro(DadosUsuarioCadastramentoVO dadosNewUser) throws ErrorSalvamento;
}
