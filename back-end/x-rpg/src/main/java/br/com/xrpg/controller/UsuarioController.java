package br.com.xrpg.controller;

import br.com.xrpg.entity.UsuarioEntity;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.service.UsuarioService;
import br.com.xrpg.vo.DadosUsuarioVO;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ApiOperation(value = "API RESPONSÁVEL POR CRIAR UM NOVO USUÁRIO.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação concluída."),
            @ApiResponse(code = 400, message = "Erro durante o salvamento e/ou manipulação dos dados.")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarNovoUsuarioCadastro(@RequestBody DadosUsuarioVO dadosNewUser) {
        try {

            UsuarioEntity objectUsuario = usuarioService.criarNovoUsuarioCadastro(dadosNewUser);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objectUsuario.getIdUsuario())
                    .toUri();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Usuário criado com sucesso.")
                    .response(uri).build(), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR RETORNAR TODOS OS USUÁRIOS SEM DADOS SENSIVEIS.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuários encontrado."),
            @ApiResponse(code = 400, message = "Erro durante a buscar e/ou nenhum usuário encontrado.")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<HttpGenericResponse> getTodosUsuariosSemDadosSensiveis() {
        try {

            List<DadosUsuarioVO> objectsUsuarios = usuarioService.getTodosUsuariosSemDadosSensiveis();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(objectsUsuarios).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR BUSCAR UM USUÁRIO PELO ID DELE.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado."),
            @ApiResponse(code = 400, message = "Erro durante a buscar e/ou usuário não encontrado.")
    })
    @GetMapping(path = "/{idUsuario}" ,produces = "application/json")
    public ResponseEntity<HttpGenericResponse> getUsuarioPorId(@PathVariable("idUsuario") BigInteger idUsuario) {
        try {

            DadosUsuarioVO objectUsuario = usuarioService.getUsuarioPorId(idUsuario);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(objectUsuario).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR INATIVAR UM USUÁRIO PELO ID DELE.")
    @ApiParam(name = "idUsuario",value = "11",type = "BigInteger")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário inativado."),
            @ApiResponse(code = 400, message = "Erro durante a inativação e/ou usuário não encontrado.")
    })
    /**
     * NÂO SERÁ POSSIVEL A REMOÇÂO DE UM USUÁRIO, APENAS O INATIVAMENTO DELE POR FLAG
     * DESSA FORMA, GARANTIMOS SEGURANÇA NOSSA DE DADOS CASO OCORRA ALGUMA URGENCIA
     *
     * TEMPO DE INATIVAMENTO INDICADO = 7 MESES.
     */
    @DeleteMapping(path = "/{idUsuario}" ,produces = "application/json")
    public ResponseEntity<HttpGenericResponse> apagarUsuario(@PathVariable("idUsuario") BigInteger idUsuario) {
        try {

            DadosUsuarioVO objectUsuario = usuarioService.getUsuarioPorId(idUsuario);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(objectUsuario).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR EDITAR UM USUÁRIO PELO ID DELE.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário editado com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante a edição e/ou usuário não encontrado.")
    })
    @PutMapping(path = "/{idUsuario}" ,produces = "application/json")
    public ResponseEntity<HttpGenericResponse> editarUsuario(@PathVariable("idUsuario") BigInteger idUsuario,
                                                             @RequestBody DadosUsuarioVO dadosUsuario) {
        try {

            DadosUsuarioVO objectUsuario = usuarioService.editarDadosBasicosUsuario(idUsuario,dadosUsuario);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objectUsuario.getIdUsuario())
                    .toUri();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Edição concluída")
                    .response(uri).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

}
