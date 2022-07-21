package com.api.rest.v1.security.entities;

import java.util.HashSet;
import java.util.Set;

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
@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;

	@Field("nombre")
	@NotNull(message = "El Nombre no puede ser null")
	private String nombre;

	@Field("apellido")
	@NotNull(message = "El Apellido no puede ser null")
	private String apellido;

	@Field("user")
	@NotNull(message = "El Username no puede ser null")
	private String username;

	@Field("password")
	@NotNull(message = "El Password no puede ser null")
	private String password;

	@Field("email")
	@NotNull(message = "El Email no puede ser null")
	private String email;

	@NotNull(message="Los Roles no pueden ser nulos")
	private Set<Rol> roles = new HashSet<>();

	
	/*Constructor parametrico sin id
	 * 
	 */
	public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String password, @NotNull String email) {
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
