package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Min(0)
    @PositiveOrZero
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idDenuncia",nullable = false)
    private BigInteger idDenuncia;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idUsuarioDenunciado",nullable = false)
    private BigInteger idUsuarioDenunciado;

    @Min(0)
    @PositiveOrZero
    @Column(name = "idUsuarioDenunciou",nullable = false)
    private BigInteger idUsuarioDenunciou;

    @NotNull
    @NotBlank
    @Column(name = "descDenuncia",nullable = false)
    private String descDenuncia;

    @Column(name = "dataDenuncia",nullable = false)
    private Date dataDenuncia;

    @NotNull
    @NotBlank
    @Column(name = "categoriaDenuncia",nullable = false)
    private String categoriaDenuncia;       //

    @Column(name = "flagDenunciaStatus",nullable = false)
    private String flagDenunciaStatus;      //R = Resolvido | E = EmAnalise | N = NãoVisualizado

}
