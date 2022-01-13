package com.evgensoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.evgensoft.entities.User;
import com.evgensoft.services.impl.UserServiceTest;

@Controller
public class RegistrationController {
	@Autowired
	private UserServiceTest userService;
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("userForm", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String addUser(@Validated @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		if(!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			model.addAttribute("passwordError", "Пароли не совпадают");
			return "signup";
		}
		if(!userService.saveUser(userForm)) {
			model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
			return "signup";
		}

		return "redirect:/";
	}

}
