package com.evgensoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evgensoft.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
	Country findByName(String name);

	@Query("SELECT c FROM Country c WHERE c.name LIKE %?1%")
	public List<Country> search(String keyword);
}
