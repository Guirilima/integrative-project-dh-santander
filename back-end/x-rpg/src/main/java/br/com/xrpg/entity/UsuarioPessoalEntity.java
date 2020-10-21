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
@Table(name = "USUARIO_PESSOAL")
public class UsuarioPessoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idUsuarioPessoal",nullable = false)
    private BigInteger idUsuarioPessoal;

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
    
}
