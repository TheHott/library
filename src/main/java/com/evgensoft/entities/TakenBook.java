package com.evgensoft.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class TakenBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(nullable = false, name = "reader_id")
	private Reader reader;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd MMMM yyyy")
	private LocalDate dateOfTaking;
}
