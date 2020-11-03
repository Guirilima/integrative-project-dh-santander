package br.com.xrpg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.entity.RacaEntity;
import br.com.xrpg.service.RacaService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/raca")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class RacaController {

    @Autowired
    RacaService racaService;

    @ApiOperation(value = "API RESPONSÁVEL POR LISTAR TODAS AS RAÇAS EXISTENTES NO SISTEMA.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de raça encontrada."),
            @ApiResponse(code = 400, message = "Erro na listagem das raças.")
    })
    @RequestMapping(value = "/listar-racas",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> listarRacas() {
        try {

            Iterable<RacaEntity> listaRacasPersonagem = racaService.getListaRacas();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(listaRacasPersonagem).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR CRIAR UMA NOVA RAÇA.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "criaçaõ concluída"),
            @ApiResponse(code = 400, message = "Erro na criação da raça")
    })
    @RequestMapping(value = "/inclusao-raca",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarRaca(@RequestBody RacaEntity newRaca) {
        try {

            newRaca = racaService.inclusaoRaca(newRaca);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Criação concluída")
                    .response(newRaca).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
