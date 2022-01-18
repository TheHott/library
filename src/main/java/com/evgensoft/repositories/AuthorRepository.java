package com.evgensoft.repositories;

import java.util.List;

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

	@Query("SELECT a FROM Author a LEFT JOIN Country c ON a.birthCountry.id = c.id "
			+ "WHERE CONCAT(a.fullName, ' ', c.name, ' ', a.birthday, ' ', a.deathDate) LIKE %?1%")
	public List<Author> search(String keyword);
}
