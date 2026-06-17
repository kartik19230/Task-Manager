package com.kk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.entity.Task;
import com.kk.entity.Users;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByUser(Users user);
}
