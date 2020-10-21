package br.com.xrpg.service.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.repository.PersonagemRepository;
import br.com.xrpg.service.PersonagemService;

@Service
public class PersonagemServiceImpl implements PersonagemService {
	
	@Autowired
	PersonagemRepository personagemRepository;

	@Override
	public PersonagemEntity criarPersonagem(PersonagemEntity newCharacter) throws ErrorSalvamento {
		Optional
		.ofNullable(newCharacter)
		.orElseThrow( () -> new ErrorSalvamento("O nome do personagem não pode ser nulo"));
		
		Optional
		.ofNullable(newCharacter.getIdUsuario())
		.orElseThrow( () -> new ErrorSalvamento("O personagem precisa estar atrelado a um usuario"));
		
		Optional
		.ofNullable(newCharacter.getIdRaca())
		.orElseThrow( () -> new ErrorSalvamento("A raca do personagem não pode ser nula"));

        if (newCharacter.getHistoriaPersonagem().length() > 450) throw new ErrorSalvamento("O backstory do personagem deve possuir, no maximo, 449 caracteres");
        
        this.personagemRepository.save(newCharacter);
        
        return newCharacter;
	}

	@Override
	public List<PersonagemEntity> listCharacters(){
		
		return personagemRepository.getTodosPersonagem();
	}}





