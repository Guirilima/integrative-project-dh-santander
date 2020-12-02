package br.com.xrpg.service.serviceImpl;

import br.com.xrpg.entity.NotificacaoEntity;
import br.com.xrpg.entity.UsuarioEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.NotificacaoReposiotry;
import br.com.xrpg.service.NoticiasService;
import br.com.xrpg.service.NotificacaoService;
import br.com.xrpg.vo.GenericPageRequestResponse;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import br.com.xrpg.vo.UsuarioApresentacaoVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificacaoServiceImpl implements NotificacaoService {

    private final NotificacaoReposiotry notificacaoReposiotry;

    @Override
    public NotificacaoEntity criar(NotificacaoEntity newNotificacaoEntity) throws ErrorSalvamento {
        try {
            newNotificacaoEntity.setFlagSituacaoConvite("N");
            newNotificacaoEntity.setDataConvite(new Date());
            if (newNotificacaoEntity.getIdPersonagemConvidado() != null) {
                newNotificacaoEntity.setDestinoNotificacao("Personagem");
            }else {
                newNotificacaoEntity.setDestinoNotificacao("Mestre");
            }
            return notificacaoReposiotry.save(newNotificacaoEntity);
        }catch (Exception ee) {
            throw new ErrorSalvamento("Erro durante o salvamento. Por favor confirme o preenchimento de todos os campos.");
        }
    }

    @Override
    public NotificacaoEntity buscarPorId(BigInteger idNotificacao) throws ObjectNotFound {
        try {
            return notificacaoReposiotry.findById(idNotificacao).get();
        }catch (Exception ee) {
            throw new ObjectNotFound("Erro durante a buscar pela Notificação.");
        }
    }

    @Override
    public HttpGenericPageableResponse listarTodosPorUsuarioConvidou(BigInteger idUsuario,int pagina,int qtdPagina) {
        try {
            PageRequest pageable = PageRequest.of(pagina,qtdPagina, Sort.by("dataConvite").descending());

            Page<List<NotificacaoEntity>> notificacoes = notificacaoReposiotry.findAllByIdUsuarioConvidou(idUsuario,pageable);

            HttpGenericPageableResponse resp = new HttpGenericPageableResponse();
            GenericPageRequestResponse pageRequest = new GenericPageRequestResponse(notificacoes.getNumber(),
                    notificacoes.getSize(),notificacoes.getTotalElements(),notificacoes.getTotalPages(),
                    notificacoes.getSort().toString());
            resp.setPageRequestResponse(pageRequest);
            resp.setData(notificacoes.getContent());

            return resp;
        }catch (Exception ee) {
            throw new RuntimeException("Erro durante a listagem das notificações.");
        }
    }

    @Override
    public HttpGenericPageableResponse listarTodosPorUsuarioConvidado(BigInteger idUsuario,int pagina,int qtdPagina) {
        try {
            PageRequest pageable = PageRequest.of(pagina,qtdPagina, Sort.by("dataConvite").descending());

            Page<List<NotificacaoEntity>> notificacoes = notificacaoReposiotry.findAllByIdUsuarioConvidado(idUsuario,pageable);

            HttpGenericPageableResponse resp = new HttpGenericPageableResponse();
            GenericPageRequestResponse pageRequest = new GenericPageRequestResponse(notificacoes.getNumber(),
                    notificacoes.getSize(),notificacoes.getTotalElements(),notificacoes.getTotalPages(),
                    notificacoes.getSort().toString());
            resp.setPageRequestResponse(pageRequest);
            resp.setData(notificacoes.getContent());

            return resp;
        }catch (Exception ee) {
            throw new RuntimeException("Erro durante a listagem das notificações.");
        }
    }

    @Override
    public void editarFlagOuDesc(BigInteger idNotificacao, String newFlagSituacao, String newDescricao) {
        try {
            NotificacaoEntity notificacaoEntityBD = this.buscarPorId(idNotificacao);

            if (newFlagSituacao != null && !newFlagSituacao.isEmpty()) {
                notificacaoEntityBD.setFlagSituacaoConvite( newFlagSituacao );
            }
            if (newDescricao != null && !newDescricao.isEmpty()) {
                notificacaoEntityBD.setDescricaoConvite( newDescricao );
            }

            notificacaoReposiotry.save(notificacaoEntityBD);

        }catch (Exception ee) {
            throw new RuntimeException("Erro durante a atualização dos novos atributos");
        }
    }

    @Override
    public void deletar(BigInteger idNotificacao) {
        try {
            notificacaoReposiotry.delete( this.buscarPorId(idNotificacao) );
        }catch (Exception ee) {
            throw new RuntimeException("Erro durante ao tentar deletar a notificação.");
        }
    }

}
