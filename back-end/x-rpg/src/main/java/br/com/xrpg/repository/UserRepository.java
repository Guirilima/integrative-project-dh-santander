package br.com.xrpg.repository;

import br.com.xrpg.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, BigInteger> {
}
