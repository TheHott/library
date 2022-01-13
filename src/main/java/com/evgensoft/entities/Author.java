package com.evgensoft.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullName;

	// manyToOne. Здесь это значит что много авторов могут быть из одной страны
	@ManyToOne
	@JoinColumn(nullable = false, name = "birth_country_id")
	private Country birthCountry;

	@Column(nullable = false)
	private LocalDate birthday;

	private LocalDate deathDate;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books;

}
