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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Table(name = "MESTRE")
public class MestreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "idMestre", nullable = false)
	@EqualsAndHashCode.Include
	private BigInteger idMestre;

	@Column(name = "anosExperiencia", nullable = false)
	private BigInteger anosExperiencia;

	@Column(name = "campanhasMestradas", nullable = false)
	private BigInteger campanhasMestradas;

}