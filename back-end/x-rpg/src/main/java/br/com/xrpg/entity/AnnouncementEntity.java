package br.com.xrpg.entity;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "announcement")


public class AnnouncementEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "announcement_id",nullable = false)
    private BigInteger idAnnouncement;
	
	@Column(name = "author_name")
	private String authorName;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "announcement_date")
	private Date announcementDate;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "complete_content")
	private Blob completeContent;
	
	@Column(name = "category")
	private String category;
	
	

}
