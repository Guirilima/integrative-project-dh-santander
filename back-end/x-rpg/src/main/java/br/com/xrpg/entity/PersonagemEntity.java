package br.com.xrpg.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "PERSONAGEM")
public class PersonagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_PERSONAGEM",nullable = false)
    private BigInteger idPersonagem;

    @Column(name = "ID_USUARIO_USU",length = 50,nullable = false)
    private BigInteger idUsuario;

    @Column(name = "NM_PERSONAGEM",nullable = false)
    private String nomePersonagem;

    @Column(name = "NIVEL_PERSONAGEM",nullable = false)
    private String nivelPersonagem;

    @Column(name = "HISTORIA_PERSO")
    private String historiaPersonagem;

    @Column(name = "CLASSE_PERSO",nullable = false)
    private String classePersonagem;

    @Column(name = "GAMEMODE_PERSO")
    private String gameModePersonagem;

    @Column(name = "RACA_PERSONAGEM",nullable = false)
    private String racaPersonagem;

    @Column(name = "RATING_PERSONAGEM")
    private BigInteger ratingPersonagem;

}
