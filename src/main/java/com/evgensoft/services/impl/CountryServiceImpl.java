package com.evgensoft.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evgensoft.dto.requests.CountryRequestDTO;
import com.evgensoft.entities.Author;
import com.evgensoft.entities.Country;
import com.evgensoft.exceptions.NotFoundException;
import com.evgensoft.repositories.AuthorRepository;
import com.evgensoft.repositories.CountryRepository;
import com.evgensoft.services.CountryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

	private final CountryRepository countryRepo;
	private final AuthorRepository authorRepo;

	@Override
	public Long getCount() {
		return countryRepo.count();
	}

	@Override
	public Long createCountry(CountryRequestDTO countryReq) {
		return countryRepo.save(CountryRequestDTO.toEntity(countryReq)).getId();
	}

	@Override
	public Country readById(Long id) {
		return countryRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Страна с id=%d не найдена!", id)));
	}

	@Override
	public void deleteCountry(Long id) {
		countryRepo.deleteById(id);
	}

	@Override
	public void updateCountry(Long id, Country updatedCountry) {
		updatedCountry.setId(id);
		countryRepo.save(updatedCountry);
	}

	@Override
	public List<Country> getAll() {
		return countryRepo.findAll();
	}

	@Override
	public Page<Country> getByPage(Pageable pageable) {
		return countryRepo.findAll(pageable);
	}

	@Override
	public Country getByName(String name) {
		try {
			Country country = countryRepo.findByName(name);
			return country;
		} catch (NotFoundException nfe) {
			throw new NotFoundException(String.format("Страна с именем %d не найдена", name));
		}

	}

	@Override
	public Long getAuthorsCount(Long countryId) {
		return authorRepo.countByBirthCountryId(countryId);
	}

	@Override
	public Page<Author> getAuthorsByPage(Long countryId, Pageable pageable) {
		return authorRepo.findAllByBirthCountryId(countryId, pageable);
	}

	@Override
	public List<Country> listAll(String keyword) {
		if (keyword != null) {
			return countryRepo.search(keyword);
		}
		return countryRepo.findAll();
	}
}
