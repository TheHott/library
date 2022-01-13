package com.evgensoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evgensoft.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Modifying
	@Query(value = "update Book b set b.inStock = :inStock where b.id = :id")
	void setInStock(Long id, Integer inStock);

	@Query("SELECT COUNT(*) FROM Book WHERE author_id=:authorId")
	Long countByAuthorId(@Param("authorId") Long authorId);

	Page<Book> findAllByAuthorId(Long authorId, Pageable pageable);
}
