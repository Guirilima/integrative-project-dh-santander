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
@Table(name = "character_entity") //Nome alterado de character para character_entity por motivo de DDL;
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "character_id",nullable = false)
    private BigInteger idCharacter;

    @Column(name = "name")
    private String nameCharacter;

    @Column(name = "race_id_race",nullable = false,length = 44)
    private BigInteger idRace;

    @Column(name = "class_id_class",nullable = false)
    private BigInteger idClass;

    @Column(name = "backstory",length = 449)
    private String backsStory;

    @Column(name = "user_user_id",nullable = false)
    private BigInteger idUser;


}
