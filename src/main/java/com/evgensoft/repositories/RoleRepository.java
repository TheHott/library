package com.evgensoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgensoft.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
