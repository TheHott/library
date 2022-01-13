package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgensoft.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
