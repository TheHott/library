package com.evgensoft.services;

import com.evgensoft.entities.User;
import com.evgensoft.enums.Role;

public interface UserService {
	User findUserByLogin(String login);
    Long createUser(String login, String password, Role role, Long accountId);
}
