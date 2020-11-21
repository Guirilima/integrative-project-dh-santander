package br.com.xrpg.service;

import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import br.com.xrpg.vo.UsuarioApresentacaoVO;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.vo.DadosUsuarioVO;

import java.math.BigInteger;
import java.util.List;

@Service
public interface UsuarioService {

    UsuarioEntity criarNovoUsuarioCadastro(DadosUsuarioVO dadosNewUser) throws ErrorSalvamento;

    UsuarioApresentacaoVO getUsuarioPorId(BigInteger idUsuario) throws ObjectNotFound;

    HttpGenericPageableResponse getTodosUsuariosSemDadosSensiveis(int pagina, int qtdPagina) throws ObjectNotFound;

    DadosUsuarioVO editarDadosBasicosUsuario(BigInteger idUsuario,DadosUsuarioVO dadosUsuario) throws ObjectNotFound;

    void inativaUsuario (BigInteger idUsuario) throws ObjectNotFound;
}
