package br.com.xrpg.controller;

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

	@ApiOperation(value = "API RESPONSÁVEL POR LISTAR TODOS OS PERSONAGENS EXISTENTE NO SISTEMA.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "lista de classes encontrada."),
	@ApiResponse(code = 400, message = "Erro na listagem das classes.") })
	@RequestMapping(value = "/listar-personagem", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> listCharacters() {
		try {

			List<PersonagemEntity> charactersList = personagemService.listCharacters();

		
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder().status("OK").mensagem("").response(personagemService).build(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder().status("NOK").mensagem(e.getMessage()).response(null).build(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API RESPONSÁVEL POR CRIAR UM NOVO PERSONAGEM.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "criaçao concluída"),
							@ApiResponse(code = 400, message = "Erro na criação da classe") })
	@RequestMapping(value = "/inclusao-personagem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> createCharacter(@RequestBody PersonagemEntity newCharacter) {
		try {

			newCharacter = personagemService.criarPersonagem(newCharacter);

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder().status("OK")
					.mensagem("Criação concluída").response(newCharacter).build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder().status("NOK").mensagem(e.getMessage()).response(null).build(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
