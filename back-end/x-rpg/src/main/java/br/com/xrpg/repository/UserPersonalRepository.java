package br.com.xrpg.repository;

import br.com.xrpg.entity.UserPersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserPersonalRepository extends CrudRepository<UserPersonalEntity, BigInteger> {

    @Query(value = "SELECT u.idUser,u.idUserAuth,ua.emailUser,ua.passwordUser," +
            "up.cpfPersonal,up.nomePersonal,up.sobrenomePersonal FROM UserEntity u INNER JOIN UserAuthEntity ua ON ua.idUserAuth = u.idUserAuth " +
            "INNER JOIN UserPersonalEntity up ON up.idUserPersonal = u.IdUserPersonal WHERE u.idUser = :idUser")
    public Object buscarPorDadosDoUsuario(@Param("idUser") BigInteger idUser);

    public UserPersonalEntity findByCpfPersonal(String cpfUser);
}
