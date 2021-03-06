package br.com.xrpg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CLASSE")
public class ClasseEntity {

    @Id
    @Min(0)
    @PositiveOrZero
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idClasse",nullable = false)
    private BigInteger idClasse;

    @NotNull
    @NotBlank
    @Column(name = "nomeClasse",length = 44,nullable = false)
    private String nomeClasse;

    @NotNull
    @NotBlank
    @Column(name = "descricaoClasse",length = 450,nullable = false)
    private String descricaoClasse;

}
