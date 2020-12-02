package br.com.xrpg.vo;

import lombok.*;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DadosUsuarioEnvioWhatsapp {

    private BigInteger idUsuario;

    private String nomeUsuario;

    private String urlApiWhatsapp;
}
