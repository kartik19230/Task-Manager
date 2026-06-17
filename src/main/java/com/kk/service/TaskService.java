package com.kk.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kk.dto.TaskRequestDTO;
import com.kk.entity.Task;
import com.kk.entity.Users;
import com.kk.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository repo;
	
	public void addTask(TaskRequestDTO dto, Users user) {
		
		Task task  = new Task();
		
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.setCompleted(false);
		task.setCreatedAt(LocalDateTime.now());
		task.setUser(user);
		
		repo.save(task);
	}
	
	public List<Task> getTasksByUser(Users user) {
		
		return repo.findByUser(user);
	}
}
