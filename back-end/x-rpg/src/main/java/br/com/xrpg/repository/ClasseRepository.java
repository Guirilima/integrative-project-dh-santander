package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import br.com.xrpg.entity.RacaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.ClasseEntity;

@Repository
public interface ClasseRepository extends CrudRepository<ClasseEntity, BigInteger> {

    @Query(value = "FROM ClasseEntity c ")
    public List<ClasseEntity> getAllClasses();

}
