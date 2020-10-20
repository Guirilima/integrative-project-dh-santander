package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id",nullable = false)
    private BigInteger idUser;

    @Column(name = "active_flag")
    private BigInteger flagActive;

    @Column(name = "master_id_master")
    private BigInteger idMaster;

    @Column(name = "user_personal_iduser_personal",nullable = false)
    private BigInteger IdUserPersonal;

    @Column(name = "user_auth_iduser_auth",nullable = false)
    private BigInteger idUserAuth;
    
    @OneToOne
    @JoinColumn(name = "master_id", nullable = false, unique = true)
    private MasterEntity master;
}
