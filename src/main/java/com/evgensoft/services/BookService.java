package com.evgensoft.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.evgensoft.dto.requests.BookRequestDTO;
import com.evgensoft.entities.Book;

public interface BookService {
	Long createBook(BookRequestDTO bookReq);
	void updateBook(Long id, Book updatedBook);
	void deleteBook(Long id);
	Book getBookById(Long id);
	List<Book> getAll();
	Page<Book> getByPage(Pageable pageable);
	Long getCount();
	void giveBookToReader(Long id);
	public void giveBookToLibrary(Long id);
}
