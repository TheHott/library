package com.evgensoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evgensoft.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	Author findByFullName(String name);

	@Query("SELECT COUNT(*) FROM Author WHERE birth_country_id=:birthCountryId")
	Long countByBirthCountryId(@Param("birthCountryId") Long birthCountryId);

	Page<Author> findAllByBirthCountryId(Long birthCountryId, Pageable pageable);
}
