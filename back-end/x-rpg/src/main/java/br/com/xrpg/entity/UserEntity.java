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
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id", nullable = false)
	private BigInteger idUser;

	@Column(name = "active_flag")
	private BigInteger flagActive;

	@Column(name = "master_id_master")
	private BigInteger idMaster;

	@Column(name = "user_personal_iduser_personal", nullable = false)
	private BigInteger IdUserPersonal;

<<<<<<< Updated upstream
    @Column(name = "user_auth_iduser_auth",nullable = false)
    private BigInteger idUserAuth;
=======
	@Column(name = "user_auth_iduser_auth", nullable = false)
	private BigInteger idUserAuth;
>>>>>>> Stashed changes
}
