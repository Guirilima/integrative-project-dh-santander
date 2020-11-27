package br.com.xrpg.service.serviceImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.CampanhaEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.CampanhaRepository;
import br.com.xrpg.service.CampanhaService;

@Service
public class CampanhaServiceImpl implements CampanhaService {
	
	@Autowired
	CampanhaRepository campanhaRepository;

	@Override
	public CampanhaEntity criar(CampanhaEntity campanhaEntity) throws ErrorSalvamento {
		
		Optional.ofNullable(campanhaEntity.getTituloCampanha()).orElseThrow( () -> new ErrorSalvamento("O titulo da campanha nao pode ser nulo"));
		Optional.ofNullable(campanhaEntity.getHistoriaCampanha()).orElseThrow( () -> new ErrorSalvamento("A descricao da campanha nao pode ser nula"));
		
		if (campanhaEntity.getHistoriaCampanha().length() > 1000) throw new ErrorSalvamento ("A historia excede o limite de 1000 caracteres");
		
		this.campanhaRepository.save(campanhaEntity);
		
		return campanhaEntity;
	}

	@Override
	public CampanhaEntity encontrarPorId(BigInteger bigInteger) throws ObjectNotFound {
		Optional
		.ofNullable(bigInteger)
		.orElseThrow( () -> new ArgumentNotValid("O id informado não é valido"));
		
		 return this.campanhaRepository.findById(bigInteger)
               .orElseThrow(() -> new ObjectNotFound("Campanha não encontrada"));
	}

	@Override
	public List<CampanhaEntity> listar() {
		//return this.campanhaRepository.findAll();
		return this.campanhaRepository.findBycampanhaAtivaTrue();
	}

	@Override
	public void editar(CampanhaEntity campanhaEntity) {
		this.campanhaRepository.findById(campanhaEntity.getIdCampanha());
		this.campanhaRepository.save(campanhaEntity);
	}

	@Override
	public void deletar(BigInteger bigInteger) {
		this.campanhaRepository.findById(bigInteger);
		this.campanhaRepository.deleteById(bigInteger);
		
	}

}
