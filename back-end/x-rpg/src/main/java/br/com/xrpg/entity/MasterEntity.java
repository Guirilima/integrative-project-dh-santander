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
@Entity
@Table(name = "master")



public class MasterEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idMaster",nullable = false)
    private BigInteger idMaster;

    @Column(name = "anos_experiencia",nullable = false)
    private Integer yearsExperience;
    
    @Column(name = "campanhas_mestradas",nullable = false)
    private Integer campanhasMestradas;
	
	

}
