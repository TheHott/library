package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgensoft.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	Author findByFullName(String name);
}
