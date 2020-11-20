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

@Slf4j
@RestController
@RequestMapping("/api/securanca")
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
    @PostMapping(produces = "application/json")
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
}
