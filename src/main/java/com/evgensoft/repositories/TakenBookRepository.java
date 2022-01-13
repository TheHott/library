package com.evgensoft.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evgensoft.entities.TakenBook;

public interface TakenBookRepository extends JpaRepository<TakenBook, Long> {
	Page<TakenBook> findAllByReaderId(Long readerId, Pageable pageable);

	@Query("SELECT COUNT(*) FROM TakenBook WHERE reader_id=:readerId")
	Long countByReaderId(@Param("readerId") Long readerId);

	@Transactional
	void deleteAllByReaderId(Long readerId);
}
