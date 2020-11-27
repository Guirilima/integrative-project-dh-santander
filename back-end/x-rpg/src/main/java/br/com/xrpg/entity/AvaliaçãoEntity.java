package br.com.xrpg.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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
@Table(name = "AVALIACAO")
public class AvaliaçãoEntity {
	
        @Id
        @Min(0)
        @PositiveOrZero
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "idAvaliacao",nullable = false)
        private BigInteger idAvaliação;

        @Column(name = "dataAvaliação",nullable = false)
        private Date dataAvaliação;

        @Min(0)
        @PositiveOrZero
        @Column (name = "idUsuarioAvaliou",nullable = false)
        private BigInteger idUsuarioAvaliou;

        @Min(0)
        @PositiveOrZero
        @Column (name = "idUsuario",nullable = false)
        private BigInteger idUsuario;

}
