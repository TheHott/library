package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgensoft.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	Country findByName(String name);
}
