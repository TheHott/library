package com.evgensoft.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evgensoft.dto.requests.CountryRequestDTO;
import com.evgensoft.dto.responses.CountryResponseDTO;
import com.evgensoft.entities.Country;
import com.evgensoft.exceptions.NotFoundException;
import com.evgensoft.repositories.CountryRepository;
import com.evgensoft.services.CountryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

	private final CountryRepository countryRepo;
	
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
		return countryRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Страна с id=%d не найдена!", id)));
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
	
	public List<Country> getAll() {
		return countryRepo.findAll();
	}

	@Override
	public Page<Country> getByPage(Pageable pageable) {
		return countryRepo.findAll(pageable);
	}

}
