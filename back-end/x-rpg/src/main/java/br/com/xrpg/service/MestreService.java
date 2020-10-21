package br.com.xrpg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.MestreEntity;

@Service
public interface MestreService {

	public MestreEntity create(MestreEntity master) ;

	public MestreEntity update(MestreEntity master) ;
	public MestreEntity findById(BigInteger id) ;

	public List<MestreEntity> findAll();
	
	public void delete(BigInteger id) ;
}
