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

import br.com.xrpg.entity.NoticiasEntity;
import br.com.xrpg.entity.PersonagemEntity;
import br.com.xrpg.service.NoticiasService;
import br.com.xrpg.service.PersonagemService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/noticias")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class NoticiasController {
	
	@Autowired
	NoticiasService noticiasService;

	@ApiOperation(value = "API responsavel por criar uma nova notícia.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "criaçao concluída"),
							@ApiResponse(code = 400, message = "Erro na criação da notícia") })
	
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HttpGenericResponse> criarNoticia(@RequestBody NoticiasEntity noticias) {
		try {

			NoticiasEntity novaNoticia = noticiasService.criar(noticias);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idNoticias}").buildAndExpand(novaNoticia.getIdNoticias())
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
	
	@ApiOperation(value = "API responsavel por listar todas as noticias do sistema.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "lista de noticias encontrada."),
	@ApiResponse(code = 400, message = "Erro na listagem das noticias.") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> listarNoticias() {
		
		try {

			List<NoticiasEntity> lista = noticiasService.listar();

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


	@ApiOperation(value = "API responsavel por encontrar noticia pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Noticia encontrada com sucesso."),
	@ApiResponse(code = 400, message = "Noticia nao encontrada") })
	@RequestMapping(value ="/{idNoticias}",method = RequestMethod.GET, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> encontrarPersonagem(@PathVariable BigInteger idNoticias) {
		
		try {

			NoticiasEntity resultado = noticiasService.encontrarPorId(idNoticias);

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

	@ApiOperation(value = "API responsavel por excluir noticia do sistema pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Noticia excluida com sucesso."),
	@ApiResponse(code = 400, message = "Noticia nao encontrado") })
	@RequestMapping(value ="/{idNoticias}",method = RequestMethod.DELETE, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> deletarPersonagem(@PathVariable BigInteger idNoticias) {
		
		try {

			noticiasService.deletar(idNoticias);

			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("Noticia excluida com sucesso")
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

	@ApiOperation(value = "API responsavel por editar a noticia sistema pelo ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Informações da notícia atualizadas com sucesso."),
	@ApiResponse(code = 400, message = "personagem nao encontrado") })
	@RequestMapping(value ="/{idNoticias}",method = RequestMethod.PUT, produces = "application/json")
	
	public ResponseEntity<HttpGenericResponse> editarNoticia(@PathVariable("idNoticias") BigInteger idNoticias, @RequestBody NoticiasEntity noticiasEntity) {
		
		try {
			
			noticiasEntity.setIdNoticias(idNoticias);

			noticiasService.editar(noticiasEntity);

			return new ResponseEntity<HttpGenericResponse>(
					new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("Noticia atualizada com sucesso")
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
