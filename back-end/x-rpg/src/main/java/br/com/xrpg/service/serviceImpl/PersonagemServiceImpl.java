package br.com.xrpg.service.serviceImpl;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import br.com.xrpg.entity.MestreEntity;
import br.com.xrpg.vo.GenericPageRequestResponse;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.PersonagemRepository;
import br.com.xrpg.service.PersonagemService;

@Service
public class PersonagemServiceImpl implements PersonagemService {
	
	
	@Autowired
	PersonagemRepository personagemRepository;
	

	@Override
	public PersonagemEntity criar(PersonagemEntity personagemEntity) throws ErrorSalvamento {
		
		Optional.ofNullable(personagemEntity.getNomePersonagem()).orElseThrow( () -> new ErrorSalvamento("O nome do personagem nao pode ser nulo"));
		Optional.ofNullable(personagemEntity.getIdRaca()).orElseThrow( () -> new ErrorSalvamento("A raca do personagem nao pode ser nula"));
		Optional.ofNullable(personagemEntity.getIdClasse()).orElseThrow( () -> new ErrorSalvamento("A classe do personagem nao pode ser nula"));;
		
		if (personagemEntity.getHistoriaPersonagem().length() > 400) throw new ErrorSalvamento ("A historia excede o limite de 400 caracteres");
		
		this.personagemRepository.save(personagemEntity);
		return personagemEntity;
	}


	@Override
	public PersonagemEntity encontrarPorId(BigInteger bigInteger) throws ObjectNotFound, ArgumentNotValid {
		
		Optional
		.ofNullable(bigInteger)
		.orElseThrow( () -> new ArgumentNotValid("O id informado nao e valido"));
		
		 return this.personagemRepository.findById(bigInteger)
               .orElseThrow(() -> new ObjectNotFound("O personagem nao foi encontrado"));
	}
	
	@Override
	public HttpGenericPageableResponse listar(int pagina,int qtdPagina) {

		PageRequest pageable = PageRequest.of(pagina,qtdPagina, Sort.by("nomePersonagem").ascending());

		Page<PersonagemEntity> personagens = personagemRepository.findAll(pageable);

		HttpGenericPageableResponse resp = new HttpGenericPageableResponse();
		GenericPageRequestResponse pageRequest = new GenericPageRequestResponse(personagens.getNumber(),
				personagens.getSize(),personagens.getTotalElements(),personagens.getTotalPages(),
				personagens.getSort().toString());
		resp.setPageRequestResponse(pageRequest);
		resp.setData(personagens.getContent());

		return resp;
	}


	@Override
	public void atualizar(PersonagemEntity personagemEntity){
		
		this.encontrarPorId(personagemEntity.getIdPersonagem());
		this.personagemRepository.save(personagemEntity);
	}


	@Override
	public void deletar(BigInteger bigInteger) {
		this.encontrarPorId(bigInteger);
		this.personagemRepository.deleteById(bigInteger);
	}
	
}		
	
	








