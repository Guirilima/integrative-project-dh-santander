package br.com.xrpg.entity;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NOTICIAS")
public class NoticiasEntity {
	
	@Id
	@Min(0)
	@PositiveOrZero
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idNoticias",nullable = false)
    private BigInteger idNoticias;

	@NotNull
	@NotBlank
	@Column(name = "autorNome",nullable = false)
	private String autorNome;

	@NotNull
	@NotBlank
	@Column(name = "resumoNoticia",nullable = false)
	private String resumoNoticia;

	@Column(name = "dataNoticia",nullable = false)
	private Date dataNoticia;

	@NotNull
	@NotBlank
	@Column(name = "tituloNoticia",nullable = false)
	private String tituloNoticia;

	@NotNull
	@NotBlank
	@Column(name = "conteudoCompleto",nullable = false)
	private String conteudoCompleto;

	@NotNull
	@NotBlank
	@Column(name = "categoriaNoticia")
	private String categoriaNoticia;

}
