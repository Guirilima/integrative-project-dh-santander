package br.com.xrpg.repository;

import br.com.xrpg.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuthEntity, BigInteger> {

}
