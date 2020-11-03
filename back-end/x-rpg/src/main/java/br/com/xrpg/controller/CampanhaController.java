package br.com.xrpg.controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.xrpg.entity.CampanhaEntity;
import br.com.xrpg.service.CampanhaService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/campanha")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class CampanhaController {

	@Autowired
	CampanhaService campanhaService;

	@ApiOperation(value = "API responsavel por criar uma nova campanha.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "criaçao concluída"),
							@ApiResponse(code = 400, message = "Erro na criação da campanha") })
	
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> criarCampanha(@RequestBody CampanhaEntity campanha) {
		try {

			CampanhaEntity novaCampanha = campanhaService.criar(campanha);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCampanha}").buildAndExpand(novaCampanha.getIdCampanha())
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
	
	@ApiOperation(value = "API responsavel por listar todas as campanhas ativas do sistema.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "lista de campanhas encontrada."),
	@ApiResponse(code = 400, message = "Erro na listagem das campanhas.") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> listarCampanha() {
		
		try {

			List<CampanhaEntity> lista = this.campanhaService.listar();

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


	@ApiOperation(value = "API responsavel por encontrar campanha pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Campanha encontrada com sucesso."),
	@ApiResponse(code = 400, message = "Campanha nao encontrada") })
	@RequestMapping(value ="/{idCampanha}",method = RequestMethod.GET, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> encontrarCampanha(@PathVariable BigInteger idCampanha) {
		
		try {

			CampanhaEntity resultado = campanhaService.encontrarPorId(idCampanha);

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

	@ApiOperation(value = "API responsavel por excluir campanha do sistema pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Campanha excluido sucesso."),
	@ApiResponse(code = 400, message = "Campanha nao encontrada") })
	@RequestMapping(value ="/{idCampanha}",method = RequestMethod.DELETE, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> deletarPersonagem(@PathVariable BigInteger idCampanha) {
		
		try {

			campanhaService.deletar(idCampanha);

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

	@ApiOperation(value = "API responsavel por editar campanha no sistema pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "dados da campanha atualizados com sucesso."),
	@ApiResponse(code = 400, message = "campanha nao encontrada") })
	@RequestMapping(value ="/{idCampanha}",method = RequestMethod.PUT, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> editarPersonagem(
			@PathVariable("idCampanha") BigInteger idCampanha, 
			@RequestBody CampanhaEntity campanhaEntity) {
		
		try {
			
			campanhaEntity.setIdCampanha(idCampanha);

			campanhaService.editar(campanhaEntity);

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
