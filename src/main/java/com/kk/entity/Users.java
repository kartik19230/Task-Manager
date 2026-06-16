package com.kk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@Email(message = "Invalid Email format")
	@NotBlank(message = "Email is required")
	@Column(unique = true,nullable = false)
	private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8,message = "Password must greater than 8 characters")
	private String password;

}
