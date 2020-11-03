package br.com.xrpg.repository;

import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.xrpg.entity.PersonagemEntity;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemEntity, BigInteger> {}

