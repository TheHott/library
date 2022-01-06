package com.evgensoft.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String fullName;
	
	@ManyToOne // manyToOne - Many - класс который пишешь, One - класс который вписываешь. Здесь это значит что много авторов могут быть из одной страны
	@JoinColumn(nullable = false, name = "birth_country_id")
	private Country birthCountry;
	
	@Column(nullable = false)
	private LocalDate birthday;
	
	private LocalDate deathDate;

}
