package com.evgensoft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evgensoft.entities.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
	@Query("SELECT r FROM Reader r WHERE CONCAT(r.fullName, ' ', r.id) LIKE %?1%")
	public List<Reader> search(String keyword);

}
