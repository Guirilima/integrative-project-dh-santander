package br.com.xrpg.controller;

import br.com.xrpg.entity.UsuarioAutenticacao;
import br.com.xrpg.repository.UsuarioAutenticacaoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @PostMapping(produces = "application/json")
    public ResponseEntity<HttpGenericResponse> efetuarLogin(@RequestParam("email") String email,
                                                            @RequestParam("senha") String senha) {
        try {

            BigInteger idUsuario = metodosValidadores.validarDadosLogin(email, senha);

            if (idUsuario != null) {
                return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                        .status("OK")
                        .mensagem("Combinação bem sucedida.")
                        .response(idUsuario).build(), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                        .status("NOK")
                        .mensagem("Combinação invalida.")
                        .response(null).build(), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem("Erro durante o Salvamento do novo Usúario: " + e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
