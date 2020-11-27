package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.xrpg.entity.PersonagemEntity;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemEntity, BigInteger> {

    public Page<List<PersonagemEntity>> findAllByIdUsuario(BigInteger idUsuario, Pageable pageable);
}

