package br.com.xrpg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.xrpg.entity.RaceEntity;
import br.com.xrpg.service.RaceService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/race")
@Slf4j
public class RaceController {

    @Autowired
    RaceService raceService;

    @ApiOperation(value = "API responsavel por lista todas as raças registradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de raça encontrada."),
            @ApiResponse(code = 400, message = "Erro na listagem das raças.")
    })
    @RequestMapping(value = "/listar-racas",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> listarRacas() {
        try {

            List<RaceEntity> listaRacasPersonagem = raceService.getListaRacas();

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

    @ApiOperation(value = "API responsavel por criar uma nova raça.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "criaçaõ concluída"),
            @ApiResponse(code = 400, message = "Erro na criação da raça")
    })
    @RequestMapping(value = "/inclusao-raca",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarRaca(@RequestBody RaceEntity newRaca) {
        try {

            newRaca = raceService.inclusaoRaca(newRaca);

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
