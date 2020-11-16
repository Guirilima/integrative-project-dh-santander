package br.com.xrpg.controller;

import br.com.xrpg.service.AdministradorService;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @ApiOperation(value = "API RESPONSÁVEL POR BUSCAR DADOS PARA O PREENCHIMENTO DO DASHBOARD ADMIN.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "buscar conclúida"),
            @ApiResponse(code = 400, message = "Erro na busca dos dados.")
    })
    @RequestMapping(value = "/dados-dashboard",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpGenericResponse> getDadosDashBoardAdmin() {
        try {

            log.debug("INICIANDO BUSCAR PELOS DADOS PREENCHIMENTO DASHBOARD ADMIN");
            HashMap<String,Object> dadosHashBoard = administradorService.getResumoDashBoardAdm();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Dados encontrado.")
                    .response(dadosHashBoard).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
