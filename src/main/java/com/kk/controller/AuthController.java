package com.kk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kk.dto.RegisterDTO;


@Controller
@RequestMapping("/auth")
public class AuthController {

	@GetMapping("/register")
	public String registerPage(Model model) {
		
		model.addAttribute("register",new RegisterDTO());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(Model model,RegisterDTO registerDTO) {
		return "login";
	}
}
