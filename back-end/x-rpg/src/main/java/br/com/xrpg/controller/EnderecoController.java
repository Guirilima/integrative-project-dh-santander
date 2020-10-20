package br.com.xrpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xrpg.entity.CepEntity;
import br.com.xrpg.service.CepService;
import br.com.xrpg.vo.HttpGenericResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/endereco")
@Slf4j
public class EnderecoController {

    @Autowired
    CepService cepService;

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
