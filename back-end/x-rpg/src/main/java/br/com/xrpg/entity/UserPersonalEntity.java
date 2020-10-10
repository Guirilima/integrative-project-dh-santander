package br.com.xrpg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_personal")
public class UserPersonalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iduser_personal",nullable = false)
    private BigInteger idUserPersonal;

    @Column(name = "nome",nullable = false)
    private String nomePersonal;

    @Column(name = "sobrenome")
    private String sobrenomePersonal;

    @Column(name = "state",nullable = false)
    private String statePersonal;

    @Column(name = "city",nullable = false)
    private String cityPersonal;

    @Column(name = "cpf",nullable = false)
    private String cpfPersonal;
    
    @Column(name = "data de nascimento", nullable = false )
    private Date dateOfBirth;
    
    @Column(name = "telefone", nullable = false)
    private String phoneNumber;
    
    @Column(name = "genero", nullable = false)
    private char gender;
    
}
