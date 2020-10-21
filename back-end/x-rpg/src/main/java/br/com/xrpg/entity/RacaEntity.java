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
@Table(name = "RACA")
public class RacaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idRaca",nullable = false)
    private BigInteger idRaca;

    @Column(name = "nomeRaca",length = 44,nullable = false)
    private String nomeRaca;

    @Column(name = "descricaoRaca",length = 449,nullable = false)
    private String descricaoRaca;
}
