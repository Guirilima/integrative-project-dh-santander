package br.com.xrpg.service;

import br.com.xrpg.exceptions.ObjectNotFound;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.vo.DadosUsuarioVO;

import java.math.BigInteger;
import java.util.List;

@Service
public interface UsuarioService {

    UsuarioEntity criarNovoUsuarioCadastro(DadosUsuarioVO dadosNewUser) throws ErrorSalvamento;

    DadosUsuarioVO getUsuarioPorId(BigInteger idUsuario) throws ObjectNotFound;

    List<DadosUsuarioVO> getTodosUsuariosSemDadosSensiveis() throws ObjectNotFound;

    DadosUsuarioVO editarDadosBasicosUsuario(BigInteger idUsuario,DadosUsuarioVO dadosUsuario) throws ObjectNotFound;
}
