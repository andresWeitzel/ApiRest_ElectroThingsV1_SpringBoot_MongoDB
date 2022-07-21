package com.api.rest.v1.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninUsuarioDTO {
	
	@NotBlank
    private String nombre;
	
	@NotBlank
    private String apellido;
    
    @NotBlank
    private String username;

    @NotBlank
    private String password;
    
    @Email
    private String email;
    
    private Set<String> roles = new HashSet<>();

}
