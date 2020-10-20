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
    @Column(name = "idUsuarioPessoal",nullable = false)
    private BigInteger idUsuarioPessoal;

    @Column(name = "nome",nullable = false)
    private String nomePessoal;

    @Column(name = "sobrenome")
    private String sobrenomePessoal;

    @Column(name = "estado",nullable = false)
    private String estadoPessoal;

    @Column(name = "cidade",nullable = false)
    private String cidadePessoal;

    @Column(name = "cpf",nullable = false)
    private String cpfPessoal;
    
    @Column(name = "dataNascimento", nullable = false )
    private Date dataNascimento;
    
    @Column(name = "telefone", nullable = false)
    private String telefone;
    
    @Column(name = "genero", nullable = false) //F=Feminino/M=Masculino/P=prefiro não responde
    private String genero;
    
}
