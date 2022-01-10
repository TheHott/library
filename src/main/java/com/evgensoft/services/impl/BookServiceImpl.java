package com.evgensoft.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evgensoft.dto.requests.BookRequestDTO;
import com.evgensoft.entities.Book;
import com.evgensoft.exceptions.NotFoundException;
import com.evgensoft.repositories.BookRepository;
import com.evgensoft.services.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepo;

	@Override
	public Long createBook(BookRequestDTO bookReq) {
		Book book = BookRequestDTO.toEntity(bookReq);
		return bookRepo.save(book).getId();
	}

	@Override
	public void updateBook(Long id, Book updatedBook) {
		updatedBook.setId(id);
		bookRepo.save(updatedBook);
	}

	@Override
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}

	@Override
	public Book getBookById(Long id) {
		return bookRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Книга с id=%d не найдена!", id)));
	}

	@Override
	public List<Book> getAll() {
		return bookRepo.findAll();
	}

	@Override
	public Page<Book> getByPage(Pageable pageable) {
		return bookRepo.findAll(pageable);
	}

	@Override
	public Long getCount() {
		return bookRepo.count();
	}

	@Transactional
	@Override
	public void giveBookToReader(Long id) {
		Book book = getBookById(id);
		Integer stock = book.getInStock();
		if(stock > 0) {
			bookRepo.setInStock(id, stock - 1);
		} else {
			// TODO вывести ошибку что на складе нет этой книги
		}
	}
	
	@Transactional
	@Override
	public void giveBookToLibrary(Long id) {
		Book book = getBookById(id);
		Integer stock = book.getInStock();
		bookRepo.setInStock(id, stock + 1);
	}
	
	

}
