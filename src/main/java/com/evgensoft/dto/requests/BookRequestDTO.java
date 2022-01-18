package com.evgensoft.dto.requests;

import java.time.LocalDate;

import com.evgensoft.entities.Author;
import com.evgensoft.entities.Book;

import lombok.Data;

@Data
public class BookRequestDTO {
	private Long id;
	private String name;
	private Author author;
	private Integer inStock; // сколько таких книг на складе (в библиотеке)
	private LocalDate releaseDate;
	private String authorName; // TODO костыль чтоб вводить имя автора без вывода списка авторов
	
	public static Book toEntity(BookRequestDTO bookReq) {
		Book book = new Book();
		
		book.setId(bookReq.getId());
		book.setInStock(bookReq.getInStock());
		book.setName(bookReq.getName());
		book.setReleaseDate(bookReq.getReleaseDate());
		book.setAuthor(bookReq.getAuthor());
		
		return book;
	}
}
