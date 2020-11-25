package br.com.xrpg.controller;

import java.math.BigInteger;
import java.net.URI;

import br.com.xrpg.vo.HttpGenericPageableResponse;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.xrpg.entity.MestreEntity;
import br.com.xrpg.service.MestreService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/mestre")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class MestreController {

	private final MestreService service;

	@ApiOperation(value = "API RESPONSÁVEL POR CRIAR UM MESTRE.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Mestre Criado com sucesso."),
							@ApiResponse(code = 400, message = "Erro durante o salvamento e/ou manipulação dos dados.")
	})

	@PostMapping()
	public ResponseEntity<HttpGenericResponse> create(@RequestBody MestreEntity master) {
		try {
			//return ResponseEntity.created(uri).build();
			MestreEntity masterCreated = this.service.create(master);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idMestre}").buildAndExpand(masterCreated.getIdMestre())
					.toUri();

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("")
					.response(uri).build(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null).build(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API RESPONSÁVEL POR EDITAR UM MESTRE POR ID.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Mestre Criado com sucesso."),
							@ApiResponse(code = 400, message = "Erro durante a edição e/ou manipulação dos dados.")
	})
	@PutMapping("/{idMestre}")
	public ResponseEntity<HttpGenericResponse> update(@PathVariable("idMestre") BigInteger idMestre, @RequestBody MestreEntity master) {
		try {

			master.setIdMestre(idMestre);

			this.service.update(master);

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("")
					.response(master).build(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null).build(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API RESPONSÁVEL POR RETORNAR UM MESTRE POR ID.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Mestre retornado."),
							@ApiResponse(code = 400, message = "Erro durante a busca e/ou mestre não encontrado.")
	})

	@GetMapping("/{idMestre}")
	public ResponseEntity<HttpGenericResponse> findById(@PathVariable BigInteger idMestre) {
		try {

			MestreEntity master = this.service.findById(idMestre);

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("")
					.response(master).build(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null).build(), HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "API RESPONSÁVEL POR TRAZER TODOS OS MESTRES.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno com todos os mestres."),
							@ApiResponse(code = 400, message = "Erro durante a busca e/ou mestre não encontrado.")
	})
	@GetMapping
	public ResponseEntity<HttpGenericResponse> findAll(@RequestParam(value = "pagina",defaultValue = "0",required = false) int pagina,
													   @RequestParam(value = "qtdPagina",defaultValue = "6",required = false) int qtdPagina) {
		try {

			HttpGenericPageableResponse allMasters = this.service.findAll(pagina,qtdPagina);

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("")
					.response(allMasters).build(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null).build(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "API RESPONSÁVEL POR DELETAR UM MESTRE.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mestre deletado com sucesso."),
							@ApiResponse(code = 400, message = "Erro durante a deletação e/ou mestre não encontrado.")
	})
	@DeleteMapping("/{idMestre}")
	public ResponseEntity<HttpGenericResponse> deleteById(@PathVariable BigInteger idMestre){
		try {

			this.service.delete(idMestre);

			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("OK")
					.mensagem("Mestre deletado.")
					.response(null).build(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
					.status("NOK")
					.mensagem(e.getMessage())
					.response(null).build(), HttpStatus.BAD_REQUEST);
		}

	}
}
