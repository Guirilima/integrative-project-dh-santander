package br.com.xrpg.repository;

import br.com.xrpg.entity.PersonagemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PersonagemRepository extends CrudRepository<PersonagemEntity, BigInteger> {
}
