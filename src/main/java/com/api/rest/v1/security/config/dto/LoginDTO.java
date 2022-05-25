package com.api.rest.v1.security.config.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
	@NotBlank
	private String user;
	
	@NotBlank
	private String password;

}
