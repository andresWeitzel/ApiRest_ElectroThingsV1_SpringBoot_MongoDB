package com.api.rest.v1.security.entities;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.api.rest.v1.security.enums.TipoRol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Rol {
	
	
	@Field("_id")
	@NotNull(message="El ID no puede ser null")
	private ObjectId id;
	
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
