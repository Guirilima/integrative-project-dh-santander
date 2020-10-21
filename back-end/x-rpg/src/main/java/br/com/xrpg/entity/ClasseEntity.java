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
@Table(name = "CLASSE")
public class ClasseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idClasse",nullable = false)
    private BigInteger idClasse;

    @Column(name = "nomeClasse",length = 44,nullable = false)
    private String nomeClasse;

    @Column(name = "descricaoClasse",length = 450,nullable = false)
    private String descricaoClasse;

}
