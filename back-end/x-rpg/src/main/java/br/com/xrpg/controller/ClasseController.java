package br.com.xrpg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.entity.ClasseEntity;
import br.com.xrpg.service.ClasseService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/classe")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class ClasseController {

    @Autowired
    ClasseService classeService;

    @ApiOperation(value = "API responsavel por lista todas as classes registradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de classes encontrada."),
            @ApiResponse(code = 400, message = "Erro na listagem das classes.")
    })
    @RequestMapping(value = "/listar-classes",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> listarClassePersonagem() {
        try {

            List<ClasseEntity> listaClassesPersonagem = classeService.getListaClasses();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(listaClassesPersonagem).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API responsavel por criar uma nova classe.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "criaçaõ concluída"),
            @ApiResponse(code = 400, message = "Erro na criação da classe")
    })
    @RequestMapping(value = "/inclusao-classe",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarClasse(@RequestBody ClasseEntity newClass) {
        try {

            newClass = classeService.inclusaoClasse(newClass);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Criação concluída")
                    .response(newClass).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
