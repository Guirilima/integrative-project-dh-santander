package br.com.xrpg.controller;

import br.com.xrpg.entity.UsuarioAutenticacao;
import br.com.xrpg.repository.UsuarioAutenticacaoRepository;
import br.com.xrpg.vo.Credential;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.service.MetodosValidadores;
import br.com.xrpg.vo.HttpGenericResponse;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
@RestController
@RequestMapping("/api/seguranca")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
@AllArgsConstructor
public class SegurancaController {

    private final MetodosValidadores metodosValidadores;

    private final UsuarioAutenticacaoRepository usuarioDetailsRepository;

    private final BCryptPasswordEncoder pEnconder;


    @ApiOperation(value = "API TESTE PARA CRIAÇÂO DO USUARIO COM SPRING SECURITY")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Criação concluída."),
            @ApiResponse(code = 400, message = "Erro durante o salvamento e/ou manipulação dos dados.")
    })
    @PostMapping(path = "/efetuar-login",produces = "application/json")
    public ResponseEntity<HttpGenericResponse> criarNovoUsuarioCadastro(@RequestBody UsuarioAutenticacao newUsuario) {
        try {

            newUsuario.setSenha( this.pEnconder.encode(newUsuario.getSenha()) );

            usuarioDetailsRepository.save(newUsuario);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Usuário criado com sucesso.")
                    .response( newUsuario ).build(), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem("Erro durante o Salvamento do novo Usúario: " + e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÀVEL POR EFETUAR O LOGIN NO FRONT-END")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Combinação perfeita."),
            @ApiResponse(code = 400, message = "Erro na combinação recebida.")
    })
    @PostMapping("/login")
    public ResponseEntity<HttpGenericResponse> login( @RequestBody Credential credential) throws ObjectNotFoundException {

        try {
            BigInteger idUsuario = this.metodosValidadores.validarDadosLogin(credential);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Combinação bem sucedida.")
                    .response(idUsuario).build(), HttpStatus.OK);
        }catch (Exception ee) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(ee.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
