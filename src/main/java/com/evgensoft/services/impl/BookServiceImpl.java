package com.evgensoft.services.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evgensoft.dto.requests.BookRequestDTO;
import com.evgensoft.entities.Book;
import com.evgensoft.entities.Reader;
import com.evgensoft.entities.TakenBook;
import com.evgensoft.exceptions.NotFoundException;
import com.evgensoft.repositories.BookRepository;
import com.evgensoft.repositories.TakenBookRepository;
import com.evgensoft.services.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepo;
	private final TakenBookRepository takenBookRepo;

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
		return bookRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Книга с id=%d не найдена!", id)));
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
	public void giveBookToReader(Long bookId, Reader reader) {
		Book book = getBookById(bookId);
		// Reader reader = readerRepo.getById(readerId);
		TakenBook takenBook = new TakenBook();
		takenBook.setBook(book);
		takenBook.setReader(reader);
		takenBook.setDateOfTaking(LocalDate.now());
		int stock = book.getInStock();

		if (stock > 0) {
			bookRepo.setInStock(bookId, stock - 1);
			takenBookRepo.save(takenBook);
		} else {
			// TODO вывести ошибку что на складе нет этой книги
		}
	}

	@Transactional
	@Override
	public void giveBookToLibrary(Long bookId, Long takenId) {
		Book book = getBookById(bookId);
		Integer stock = book.getInStock();
		bookRepo.setInStock(bookId, stock + 1);
		takenBookRepo.deleteById(takenId);
	}

	@Override
	public List<Book> listAll(String keyword) {
		if (keyword != null) {
			return bookRepo.search(keyword);
		}
		return bookRepo.findAll();
	}

}
