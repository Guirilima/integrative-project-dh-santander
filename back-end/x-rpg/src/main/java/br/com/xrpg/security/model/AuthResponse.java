package br.com.xrpg.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AuthResponse {

    private String jwt;

    private BigInteger idUsuario;

    private String email;
}
