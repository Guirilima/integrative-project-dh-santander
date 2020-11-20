package br.com.xrpg.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.xrpg.enumber.TipoUsuarioEnum;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUsuario", nullable = false)
	private BigInteger idUsuario;

	@Column(name = "flagAtivo", nullable = false)
	private BigInteger flagAtivo;						//0 = false | 1 = true

	@Column(name = "idMestre")
	private BigInteger idMestre;

	@Column(name = "ultimoLogin",nullable = false)
	private Date dataUltimoLogin;


	@Column(name = "tipoUsuario",nullable = false)
	private BigInteger tipoUsuarioEnum;



	@Column(name = "nomePessoal",nullable = false)
	private String nomePessoal;

	@Column(name = "sobrenomePessoal")
	private String sobrenomePessoal;

	@Column(name = "estado",nullable = false,length = 2)
	private String estadoPessoal;

	@Column(name = "cidade",nullable = false)
	private String cidadePessoal;

	@Column(name = "cpfPessoal",nullable = false,length = 20)
	private String cpfPessoal;

	@Column(name = "dataNascimento", nullable = false )
	private Date dataNascimento;

	@Column(name = "telefonePessoal", nullable = false,length = 14)
	private String telefone;

	@Column(name = "genero", nullable = false,length = 1) //F=Feminino/M=Masculino/O=Outro
	private String genero;



	@Column(name = "emailUsuAutenticacao",nullable = false)
	private String emailUsuAutenticacao;


	@Column(name = "usuarioAutenticacao",nullable = false)
	private BigInteger idUsuarioAutenticacao;

}
