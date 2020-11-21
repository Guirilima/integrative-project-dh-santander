package br.com.xrpg.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GenericPageRequestResponse {

    private int pagina;

    private int tamanho;

    private long total;

    private int totalPaginas;

    private String ordenamento;
}
