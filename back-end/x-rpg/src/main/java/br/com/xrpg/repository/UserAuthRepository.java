package br.com.xrpg.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.UserAuthEntity;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuthEntity, BigInteger> {

}
