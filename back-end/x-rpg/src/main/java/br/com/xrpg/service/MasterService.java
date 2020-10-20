package br.com.xrpg.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.xrpg.entity.MasterEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ObjectNotFoundException;
import br.com.xrpg.repository.MasterRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MasterService {

	private MasterRepository repository;

	public MasterEntity create(MasterEntity master) {

		return this.repository.save(master);
	}

	public MasterEntity update(MasterEntity master) {
		findById(master.getId());

		return this.repository.save(master);
	}

	public MasterEntity findById(BigInteger id) {

		Optional.ofNullable(id).orElseThrow(() -> new ArgumentNotValid("Id não pode ser nulo"));

		return this.repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi possivel encontrar o mestre de id: " + id));
	}

	public List<MasterEntity> findAll() {
		return this.repository.findAll();
	}
	
	public void delete(BigInteger id) {
		this.findById(id);
		
		this.repository.deleteById(id);
	}
}
