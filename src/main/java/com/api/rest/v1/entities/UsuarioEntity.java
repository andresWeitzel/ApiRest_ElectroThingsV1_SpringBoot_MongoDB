package com.api.rest.v1.entities;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="usuarios")
public class UsuarioEntity {
	
	
	@Id
	private String id;
	
	@Field("nombre")
	@NotNull(message="El Código no puede ser null")
	private String nombre;
	
	@Field("apellido")
	@NotNull(message="El Apellido no puede ser null")
	private String apellido;
	
	
	@Field("user")
	@NotNull(message="El User no puede ser null")
	private String user;
	
	@Field("password")
	@NotNull(message="El Password no puede ser null")
	private String password;
	
	@Field("email")
	@NotNull(message="El Email no puede ser null")
	private String email;

}
