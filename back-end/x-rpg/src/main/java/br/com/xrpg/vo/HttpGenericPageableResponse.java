package br.com.xrpg.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HttpGenericPageableResponse {

    private GenericPageRequestResponse pageRequestResponse;

    private Object data;
}
