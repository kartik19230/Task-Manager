package com.kk.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kk.entity.Task;
import com.kk.entity.Users;

public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByUserOrderByIdAsc(Users user);
	
	Page<Task> findByUser(Users user,Pageable pageable);
	
	@Query("""
			SELECT t
			FROM Task t
			WHERE t.user =:user
			ORDER BY t.completed ASC,
			          t.createdAt DESC
			""")
	Page<Task> findTaskByUser(@Param("user") Users user,Pageable pageable);
}
