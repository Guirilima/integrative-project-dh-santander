package br.com.xrpg.service;


import br.com.xrpg.vo.HttpGenericPageableResponse;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.exceptions.ErrorSalvamento;
import br.com.xrpg.exceptions.ObjectNotFound;


@Service
public interface PersonagemService {
	
	public PersonagemEntity criar(PersonagemEntity personagemEntity) throws ErrorSalvamento;
	
	public PersonagemEntity encontrarPorId (BigInteger bigInteger) throws ObjectNotFound;

	HttpGenericPageableResponse listar(int pagina,int qtdPagina);
	
	public void atualizar(PersonagemEntity personagemEntity);
	
	public void deletar(BigInteger bigInteger);

	public HttpGenericPageableResponse buscarPersonagemPorUsuario(BigInteger idUsuario,int pagina,int qtdPagina);
	
}





