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
		
		return repo.findByUserOrderByIdAsc(user);
	}
	
	public void deleteTask(Long id,Users user) {
		
		if (!repo.findById(id)
				.orElseThrow()
				.getUser()
				.getId()
				.equals(user.getId())) {
			
			throw new RuntimeException("unauthorized");
		}
		
		repo.deleteById(id);
	}
	
	public void toggleTask(Long id,Users user) {
		
		Task task = repo.findById(id)
				.orElseThrow();
		
		if (!task.getUser()
				.getId()
				.equals(user.getId())) {
			
			throw new RuntimeException("unauthorized");
		}
		
		//it will toggle the task state from complete to incomplete and incomplete to complete
		task.setCompleted(!task.isCompleted());
		
		repo.save(task);
		
	}
	
	public TaskRequestDTO setTaskForUpdate(Long id,Users user) {
		
		Task task = repo.findById(id)
				.orElseThrow();
		
		if (!task.getUser()
				.getId()
				.equals(user.getId())) {
			
			throw new RuntimeException("unauthorized");
		}
		
		TaskRequestDTO dto = new TaskRequestDTO();
		
		dto.setId(task.getId());
		dto.setTitle(task.getTitle());
		dto.setDescription(task.getDescription());
		
		return dto;
	}
	
	public void updateTask(TaskRequestDTO dto,Users user) {
		
		Task task = repo.findById(dto.getId()).orElseThrow();
		
		if (!task.getUser()
				.getId()
				.equals(user.getId())) {
			
			throw new RuntimeException("unauthorized");
		}
		
		task.setTitle(dto.getTitle());
		task.setDescription(dto.getDescription());
		
		repo.save(task);
	}
}
