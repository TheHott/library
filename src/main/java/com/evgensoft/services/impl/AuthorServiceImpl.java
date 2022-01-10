package com.evgensoft.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evgensoft.dto.requests.AuthorRequestDTO;
import com.evgensoft.entities.Author;
import com.evgensoft.exceptions.NotFoundException;
import com.evgensoft.repositories.AuthorRepository;
import com.evgensoft.services.AuthorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepo;
	
	@Override
	public Long createAuthor(AuthorRequestDTO authorReq) {
		Author author = AuthorRequestDTO.toEntity(authorReq);
		return authorRepo.save(author).getId();
	}

	@Override
	public void updateAuthor(Long id, Author updatedAuthor) {
		updatedAuthor.setId(id);
		authorRepo.save(updatedAuthor);
	}

	@Override
	public void deleteAuthor(Long id) {
		authorRepo.deleteById(id);
	}

	@Override
	public Author getAuthorById(Long id) {
		return authorRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Страна с id=%d не найдена!", id)));
	}

	@Override
	public List<Author> getAll() {
		return authorRepo.findAll();
	}

	@Override
	public Page<Author> getByPage(Pageable pageable) {
		return authorRepo.findAll(pageable);
	}

	@Override
	public Long getCount() {
		return authorRepo.count();
	}

	@Override
	public Author getAuthorByFullName(String name) {
		try {
			Author author = authorRepo.findByFullName(name);
			return author;
		} catch (NotFoundException nfe) {
			throw new NotFoundException(String.format("Автора с именем %d не найдена", name));
		}
	}
	
}
