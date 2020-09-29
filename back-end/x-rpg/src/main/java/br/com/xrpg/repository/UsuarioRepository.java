package br.com.xrpg.repository;

import br.com.xrpg.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, BigInteger> {
}
