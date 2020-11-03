package br.com.xrpg.service;


import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;


@Service
public interface PersonagemService {
	
	public PersonagemEntity criar(PersonagemEntity personagemEntity) throws ErrorSalvamento;
	
	public PersonagemEntity encontrarPorId (BigInteger bigInteger) throws ObjectNotFound;
	
	public List<PersonagemEntity> listar();
	
	public void atualizar(PersonagemEntity personagemEntity);
	
	public void deletar(BigInteger bigInteger);
	
}





