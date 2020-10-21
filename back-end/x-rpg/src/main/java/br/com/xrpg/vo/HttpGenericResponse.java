package br.com.xrpg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpGenericResponse {

    @ApiModelProperty(value = "status", example = "OK/NOK")
    private String status;      //Status de retorno: OK / NOK

    @ApiModelProperty(value = "mensagem", example = "Mensagem de retorno de acordo com o status")
    private String mensagem;    //Mensagem de retorno para status NOK

    @ApiModelProperty(value = "response", example = "null ou Object da API")
    private Object response;    //Objeto de retorno
}
