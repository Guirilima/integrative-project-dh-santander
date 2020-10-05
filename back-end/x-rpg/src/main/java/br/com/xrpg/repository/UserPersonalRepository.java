package br.com.xrpg.repository;

import br.com.xrpg.entity.UserPersonalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserPersonalRepository extends CrudRepository<UserPersonalEntity, BigInteger> {
}
