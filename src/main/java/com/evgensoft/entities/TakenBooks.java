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
public class TakenBooks {
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
	private LocalDate dateOfTaking;
}
