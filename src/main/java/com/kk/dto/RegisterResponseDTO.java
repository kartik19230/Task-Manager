package com.kk.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {

	private Long id;
	private String name;
	private String email;
}
