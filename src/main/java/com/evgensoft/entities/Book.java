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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(nullable = false, name = "author_id")
	private Author author;

	@Column(nullable = false)
	private Integer inStock; // сколько таких книг на складе (в библиотеке)

	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd MMMM yyyy")
	private LocalDate releaseDate;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<TakenBook> takenBooks;
}
