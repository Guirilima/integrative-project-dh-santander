package br.com.xrpg.service;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.vo.DadosUsuarioCadastramentoVO;

@Service
public interface UserService {

    UserEntity criarNovoUsuarioCadastro(DadosUsuarioCadastramentoVO dadosNewUser) throws ErrorSalvamento;
}
