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
@Table(name = "USUARIO")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "idUsuario", nullable = false)
	private BigInteger idUsuario;

	@Column(name = "flagAtivo", nullable = false)
	private BigInteger flagAtivo;						//0 = false | 1 = true

	@Column(name = "idMestre")
	private BigInteger idMestre;

	@Column(name = "idUsuarioPessoal", nullable = false)
	private BigInteger idUsuarioPessoal;

    @Column(name = "idUsuarioAutenticacao",nullable = false)
    private BigInteger idUsuarioAutenticacao;

	@Column(name = "ultimoLogin",nullable = false)
	private Date dataUltimoLogin;

}
