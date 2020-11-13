package br.com.xrpg.controller;

import br.com.xrpg.entity.DenunciasEntity;
import br.com.xrpg.service.DenunciaService;
import br.com.xrpg.vo.DenunciaVO;
import br.com.xrpg.vo.HttpGenericResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/denuncias")
@CrossOrigin(origins = "*") //Liberando acesso para todos os dominios acessarem
public class DenunciaController {

    @Autowired
    DenunciaService denunciaService;

    @ApiOperation(value = "API RESPONSÁVEL POR CRIAR UM DENUNCIA.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Denuncia Criado com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante o salvamento e/ou manipulação dos dados.")
    })

    @PostMapping()
    public ResponseEntity<HttpGenericResponse> create(@RequestBody DenunciasEntity newDenuncia) {
        try {

            newDenuncia = denunciaService.create(newDenuncia);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Criação concluída")
                    .response(newDenuncia).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR EDITAR UM DENUNCIA POR ID.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Denuncia Criado com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante a edição e/ou manipulação dos dados.")
    })
    @PutMapping("/{idDenuncia}")
    public ResponseEntity<HttpGenericResponse> update(@PathVariable("idDenuncia") BigInteger idDenuncia, @RequestBody DenunciasEntity denunciaEntity) {
        try {

            denunciaEntity.setIdDenuncia(idDenuncia);

            denunciaEntity = denunciaService.update(denunciaEntity);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Update efetuado com sucesso.")
                    .response(denunciaEntity).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR RETORNAR UMA DENUNCIA POR ID.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Denuncia retornado."),
            @ApiResponse(code = 400, message = "Erro durante a busca e/ou denuncia não encontrado.")
    })

    @GetMapping("/{idDenuncia}")
    public ResponseEntity<HttpGenericResponse> findById(@PathVariable BigInteger idDenuncia) {
        try {

            DenunciasEntity denunciasEntity = denunciaService.findById(idDenuncia);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(denunciasEntity).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "API RESPONSÁVEL POR TRAZER TODAS AS DENUNCIAS.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorno com todas as denuncias."),
            @ApiResponse(code = 400, message = "Erro durante a busca e/ou denuncia não encontrado.")
    })
    @GetMapping
    public ResponseEntity<HttpGenericResponse> findAll() {
        try {

            Iterable<DenunciasEntity> listaDenuncias = denunciaService.findAll();

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(listaDenuncias).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "API RESPONSÁVEL POR DELETAR UMA DENUNCIA.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Denuncia deletado com sucesso."),
                            @ApiResponse(code = 400, message = "Erro durante a deletação e/ou denuncia não encontrado.")
    })
    @DeleteMapping("/{idDenuncia}")
    public ResponseEntity<HttpGenericResponse> deleteById(@PathVariable BigInteger idDenuncia){
        try {


            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("Denuncia deletado.")
                    .response(null).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation(value = "API RESPONSÁVEL POR BUSCAR DENUNCIAS POR VARIOS FILTROS.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Denuncia encontrada com sucesso."),
            @ApiResponse(code = 400, message = "Erro durante a busca e/ou manipulação dos dados.")
    })

    @PostMapping("buscar-denuncias")
    public ResponseEntity<HttpGenericResponse> getDenunciasPorFiltros(@RequestBody DenunciaVO denunciaVO) {
        try {

            List<DenunciasEntity> denuncias = denunciaService.getDenunciaByFiltro(denunciaVO);

            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("OK")
                    .mensagem("")
                    .response(denuncias).build(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<HttpGenericResponse>(new HttpGenericResponse().builder()
                    .status("NOK")
                    .mensagem(e.getMessage())
                    .response(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
