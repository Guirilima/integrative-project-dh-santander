package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ESTADO")
public class EstadoEntity {

    @Id
    @Column(name = "CD_ESTADO_EST",nullable = false)
    private String codigoEstado;

    @Column(name = "DS_ESTADO_EST",nullable = false)
    private String descricaoEstado;

    @Column(name = "DS_CAPITAL_EST",nullable = false)
    private String descricaoCapital;
}
