package com.kk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kk.dto.LoginDTO;
import com.kk.dto.RegisterRequestDTO;
import com.kk.dto.RegisterResponseDTO;
import com.kk.entity.Users;
import com.kk.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService service;

	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("registerRequestDTO",new RegisterRequestDTO());

		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute RegisterRequestDTO dto,BindingResult result) {

		if (result.hasErrors()) {
			return "register";
		}
		RegisterResponseDTO responseDTO = service.register(dto);

		return "login";
	}
	
	@GetMapping("/loginIfRegister")
	public String login(Model model) {
		
		model.addAttribute("loginDTO", new LoginDTO());
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginDTO") LoginDTO dto,BindingResult result,HttpSession session) {
		
		if (result.hasErrors()) {
			return "login";
		}

		Users user = service.authenticate(dto);
		
		if (user == null) {

		    result.reject("login.failed", "Invalid Email or Password");

		    System.out.println("Has Errors : " + result.hasErrors());
		    System.out.println("All Errors : " + result.getAllErrors());

		    return "login";
		}
		
		session.setAttribute("user", user);
		
		return "redirect:/user/dashboard";

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/auth/loginIfRegister";
	}

}
