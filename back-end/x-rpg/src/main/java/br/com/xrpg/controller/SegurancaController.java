package br.com.xrpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xrpg.service.MetodosValidadores;
import br.com.xrpg.vo.HttpGenericResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/securanca")
@Slf4j
public class SegurancaController {

    @Autowired
    MetodosValidadores metodosValidadores;

    @PostMapping(path = "/codificar-string",produces = "application/json")
    public ResponseEntity<HttpGenericResponse> validacaoPassword(@RequestParam("valor") String valor,
                                                                 @RequestParam("tipo") String tipo) {
        try {

            valor = metodosValidadores.filtroCodificacao(valor,tipo);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(valor).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
