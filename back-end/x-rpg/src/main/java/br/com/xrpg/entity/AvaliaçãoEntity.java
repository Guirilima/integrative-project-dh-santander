package br.com.xrpg.entity;

import java.math.BigInteger;
import java.util.Date;

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
@Table(name = "AVALIACAO")
public class AvaliaçãoEntity {
	
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "character_id",nullable = false)
        private BigInteger idAvaliação;

        @Column(name = "dataAvaliação",nullable = false)
        private Date dataAvaliação;

        //@Column(name = "time")
        //private String time;

        @Column (name = "idUsuarioAvaliou",nullable = false)
        private BigInteger idUsuarioAvaliou;

        @Column (name = "idUsuario",nullable = false)
        private BigInteger idUsuario;

}
