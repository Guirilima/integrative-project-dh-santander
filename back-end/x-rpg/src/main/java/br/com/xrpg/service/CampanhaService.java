package br.com.xrpg.service;

import java.math.BigInteger;
import java.util.List;
import br.com.xrpg.entity.CampanhaEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;

public interface CampanhaService {
	
	public CampanhaEntity criar(CampanhaEntity campanhaEntity) throws ErrorSalvamento;
	
	public CampanhaEntity encontrarPorId (BigInteger bigInteger) throws ObjectNotFound;
	
	public List<CampanhaEntity> listar();
	
	public void editar(CampanhaEntity campanhaEntity); 
	
	public void deletar(BigInteger bigInteger);
	
}
