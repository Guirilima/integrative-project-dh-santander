package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.RacaEntity;

@Repository
public interface RacaRepository extends JpaRepository<RacaEntity, BigInteger> {

    @Query(value = "FROM RacaEntity r ")
    public List<RacaEntity> getAllRaca();

}
