package br.com.xrpg.controller;

import br.com.xrpg.entity.RaceEntity;
import br.com.xrpg.entity.UserEntity;
import br.com.xrpg.service.MetodosValidadores;
import br.com.xrpg.service.RaceService;
import br.com.xrpg.service.UserService;
import br.com.xrpg.vo.DadosUsuarioCadastramentoVO;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MetodosValidadores metodosValidadores;

    @ApiOperation(value = "API responsavel por criar um novo usuario pelo cadastramento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "criação concluída."),
            @ApiResponse(code = 400, message = "Erro durante o salvamento e/ou manipulação dos dados.")
    })
    //@RequestMapping(value = "/cadastro-criar-usuario",method = RequestMethod.POST, produces = "application/json")
    @PostMapping(path = "/cadastro-criar-usuario",produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarNovoUsuarioCadastro(@RequestBody DadosUsuarioCadastramentoVO dadosNewUser) {
        try {

            UserEntity newUser = userService.criarNovoUsuarioCadastro(dadosNewUser);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(newUser).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/codificPass",produces = "application/json")
    public ResponseEntity<HttpGenericResponse> validacaoPassword(@RequestParam("value") String value,
                                                                 @RequestParam("qual") String qual) {
        try {

            if (qual.equals("1")) {
                value = metodosValidadores.criptografarString(value);
            }else {
                value = metodosValidadores.descriptografarString(value);
            }

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(value).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

}