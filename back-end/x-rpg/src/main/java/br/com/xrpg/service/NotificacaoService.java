package br.com.xrpg.service;

import br.com.xrpg.entity.NotificacaoEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface NotificacaoService {

    public NotificacaoEntity criar(NotificacaoEntity notificacaoEntity) throws ErrorSalvamento;

    public NotificacaoEntity buscarPorId(BigInteger idNotificacao) throws ObjectNotFound;

    public HttpGenericPageableResponse listarTodosPorUsuarioConvidou(BigInteger idUsuario,int pagina,int qtdPagina);

    public HttpGenericPageableResponse listarTodosPorUsuarioConvidado(BigInteger idUsuario, int pagina, int qtdPagina);

    public void editarFlagOuDesc(BigInteger idNotificacao,String newFlagSituacao,String newDescricao);

    public void deletar(BigInteger idNotificacao);
}
