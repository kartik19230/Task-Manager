package com.kk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kk.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session) {
		
		Users user = (Users)session.getAttribute("user");
		System.out.println(user.getName());
		
		return "dashboard";
	}
}
