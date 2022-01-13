package com.evgensoft.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.evgensoft.dto.requests.CountryRequestDTO;
import com.evgensoft.entities.Author;
import com.evgensoft.entities.Country;

public interface CountryService {
	Long createCountry(CountryRequestDTO countryReq);

	Country readById(Long id);

	void deleteCountry(Long id);

	void updateCountry(Long id, Country updatedCountry);

	List<Country> getAll();

	Page<Country> getByPage(Pageable pageable);

	Long getCount();

	Country getByName(String name);

	Long getAuthorsCount(Long countryId);

	Page<Author> getAuthorsByPage(Long countryId, Pageable pageable);
}
