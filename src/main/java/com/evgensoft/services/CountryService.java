package com.evgensoft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.evgensoft.dto.requests.CountryRequestDTO;
import com.evgensoft.entities.Country;

public interface CountryService {
	Long createCountry(CountryRequestDTO countryReq);
	Country readById(Long id);
	void deleteCountry(Long id);
	void updateCountry(Long id, Country updatedCountry);
	List<Country> getAll();
	Page<Country> getByPage(Pageable pageable);
	Long getCount();
	public Country getByName(String name);
}
