package br.com.xrpg.repository;

import br.com.xrpg.entity.CharacterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterEntity, BigInteger> {
}