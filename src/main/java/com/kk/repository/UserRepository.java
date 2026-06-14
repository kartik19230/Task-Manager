package com.kk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.entity.Users;
import java.util.List;


public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users>  findByEmail(String email);
}
