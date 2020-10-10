package br.com.xrpg.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "availability")

public class AvailabilityEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Column(name = "character_id",nullable = false)
private BigInteger idAvailability;

@Column(name = "day_week")
private String weekDay;

@Column(name = "time")
private String time;

@Column (name = "id_user")
private BigInteger idUser;

}
