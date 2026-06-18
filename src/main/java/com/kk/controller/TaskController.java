package com.kk.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kk.dto.TaskRequestDTO;
import com.kk.entity.Task;
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
	public String getTasks(@RequestParam(defaultValue = "0") int page,HttpSession session,Model model) {
		
		Users user = (Users)session.getAttribute("user");
		
		if (user == null) {
			
			return "redirect:/auth/loginIfRegister";
		}
		
		Page<Task> taskPage = service.getTasks(user, page);
		
//		model.addAttribute("tasks", service.getTasksByUser(user));
		model.addAttribute("taskPage",taskPage);
		
		return "task-list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id,HttpSession session) {
		
		service.deleteTask(id,(Users) session.getAttribute("user"));
		
		return "redirect:/task/list";
	}
	
	@GetMapping("/toggle/{id}")
	public String toggleTask(@PathVariable Long id,HttpSession session) {
		
		Users user = (Users) session.getAttribute("user");
		
		service.toggleTask(id, user);
		
		return "redirect:/task/list";
	}
	
	@GetMapping("/update/{id}")
	public String editTaskForm(@PathVariable Long id,HttpSession session,Model model) {
		
		Users user = (Users) session.getAttribute("user");
		
		TaskRequestDTO dto = service.setTaskForUpdate(id, user);
		
		model.addAttribute("taskRequestDTO", dto);
		
		return "task-form";
	}
	
	@PostMapping("/update")
	public String updateTask(@Valid @ModelAttribute TaskRequestDTO dto,BindingResult result,HttpSession session) {
		
		if (result.hasErrors()) {
			return "redirect:/task/form";
		}
		
		Users user = (Users) session.getAttribute("user");
		
		service.updateTask(dto, user);
		
		return "redirect:/task/list";
	}
	
}
