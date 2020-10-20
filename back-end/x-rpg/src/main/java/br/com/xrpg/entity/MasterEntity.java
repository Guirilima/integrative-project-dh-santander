package br.com.xrpg.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

public class MasterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_master", nullable = false)
	@EqualsAndHashCode.Include
	private BigInteger id;

	@Column(name = "anos_experiencia", nullable = false)
	private Integer yearsExperience;

	@Column(name = "campanhas_mestradas", nullable = false)
	private Integer campanhasMestradas;

	@OneToOne(mappedBy = "master", cascade = CascadeType.ALL)
	@JsonIgnore
	private UserEntity user;

}
