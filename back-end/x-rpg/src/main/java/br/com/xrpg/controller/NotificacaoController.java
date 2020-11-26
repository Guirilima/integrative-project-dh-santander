package br.com.xrpg.controller;

import br.com.xrpg.entity.MestreEntity;
import br.com.xrpg.entity.NotificacaoEntity;
import br.com.xrpg.service.NotificacaoService;
import br.com.xrpg.vo.HttpGenericPageableResponse;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/notificacao")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
@AllArgsConstructor
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @ApiOperation(value = "API RESPONSÁVEL POR CRIAR UMA NOTIFICACAO.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notificacao Criado com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante o salvamento e/ou manipulação dos dados.")
    })

    @PostMapping()
    public ResponseEntity<HttpGenericResponse> create(@RequestBody NotificacaoEntity newNotificacaoEntity) {
        try {
            //return ResponseEntity.created(uri).build();
            NotificacaoEntity notificacaoCreated = this.notificacaoService.criar(newNotificacaoEntity);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idNotificacao}").buildAndExpand(notificacaoCreated.getIdNotificacao())
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

    @ApiOperation(value = "API RESPONSÁVEL POR EDITAR UM NOTIFICACAO POR ID.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notificacao atualizada com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante a edição e/ou manipulação dos dados.")
    })
    @PutMapping("/{idNotificacao}")
    public ResponseEntity<HttpGenericResponse> update(@PathVariable("idNotificacao") BigInteger idNotificacao,
                                                      @RequestParam(value = "newFlagSituacao",required = false,defaultValue = "") String newFlagSituacao,
                                                      @RequestParam(value = "newDescricao",required = false,defaultValue = "") String newDescricao) {
        try {

            this.notificacaoService.editarFlagOuDesc(idNotificacao,newFlagSituacao,newDescricao);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Atualizado com sucesso.")
                    .response(null).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR RETORNAR UM NOTIFICACAO POR ID.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Notificacao retornado."),
            @ApiResponse(code = 400, message = "Erro durante a busca e/ou Notificacao não encontrado.")
    })

    @GetMapping("/{idNotificacao}")
    public ResponseEntity<HttpGenericResponse> findById(@PathVariable BigInteger idNotificacao) {
        try {

            NotificacaoEntity newNotificação = this.notificacaoService.buscarPorId(idNotificacao);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(newNotificação).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "API RESPONSÁVEL POR TRAZER TODOS OS NOTIFICACAO DO USUARIO CONVIDADO.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno com todos os notificações."),
            @ApiResponse(code = 400, message = "Erro durante a busca e/ou notificacao não encontrado.")
    })
    @GetMapping("/usuarioConvidado/{idUsuarioConvidado}")
    public ResponseEntity<HttpGenericResponse> findAllConvidado(@PathVariable BigInteger idNotificacaoConvidado,
                                                       @RequestParam(value = "pagina",defaultValue = "0",required = false) int pagina,
                                                       @RequestParam(value = "qtdPagina",defaultValue = "6",required = false) int qtdPagina) {
        try {

            HttpGenericPageableResponse pageAllNotificacao = this.notificacaoService.listarTodosPorUsuarioConvidado(idNotificacaoConvidado, pagina, qtdPagina);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(pageAllNotificacao).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR TRAZER TODOS OS NOTIFICACAO DO USUARIO QUE CONVIDOU.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno com todos os notificações."),
            @ApiResponse(code = 400, message = "Erro durante a busca e/ou notificacao não encontrado.")
    })
    @GetMapping("/usuarioConvidou/{idUsuarioConvidou}")
    public ResponseEntity<HttpGenericResponse> findAllConvidou(@PathVariable BigInteger idNotificacaoConvidou,
                                                       @RequestParam(value = "pagina",defaultValue = "0",required = false) int pagina,
                                                       @RequestParam(value = "qtdPagina",defaultValue = "6",required = false) int qtdPagina) {
        try {

            HttpGenericPageableResponse pageAllNotificacao = this.notificacaoService.listarTodosPorUsuarioConvidou(idNotificacaoConvidou,pagina,qtdPagina);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(pageAllNotificacao).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR DELETAR UM NOTIFICACAO.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Notificação deletado com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante a deletação e/ou Notificação não encontrado.")
    })
    @DeleteMapping("/{idNotificacao}")
    public ResponseEntity<HttpGenericResponse> deleteById(@PathVariable BigInteger idNotificacao){
        try {

            this.notificacaoService.deletar(idNotificacao);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Notificação deletado.")
                    .response(null).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }

    }
}
