package br.com.xrpg.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xrpg.entity.NoticiasEntity;

public interface NoticiasRepository extends JpaRepository <NoticiasEntity, BigInteger>{

}
