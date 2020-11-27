package br.com.xrpg.repository;

import br.com.xrpg.entity.NotificacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface NotificacaoReposiotry extends JpaRepository<NotificacaoEntity, BigInteger> {

    public Page<List<NotificacaoEntity>> findAllByIdUsuarioConvidado(BigInteger idUsuario, org.springframework.data.domain.Pageable pageable);

    public Page<List<NotificacaoEntity>> findAllByIdUsuarioConvidou(BigInteger idUsuario, Pageable pageable);

}
