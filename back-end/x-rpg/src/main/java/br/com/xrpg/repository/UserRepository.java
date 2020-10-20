package br.com.xrpg.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, BigInteger> {
}
