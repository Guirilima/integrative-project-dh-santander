package br.com.xrpg.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.xrpg.entity.CampanhaEntity;

@Repository
public interface CampanhaRepository extends JpaRepository <CampanhaEntity, BigInteger>{
	
	@Query(value ="Select c from CampanhaEntity c WHERE c.campanhaAtiva = true")
	public List<CampanhaEntity> findBycampanhaAtivaTrue();

}
