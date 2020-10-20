package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.ClassEntity;

@Repository
public interface ClassRepository extends CrudRepository<ClassEntity, BigInteger> {

    @Query("SELECT e FROM ClassEntity e GROUP BY e.nameClass") //Ou DISTINCT e.descripClass
    public List<ClassEntity> getListaClassEntitysDistinctName();
}
