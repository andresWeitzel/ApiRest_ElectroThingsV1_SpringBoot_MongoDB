package com.api.rest.v1.security.entities;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.api.rest.v1.security.enums.TipoRol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;

	@Field("nombre")
	@NotNull(message = "El Nombre no puede ser null")
	@NotEmpty(message = "El Nombre no puede ser vacío")
	@NotBlank(message = "El Nombre no puede estar en blanco")
	@Size(min = 4, max = 100, message = "El Nombre debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres")
	private String nombre;

	@Field("apellido")
	@NotNull(message = "El Apellido no puede ser null")
	@NotEmpty(message = "El Apellido no puede ser vacío")
	@NotBlank(message = "El Apellido no puede estar en blanco")
	@Size(min = 4, max = 100, message = "El Apellido debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres")
	private String apellido;

	@Field("user")
	@Indexed(unique = true)
	@NotNull(message = "El Username no puede ser null")
	@NotEmpty(message = "El Username no puede ser vacío")
	 @NotBlank(message="El Username no puede estar en blanco")
  	@Size(min = 4, max = 100, message = "El Username debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres") 
	private String username;

	@Field("password")
	@NotNull(message = "El Password no puede ser null")
	@NotEmpty(message = "El Password no puede ser vacío")
	  @NotBlank(message="El Password no puede estar en blanco")
  	@Size(min = 4, max = 255, message = "El Password debe ser mayor o igual a 4 caracteres y menor o igual a 255 caracteres")
	private String password;

	@Field("email")
	@Email
	@Indexed(unique = true)
	@NotNull(message = "El Email no puede ser null")
	@NotEmpty(message = "El Email no puede ser vacío")
    @NotBlank(message="El Email no puede estar en blanco")
   	@Size(min = 4, max = 255, message = "El Email debe ser mayor o igual a 4 caracteres y menor o igual a 255 caracteres")
	private String email;

	@Field("roles")
	@NotNull(message = "Los Roles no pueden ser nulos")
	// No agregamos otra comprobacion por usar E.D
	private Set<TipoRol> roles;

	/*
	 * Constructor parametrico con id sin roles
	 * 
	 */
	public Usuario(@NotNull String id, @NotNull String nombre, @NotNull String apellido, @NotNull String username,
			@NotNull String password, @NotNull String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.username = username;
		this.password = password;
		this.email = email;

	}

	/*
	 * Constructor parametrico sin id
	 * 
	 */
	public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull String username, @NotNull String password,
			@NotNull String email, @NotNull Set<TipoRol> roles) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	/*
	 * Constructor parametrico sin id y sin roles para SIGNIN
	 * 
	 */
	public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull String username, @NotNull String password,
			@NotNull String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.username = username;
		this.password = password;
		this.email = email;

	}

}
