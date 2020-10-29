package br.com.xrpg.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.entity.CepEntity;
import br.com.xrpg.service.CepService;
import br.com.xrpg.vo.HttpGenericResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/endereco")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class EnderecoController {

    @Autowired
    CepService cepService;

    @ApiOperation(value = "API RESPONSÁVEL POR CHAMAR UMA API EXTERNA DE CEP E RETORNAR OS DADOS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dados do endereço encontrado."),
            @ApiResponse(code = 400, message = "Erro na buscar ou não encontrado.")
    })
    @PostMapping(path = "/buscar-pelo-cep/{cepString}",produces = "application/json")
    public ResponseEntity<HttpGenericResponse> buscarPeloCep(@PathVariable(value = "cepString") String cepString) {
        try {

            CepEntity cepEncontrado = cepService.findByCep(cepString);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(cepEncontrado).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
