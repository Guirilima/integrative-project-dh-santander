package br.com.xrpg.repository;

import br.com.xrpg.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDetailsRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}
