package br.com.xrpg.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xrpg.entity.MasterEntity;

public interface MasterRepository extends JpaRepository<MasterEntity, BigInteger> {

}
