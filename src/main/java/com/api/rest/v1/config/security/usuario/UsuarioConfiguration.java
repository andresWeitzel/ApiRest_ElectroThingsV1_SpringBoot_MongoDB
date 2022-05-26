package com.api.rest.v1.config.security.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioConfiguration {
	
	private String user;
	private String password;

}
