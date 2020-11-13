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
@Table(name = "DENUNCIAS")
public class DenunciasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idDenuncia",nullable = false)
    private BigInteger idDenuncia;

    @Column(name = "idUsuarioDenunciado",nullable = false)
    private BigInteger idUsuarioDenunciado;

    @Column(name = "idUsuarioDenunciou",nullable = false)
    private BigInteger idUsuarioDenunciou;

    @Column(name = "descDenuncia",nullable = false)
    private String descDenuncia;

    @Column(name = "dataDenuncia",nullable = false)
    private Date dataDenuncia;

    @Column(name = "categoriaDenuncia",nullable = false)
    private String categoriaDenuncia;       //

    @Column(name = "flagDenunciaStatus",nullable = false)
    private String flagDenunciaStatus;      //R = Resolvido | E = EmAnalise | N = NãoVisualizado

}
