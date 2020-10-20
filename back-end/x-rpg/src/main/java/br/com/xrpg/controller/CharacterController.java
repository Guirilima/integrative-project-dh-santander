package br.com.xrpg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.xrpg.entity.CharacterEntity;
import br.com.xrpg.service.CharacterService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/character")
@Slf4j


public class CharacterController {

	@Autowired
	CharacterService characterService;

	@ApiOperation(value = "API responsavel por listar todos os personagens listados no sistema.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "lista de classes encontrada."),
	@ApiResponse(code = 400, message = "Erro na listagem das classes.") })
	@RequestMapping(value = "/listar-personagem", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> listCharacters() {
		try {

			List<CharacterEntity> charactersList = characterService.listCharacters();

		
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder().status("OK").mensagem("").response(characterService).build(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder().status("NOK").mensagem(e.getMessage()).response(null).build(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API responsavel por criar um novo personagem.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "criaçao concluída"),
			@ApiResponse(code = 400, message = "Erro na criação da classe") })
	@RequestMapping(value = "/inclusao-personagem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> createCharacter(@RequestBody CharacterEntity newCharacter) {
		try {

			newCharacter = characterService.createCharacter(newCharacter);

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder().status("OK")
					.mensagem("Criação concluída").response(newCharacter).build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder().status("NOK").mensagem(e.getMessage()).response(null).build(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
