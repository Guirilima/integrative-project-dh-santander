package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.RacaEntity;

@Repository
public interface RacaRepository extends CrudRepository<RacaEntity, BigInteger> {


    @Query("FROM RacaEntity r ")
    public List<RacaEntity> getTodasRacas();
}
