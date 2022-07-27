package com.api.rest.v1.security.entities;


import java.util.Set;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
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

	@Field("_id")
	@NotNull(message="El ID no puede ser null")
	private String id;

	@Field("nombre")
	@NotNull(message = "El Nombre no puede ser null")
	private String nombre;

	@Field("apellido")
	@NotNull(message = "El Apellido no puede ser null")
	private String apellido;

	@Field("user")
	@Indexed(unique = true)
	@NotNull(message = "El Username no puede ser null")
	private String username;

	@Field("password")
	@NotNull(message = "El Password no puede ser null")
	private String password;

	@Field("email")
	@Indexed(unique = true)
	@NotNull(message = "El Email no puede ser null")
	private String email;

	@Field("roles")
	@NotNull(message="Los Roles no pueden ser nulos")
	private Set<TipoRol> roles;

	
	/*Constructor parametrico con id
	 * 
	 */
    public Usuario(@NotNull String id,@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email , @NotNull Set<TipoRol> roles) {
    	this.id=id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
    
    
    /*Constructor parametrico con id sin roles
	 * 
	 */
    public Usuario(@NotNull String id,@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email ) {
    	this.id=id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        
    }
	
	
	
	/*Constructor parametrico sin id
	 * 
	 */
    public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email , @NotNull Set<TipoRol> roles) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
    
	/*Constructor parametrico sin id y sin roles para SIGNIN
	 * 
	 */
    public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email ) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        
    }

}
