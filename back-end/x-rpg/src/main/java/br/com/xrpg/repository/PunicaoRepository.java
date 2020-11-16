package br.com.xrpg.repository;

import br.com.xrpg.entity.PunicaoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PunicaoRepository extends CrudRepository<PunicaoEntity, BigInteger> {

}
