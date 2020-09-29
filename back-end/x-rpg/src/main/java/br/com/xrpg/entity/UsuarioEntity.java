package br.com.xrpg.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_USUARI_USU")
    private BigInteger idUsuario;

    @Column(name = "NOME_USUARIO",nullable = false)
    private String nomeUsuario;

    @Column(name = "CIDADE",nullable = false)
    private String cidadeUsuario;

    @Column(name = "CODIGO_ESTADO",nullable = false)
    private String codigoEstadoUsuario;

    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @Column(name = "TELEFONE_USU")
    private String telefoneUsuario;

    @Column(name = "SENHA_USU",nullable = false)
    private String senhaUsuario;

    @Column(name = "TIPO_USU",nullable = false)
    private BigInteger tipoUsuario;

    @Column(name = "BLOCKS_USU")
    private String blocksUsuarios;

    @Column(name = "EMAIL_USU")
    private String emailUsuario;

    @Column(name = "FLAG_ATIVO_USU",nullable = false)
    private String flagAtivoUsuario;
}
