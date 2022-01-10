package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.evgensoft.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Modifying
	@Query(value = "update Book b set b.inStock = :inStock where b.id = :id")
	void setInStock(Long id, Integer inStock);
}
