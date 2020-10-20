package br.com.xrpg.entity;

import java.math.BigInteger;
import java.sql.Date;

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
    
    @Column(name = "data_de_nascimento", nullable = false )
    private Date dateOfBirth;
    
    @Column(name = "telefone", nullable = false)
    private String phoneNumber;
    
    @Column(name = "genero", nullable = false)
    private String gender;
    
}
