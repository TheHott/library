//package com.evgensoft.services.impl;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.evgensoft.entities.User;
//import com.evgensoft.enums.Role;
//import com.evgensoft.repositories.UserRepository;
//import com.evgensoft.services.UserService;
//import com.evgensoft.utils.GlobalConfig;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//	@Override
//	public User findUserByLogin(String login) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long createUser(String login, String password, Role role, Long accountId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
////	private final User admin;
////    private final UserRepository userRepository;
////    
////    public UserServiceImpl(UserRepository userRepository,
////            PasswordEncoder passwordEncoder,
////            GlobalConfig config) {
////    	this.userRepository = userRepository;
////		this.admin = new User(config.getAdminLogin(),
////		passwordEncoder.encode(config.getAdminPassword()), null, Role.ADMIN);
////    }
////
////	@Override
////	public User findUserByLogin(String login) {
////		if (login.equals(admin.getLogin())) {
////            return admin;
////        }
////        return userRepository.findByLogin(login).orElseThrow(RuntimeException::new);
////
////	}
////
////	@Override
////	public Long createUser(String login, String password, Role role, Long accountId) {
////		User user = new User(login, password, accountId, role);
////        return userRepository.save(user).getId();
////	}
//
//}
