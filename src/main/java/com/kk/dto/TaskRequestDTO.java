package com.kk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequestDTO {

	@NotBlank(message ="Title is required")
	@Size(max = 100,message = "Only 100 character allowed")
	private String title;
	
	@NotBlank(message = "Description is required")
	@Size(max = 500,message = "Only 500 character allowed")
	private String description;
}
