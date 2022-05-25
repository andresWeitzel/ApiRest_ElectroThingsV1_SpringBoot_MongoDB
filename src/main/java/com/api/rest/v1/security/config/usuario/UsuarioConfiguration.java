package com.api.rest.v1.security.config.usuario;
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
