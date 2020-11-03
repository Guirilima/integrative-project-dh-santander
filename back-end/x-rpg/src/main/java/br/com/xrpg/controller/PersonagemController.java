package br.com.xrpg.controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.service.PersonagemService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.service.PersonagemService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/personagem")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class PersonagemController {

	@Autowired
	PersonagemService personagemService;

	@ApiOperation(value = "API responsavel por criar um novo personagem.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "criaçao concluída"),
							@ApiResponse(code = 400, message = "Erro na criação do personagem") })
	
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> criarPersonagem(@RequestBody PersonagemEntity personagem) {
		try {

			PersonagemEntity novoPersonagem = personagemService.criar(personagem);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPersonagem}").buildAndExpand(novoPersonagem.getIdPersonagem())
					.toUri();
			
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("Criação concluída")
					.response(uri).build(), HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage()).response(null).build(),
					HttpStatus.BAD_REQUEST);
		}
	} 	
	
	@ApiOperation(value = "API responsavel por listar todos os personagens do sistema.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "lista de personagens encontrada."),
	@ApiResponse(code = 400, message = "Erro na listagem das classes.") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> listarPersonagem() {
		
		try {

			List<PersonagemEntity> lista = personagemService.listar();

			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("")
					.response(lista).build(),HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null)
					.build(),
					HttpStatus.BAD_REQUEST);
		}
	}


	@ApiOperation(value = "API responsavel por encontrar personagem pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "personagem encontrado com sucesso."),
	@ApiResponse(code = 400, message = "personagem nao encontrado") })
	@RequestMapping(value ="/{idPersonagem}",method = RequestMethod.GET, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> encontrarPersonagem(@PathVariable BigInteger idPersonagem) {
		
		try {

			PersonagemEntity resultado = personagemService.encontrarPorId(idPersonagem);

			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("")
					.response(resultado).build(),HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null)
					.build(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API responsavel por excluir personagem do sistema pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "personagem excluido sucesso."),
	@ApiResponse(code = 400, message = "personagem nao encontrado") })
	@RequestMapping(value ="/{idPersonagem}",method = RequestMethod.DELETE, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> deletarPersonagem(@PathVariable BigInteger idPersonagem) {
		
		try {

			personagemService.deletar(idPersonagem);

			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("Personagem excluido com sucesso")
					.response(null).build(),HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null)
					.build(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API responsavel por atualizar personagem no sistema pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "dados do personagem atualizados com sucesso."),
	@ApiResponse(code = 400, message = "personagem nao encontrado") })
	@RequestMapping(value ="/{idPersonagem}",method = RequestMethod.PUT, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> atualizarPersonagem(@PathVariable("idPersonagem") BigInteger idPersonagem, @RequestBody PersonagemEntity personagemEntity) {
		
		try {
			
			personagemEntity.setIdPersonagem(idPersonagem);

			personagemService.atualizar(personagemEntity);

			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("Personagem atualizado com sucesso")
					.response(null).build(),HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null)
					.build(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
