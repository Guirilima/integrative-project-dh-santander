package br.com.xrpg.service.serviceImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xrpg.entity.NoticiasEntity;
import br.com.xrpg.exceptions.ArgumentNotValid;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import br.com.xrpg.repository.NoticiasRepository;
import br.com.xrpg.service.NoticiasService;

@Service
public class NoticiasServiceImpl implements NoticiasService {
	
	@Autowired
	NoticiasRepository noticiasRepository;

	@Override
	public NoticiasEntity criar(NoticiasEntity noticiasEntity) throws ErrorSalvamento {
		Optional.ofNullable(noticiasEntity.getTituloNoticia()).orElseThrow( () -> new ErrorSalvamento("O titulo da notícia nao pode ser nulo"));
		Optional.ofNullable(noticiasEntity.getConteudoCompleto()).orElseThrow( () -> new ErrorSalvamento("O conteudo da notícia nao pode ser nulo"));
		
		if (noticiasEntity.getConteudoCompleto().length() > 2000) throw new ErrorSalvamento ("A notícia excede o limite de 2000 caracteres");
		
		this.noticiasRepository.save(noticiasEntity);
		return noticiasEntity;
	}

	@Override
	public NoticiasEntity encontrarPorId(BigInteger bigInteger) throws ObjectNotFound {
		
		Optional
		.ofNullable(bigInteger)
		.orElseThrow( () -> new ArgumentNotValid("O id informado nao e valido"));
		
		 return this.noticiasRepository.findById(bigInteger)
               .orElseThrow(() -> new ObjectNotFound("A noticia nao foi encontrada"));
	
	}

	@Override
	public List<NoticiasEntity> listar() {
		return this.noticiasRepository.findAll();
	}

	@Override
	public void editar(NoticiasEntity noticiasEntity) {
		this.encontrarPorId(noticiasEntity.getIdNoticias());
		this.noticiasRepository.save(noticiasEntity);
		
	}

	@Override
	public void deletar(BigInteger bigInteger) {
		this.encontrarPorId(bigInteger);
		this.noticiasRepository.deleteById(bigInteger);
		
	}

}
