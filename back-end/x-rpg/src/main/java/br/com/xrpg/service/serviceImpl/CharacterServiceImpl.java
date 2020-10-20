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
		
		Optional
		.ofNullable(newCharacter.getIdUser())
		.orElseThrow( () -> new ErrorSalvamento("O personagem precisa estar atrelado a um usuario"));
		
		Optional
		.ofNullable(newCharacter.getIdRace())
		.orElseThrow( () -> new ErrorSalvamento("A raca do personagem não pode ser nula"));

        if (newCharacter.getBacksStory().length() < 450) throw new ErrorSalvamento("O backstory do personagem deve possuir, no maximo, 449 caracteres");
        
        this.characterRepository.save(newCharacter);	
        
        return newCharacter;
	}

	@Override
	public List<CharacterEntity> listCharacters(){
		
		return characterRepository.getCharactersListEntitysDistinctName();				
	}}





