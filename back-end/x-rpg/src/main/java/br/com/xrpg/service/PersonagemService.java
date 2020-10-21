package br.com.xrpg.service;

import org.springframework.stereotype.Service;
import java.util.List;
import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;


@Service
public interface PersonagemService {
	
	public PersonagemEntity criarPersonagem(PersonagemEntity newCharacter) throws ErrorSalvamento;
	
	public List<PersonagemEntity> listCharacters();
	
}




