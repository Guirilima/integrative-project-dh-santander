package br.com.xrpg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PERSONAGEM") //Nome alterado de character para character_entity por motivo de DDL;
public class PersonagemEntity {

    @Id
    @Min(0)
    @PositiveOrZero
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idPersonagem",nullable = false)
    private BigInteger idPersonagem;

    @NotNull
    @NotBlank
    @Column(name = "nomePersonagem",nullable = false)
    private String nomePersonagem;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idRaca",nullable = false)
    private BigInteger idRaca;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idClasse",nullable = false)
    private BigInteger idClasse;

    @NotNull
    @NotBlank
    @Column(name = "historiaPersonagem",length = 450,nullable = false)
    private String historiaPersonagem;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idUsuario",nullable = false)
    private BigInteger idUsuario;

}
