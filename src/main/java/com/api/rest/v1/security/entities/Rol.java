package com.api.rest.v1.security.entities;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.api.rest.v1.security.enums.TipoRol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Rol {
	
	
	@Id
	private String id;
	
	@Field("rol")
	@NotNull(message = "El Rol no puede ser null")
	private TipoRol rol;
	
	  /**
     * Constructor parametrico sin ID
     * @param rol
     */
    public Rol(@NotNull TipoRol rol) {
        this.rol = rol;
    }
	

}
