package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgensoft.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
