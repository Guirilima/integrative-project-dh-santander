package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PUNICAO")
public class PunicaoEntity {

    @Id
    @Min(0)
    @PositiveOrZero
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idPunicao",nullable = false)
    private BigInteger idPunicao;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idDenuncia",nullable = false)
    private BigInteger idDenuncia;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idUsuario",nullable = false)
    private BigInteger idUsuario;

    @Column(name = "flagPunicao",nullable = false)
    private String flagPunicao;             //B = Banido | S = Suspenso por 7 dias | N = NãoPunido

    @Column(name = "qtdPunicao",nullable = false)
    private BigInteger qtdPunicao;          // == 3 Banimento

    @Column(name = "dataPunicao",nullable = false)
    private Date dataPunicao;
}
