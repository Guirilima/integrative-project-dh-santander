package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.PersonagemEntity;

@Repository
public interface PersonagemRepository extends CrudRepository<PersonagemEntity, BigInteger> {
	
	@Query("FROM PersonagemEntity c ")
	public List<PersonagemEntity> getTodosPersonagem();
	
}
