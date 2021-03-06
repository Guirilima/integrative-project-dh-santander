package br.com.xrpg.controller;

import java.math.BigInteger;
import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/api/classe")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class ClasseController {

    @Autowired
    ClasseService classeService;

    @ApiOperation(value = "API RESPONSÁVEL POR LISTA TODAS AS CLASSES REGISTRADAS NO SISTEMA.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de classes encontrada."),
            @ApiResponse(code = 400, message = "Erro na listagem das classes.")
    })
    @RequestMapping(value = "/listar-classes",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> listarClassePersonagem() {
        try {

            Iterable<ClasseEntity> listaClassesPersonagem = classeService.getListaClasses();

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

    @ApiOperation(value = "API RESPONSÁVEL POR LISTAR TODAS AS CLASSES EXISTENTES NO SISTEMA SEM PAGINACAO.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "lista de classes encontrada sem paginacao."),
            @ApiResponse(code = 400, message = "Erro na listagem das classes.")
    })
    @RequestMapping(value = "/select-listar-classes",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> listarClasses() {
        try {

            List<ClasseEntity> listaClassesPersonagem = classeService.getListaClassesSemPaginacao();

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

    @ApiOperation(value = "API RESPONSÁVEL POR CRIAR UMA NOVA CLASSE.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "criaçaõ concluída"),
            @ApiResponse(code = 400, message = "Erro na criação da classe")
    })
    @RequestMapping(value = "/inclusao-classe",method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarClasse(@RequestBody ClasseEntity newClass) {
        try {

            newClass = classeService.inclusaoClasse(newClass);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClass.getIdClasse())
                    .toUri();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Criação concluída")
                    .response(uri).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API responsavel por encontrar Classe pelo ID.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Classe encontrada com sucesso."),
            @ApiResponse(code = 400, message = "Classe não encontrada") })
    @RequestMapping(value ="/{idClasse}",method = RequestMethod.GET, produces = "application/json")

    public ResponseEntity<HttpGenericResponse> encontrarClasse(@PathVariable BigInteger idClasse) {

        try {

            ClasseEntity resultado = classeService.encontrarPorId(idClasse);

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

    @ApiOperation(value = "API responsavel por excluir classe do sistema pelo ID.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Classe excluido sucesso."),
            @ApiResponse(code = 400, message = "Classe nao encontrada") })
    @RequestMapping(value ="/{idClasse}",method = RequestMethod.DELETE, produces = "application/json")

    public ResponseEntity<HttpGenericResponse> deletarClasse(@PathVariable BigInteger idClasse) {

        try {

            classeService.deletar(idClasse);

            return new ResponseEntity<HttpGenericResponse>(
                    new HttpGenericResponse().builder()
                            .status("OK")
                            .mensagem("Classe excluido com sucesso")
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
