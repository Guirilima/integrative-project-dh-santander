package br.com.xrpg.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.xrpg.entity.MestreEntity;

public interface MestreRepository extends JpaRepository<MestreEntity, BigInteger> {

    public MestreEntity findByIdUsuario(BigInteger idUsuario);

}
