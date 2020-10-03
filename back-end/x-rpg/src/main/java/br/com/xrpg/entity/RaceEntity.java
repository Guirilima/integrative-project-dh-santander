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
@Table(name = "race")
public class RaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "race_id",nullable = false)
    private BigInteger idRace;

    @Column(name = "name",length = 44)
    private String nameClass;

    @Column(name = "description",length = 449)
    private String descriptionClass;
}
