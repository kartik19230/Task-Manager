package com.kk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDTO {

	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Invalid Email format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8,message = "Password must be greater than 8 character")
	private String password;
}
