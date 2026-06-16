package com.kk.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kk.dto.LoginDTO;
import com.kk.dto.RegisterRequestDTO;
import com.kk.dto.RegisterResponseDTO;
import com.kk.entity.Users;
import com.kk.exception.UserAlreadyExistException;
import com.kk.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	
	public RegisterResponseDTO register(RegisterRequestDTO dto) {
		
		if (repo.existsByEmail(dto.getEmail())) {
			throw new UserAlreadyExistException("Email already registered");
		}
		
		Users user = new Users();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		Users savedUser = repo.save(user);
		
		return RegisterResponseDTO.builder()
				.id(savedUser.getId())
				.name(savedUser.getName())
				.email(savedUser.getEmail())
				.build();
	}
	
	public Users authenticate(LoginDTO dto) {
		
			Users user = repo.findByEmail(dto.getEmail());
			
			if (user == null) {
				return null;
			}
			
			boolean passwordCheck = passwordEncoder.matches(dto.getPassword(), user.getPassword());
			
			if (passwordCheck) {
				return user;
			}else {
				return null;
			}
	}
}
