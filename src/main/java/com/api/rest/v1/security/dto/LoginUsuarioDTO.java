package com.api.rest.v1.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUsuarioDTO {
	
	
	@NotBlank
    private String username;
    
    @NotBlank
    private String password;

}
