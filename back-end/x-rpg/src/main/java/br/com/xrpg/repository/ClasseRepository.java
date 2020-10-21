package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.ClasseEntity;

@Repository
public interface ClasseRepository extends CrudRepository<ClasseEntity, BigInteger> {

    //Ou DISTINCT e.descripClass
    @Query("FROM ClasseEntity ")
    public List<ClasseEntity> getTodasClasses();
}
