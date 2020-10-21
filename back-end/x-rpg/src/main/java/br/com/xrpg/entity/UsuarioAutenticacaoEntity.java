package br.com.xrpg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO_AUTENTICACAO")
public class UsuarioAutenticacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idUsuarioAutenticacao",nullable = false)
    private BigInteger idUsuarioAutenticacao;

    @Column(name = "emailUsuAutenticacao",length = 44,nullable = false)
    private String emailUsuAutenticacao;

    @Column(name = "senhaUsuAutenticacao",length = 44,nullable = false)
    private String senhaUsuAutenticacao;

}
