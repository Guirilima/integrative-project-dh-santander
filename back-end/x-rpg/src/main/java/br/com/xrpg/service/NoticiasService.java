package br.com.xrpg.service;

import java.math.BigInteger;
import java.util.List;

import br.com.xrpg.entity.NoticiasEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;
import org.springframework.stereotype.Service;

@Service
public interface NoticiasService {
	
	public NoticiasEntity criar(NoticiasEntity noticiasEntity) throws ErrorSalvamento;
	
	public NoticiasEntity encontrarPorId (BigInteger bigInteger) throws ObjectNotFound;
	
	public List<NoticiasEntity> listar();
	
	public void editar(NoticiasEntity noticiasEntity); 
	
	public void deletar(BigInteger bigInteger);

}
