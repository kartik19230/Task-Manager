package com.kk.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private int status;
	private String message;
	private LocalDateTime date;
	
	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
		this.date = LocalDateTime.now();
	}
}
