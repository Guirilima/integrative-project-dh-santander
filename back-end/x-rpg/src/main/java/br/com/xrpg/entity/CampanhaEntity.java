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
@Table(name="CAMPANHA")
public class CampanhaEntity {
	
	@Id
	@Min(0)
	@PositiveOrZero
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "IdCampanha",nullable = false)
	private BigInteger idCampanha;
	
	@Column(name = "statusCampanha", nullable = false)
	private boolean campanhaAtiva;

	@NotNull
	@NotBlank
	@Column(name = "titulo", nullable = false)
	private String tituloCampanha;

	@NotNull
	@NotBlank
	@Column(name = "historia",nullable = false, length = 1000)
	private String historiaCampanha;
	
	@Column(name = "organizadores")
	private String organizadores;

	@Column(name = "DataEncontro")
	private String dataEncontro;
}

