package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgensoft.entities.TakenBooks;

public interface TakenBooksRepository extends JpaRepository<TakenBooks, Long>{

}
