package br.com.xrpg.controller;

import br.com.xrpg.entity.ClassEntity;
import br.com.xrpg.service.ClassService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
@Slf4j
public class ClassController {

    @Autowired
    ClassService classService;

    @ApiOperation(value = "API responsavel por lista todas as classes registradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de classes encontrada."),
            @ApiResponse(code = 400, message = "Erro na listagem das classes.")
    })
    @RequestMapping(value = "/listar-classes",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> listarClassePersonagem() {
        try {

            List<ClassEntity> listaClassesPersonagem = classService.getListaClasses();

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
    public ResponseEntity<HttpGenericResponse> criarClasse(@RequestBody ClassEntity newClass) {
        try {

            newClass = classService.inclusaoClasse(newClass);

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
