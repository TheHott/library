package com.evgensoft.dto.responses;

import java.time.LocalDate;

import com.evgensoft.entities.Author;
import com.evgensoft.entities.Book;

import lombok.Data;

@Data
public class BookResponseDTO {
	private Long id;
	private String name;
	private Author author;
	private Integer inStock; // сколько таких книг на складе (в библиотеке)
	private LocalDate releaseDate;
	
	public static BookResponseDTO fromEntity(Book book) {
		BookResponseDTO bookResp = new BookResponseDTO();
		
		bookResp.setId(book.getId());
		bookResp.setInStock(book.getInStock());
		bookResp.setName(book.getName());
		bookResp.setReleaseDate(book.getReleaseDate());
		bookResp.setAuthor(book.getAuthor());
		
		return bookResp;
	}
}
