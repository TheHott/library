package com.evgensoft.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.evgensoft.dto.requests.AuthorRequestDTO;
import com.evgensoft.entities.Author;
import com.evgensoft.entities.Book;

public interface AuthorService {
	Long createAuthor(AuthorRequestDTO authorReq);

	void updateAuthor(Long id, Author updatedAuthor);

	void deleteAuthor(Long id);

	Author getAuthorById(Long id);

	List<Author> getAll();

	Page<Author> getByPage(Pageable pageable);

	Long getCount();

	Author getAuthorByFullName(String name);

	Long getBooksCount(Long authorId);

	Page<Book> getBooksByPage(Long authorId, Pageable pageable);

	List<Author> listAll(String keyword);
}
