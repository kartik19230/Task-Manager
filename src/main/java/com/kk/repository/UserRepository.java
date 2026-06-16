package com.kk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kk.entity.Users;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	boolean existsByEmail(String email);
	
	Users findByEmail(String email);
}
