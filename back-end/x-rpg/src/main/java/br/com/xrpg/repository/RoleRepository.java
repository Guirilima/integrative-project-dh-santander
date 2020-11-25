package br.com.xrpg.repository;

import br.com.xrpg.entity.Role;
import br.com.xrpg.projection.UsuarioRoleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT usuario_id AS idUsuario,role_id AS idRole FROM usuarios_roles ur " +
            "WHERE ur.usuario_id = :idUsuario ",nativeQuery = true)
    public UsuarioRoleProjection findByIdUsuario(@Param("idUsuario")BigInteger idUsuario);

}
