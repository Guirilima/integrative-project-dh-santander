package br.com.xrpg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpGenericResponse {

    private String status;      //Status de retorno: OK / NOK

    private String mensagem;    //Mensagem de retorno para status NOK

    private Object response;    //Objeto de retorno
}
