package br.com.xrpg.security;

import br.com.xrpg.entity.Role;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.xrpg.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long exp;

    public String generateToken(UsuarioEntity usuario) {

        List<String> listaNomes = usuario.getRoles().stream().map( r -> r.getName()).collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("roles", listaNomes);
        claims.put("usrid", usuario.getIdUsuario());

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .addClaims(claims)
                .compact();
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if(claims != null) {
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return  now != null && now.before(expirationDate);
        }
        return false;
    }

    public UsuarioEntity getUser(String token) {
        Claims claims = this.getClaims(token);

        BigInteger usrid = BigInteger.valueOf(claims.get("usrid", Integer.class));
        List<String> roles = claims.get("roles", List.class);
        String subject = claims.getSubject();

        return UsuarioEntity.builder()
                .idUsuario(usrid)
                .roles(roles.stream().map( s -> Role.builder().name(s).build()).collect(Collectors.toSet()))
                .username(subject)
                .build();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch(Exception e) {
            return null;
        }

    }
}
