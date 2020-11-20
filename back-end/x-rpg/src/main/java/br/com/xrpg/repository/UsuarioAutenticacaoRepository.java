package br.com.xrpg.repository;

import br.com.xrpg.entity.UsuarioAutenticacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAutenticacaoRepository extends JpaRepository<UsuarioAutenticacao, Long> {

    Optional<UsuarioAutenticacao> findByUsername(String username);
}
