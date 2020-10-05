package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_auth")
public class UserAuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iduser_auth",nullable = false)
    private BigInteger idUserAuth;

    @Column(name = "email",length = 44)
    private String emailUser;

    @Column(name = "pass",length = 44)
    private String passwordUser;

    @Column(name = "active_flag")      //tinynt | 0 = false / > 1 = true
    private BigInteger flagActiveUser;

}
