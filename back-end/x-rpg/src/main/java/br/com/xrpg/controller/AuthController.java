package br.com.xrpg.controller;

import br.com.xrpg.security.model.AuthResponse;
import br.com.xrpg.security.model.Credential;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.xrpg.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/seguranca")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final BCryptPasswordEncoder pEnconder;

    @ApiOperation(value = "API RESPONSÀVEL POR EFETUAR O LOGIN NO FRONT-END")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Combinação perfeita."),
            @ApiResponse(code = 400, message = "Erro na combinação recebida.")
    })
    @PostMapping("/loginJWT")
    public ResponseEntity<AuthResponse> loginJWT(@RequestBody Credential credential){

        AuthResponse login = this.authService.login(credential);

        return ResponseEntity.ok(login);
    }
}
