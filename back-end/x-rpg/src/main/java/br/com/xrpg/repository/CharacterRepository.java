package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.CharacterEntity;
import br.com.xrpg.entity.ClassEntity;
import br.com.xrpg.entity.RaceEntity;

@Repository
public interface CharacterRepository extends CrudRepository<CharacterEntity, BigInteger> {
	
	@Query("FROM CharacterEntity c GROUP BY c.idCharacter")
	public List<CharacterEntity> getCharactersListEntitysDistinctName();
	
}
