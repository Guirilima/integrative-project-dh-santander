package br.com.xrpg.service.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.CharacterEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.CharacterRepository;
import br.com.xrpg.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	@Autowired
    CharacterRepository characterRepository;

	@Override
	public CharacterEntity createCharacter(CharacterEntity newCharacter) throws ErrorSalvamento {
		Optional
		.ofNullable(newCharacter)
		.orElseThrow( () -> new ErrorSalvamento("O nome do personagem não pode ser nulo"));

        if (newCharacter.getBacksStory().length() < 100) throw new ErrorSalvamento("O backstory do personagem deve possuir mais do que 100 caracteres");
        
        this.characterRepository.save(newCharacter);	
        
        return newCharacter;
	}

	@Override
	public List<CharacterEntity> listCharacters(){
		
		return characterRepository.getCharactersListEntitysDistinctName();				
	}}





