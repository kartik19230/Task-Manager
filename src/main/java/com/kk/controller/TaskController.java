package com.kk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import com.kk.dto.TaskRequestDTO;
import com.kk.entity.Users;
import com.kk.service.TaskService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
	
	private final TaskService service;
	
	@GetMapping("/add")
	public String showTaskForm(Model model) {
		
		model.addAttribute("taskRequestDTO", new TaskRequestDTO());
		
		return "task-form";
	}

	@PostMapping("/add")
	public String addTask(@Valid @ModelAttribute("taskRequestDTO") TaskRequestDTO dto,BindingResult result,HttpSession session) {
		
		if (result.hasErrors()) {
			return "task-form";
		}
		
		Users user = (Users)session.getAttribute("user");
		
		service.addTask(dto, user);
		
		return "redirect:/task/list";
	}
	
	@GetMapping("/list")
	public String getTasks(HttpSession session,Model model) {
		
		Users user = (Users)session.getAttribute("user");
		
		if (user == null) {
			
			return "redirect:/auth/loginIfRegister";
		}
		
		model.addAttribute("tasks", service.getTasksByUser(user));
		
		return "task-list";
	}
	
}
