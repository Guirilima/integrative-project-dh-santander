package br.com.xrpg.service;

import org.springframework.stereotype.Service;
import java.util.List;
import br.com.xrpg.entity.CharacterEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;


@Service
public interface CharacterService {
	
	public CharacterEntity createCharacter(CharacterEntity newCharacter) throws ErrorSalvamento;
	
	public List<CharacterEntity> listCharacters();
	
}




