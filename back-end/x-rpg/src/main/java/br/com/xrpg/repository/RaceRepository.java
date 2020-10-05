package br.com.xrpg.repository;

import br.com.xrpg.entity.RaceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface RaceRepository extends CrudRepository<RaceEntity, BigInteger> {


    @Query("FROM RaceEntity r GROUP BY r.nameClass")
    public List<RaceEntity> getListaRacaEntitysDistinctName();
}
