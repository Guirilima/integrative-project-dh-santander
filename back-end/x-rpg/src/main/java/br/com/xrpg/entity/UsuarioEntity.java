package br.com.xrpg.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity {

	@Id
	@Min(0)
	@PositiveOrZero
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUsuario", nullable = false)
	private BigInteger idUsuario;

	@Min(0)
	@Max(1)
	@PositiveOrZero
	@Column(name = "flagAtivo", nullable = false)
	private BigInteger flagAtivo;						//0 = false | 1 = true

	@Min(0)
	@PositiveOrZero
	@Column(name = "idMestre")
	private BigInteger idMestre;

	@Column(name = "ultimoLogin",nullable = false)
	private Date dataUltimoLogin;

	@NotNull
	@NotBlank
	@Column(name = "nomePessoal",nullable = false)
	private String nomePessoal;

	@NotNull
	@NotBlank
	@Column(name = "sobrenomePessoal")
	private String sobrenomePessoal;

	@NotNull
	@NotBlank
	@Column(name = "estado",nullable = false,length = 2)
	private String estadoPessoal;

	@NotNull
	@NotBlank
	@Column(name = "cidade",nullable = false)
	private String cidadePessoal;

	@NotNull
	@NotBlank
	@CPF
	@Column(name = "cpfPessoal",nullable = false,length = 20)
	private String cpfPessoal;

	@Column(name = "dataNascimento", nullable = false )
	private Date dataNascimento;

	@NotNull
	@NotBlank
	@Column(name = "telefonePessoal", nullable = false,length = 14)
	private String telefone;

	@NotNull
	@NotBlank
	@Column(name = "genero", nullable = false,length = 1) //F=Feminino/M=Masculino/O=Outro
	private String genero;

	@Email
	@NotNull
	@NotBlank
	@Column(name = "emailUsuario",nullable = false)
	private String emailUsuario;

	@NotNull
	@NotBlank
	@Column( nullable = false)
	private String username;

	@NotNull
	@NotBlank
	@Column( nullable = false)
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns  = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEntity other = (UsuarioEntity) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}


	public List<String> getSimpleRoles() {
		return this.roles.stream().map(role -> role.getName()).collect(Collectors.toList());
	}
}
