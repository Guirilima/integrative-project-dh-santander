package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idPunicao",nullable = false)
    private BigInteger idPunicao;

    @Column(name = "idDenuncia",nullable = false)
    private BigInteger idDenuncia;

    @Column(name = "idUsuario",nullable = false)
    private BigInteger idUsuario;

    @Column(name = "flagPunicao",nullable = false)
    private String flagPunicao;             //B = Banido | S = Suspenso por 7 dias | N = NãoPunido

    @Column(name = "qtdPunicao",nullable = false)
    private BigInteger qtdPunicao;          // == 3 Banimento

    @Column(name = "dataPunicao",nullable = false)
    private Date dataPunicao;
}
