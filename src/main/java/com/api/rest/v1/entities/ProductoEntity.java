package com.api.rest.v1.entities;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="productos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoEntity {

	//@Field("_id") Porque es que el repository de mongo no toma valido el id con esta referencia???
	
	@Id
	//@Field("_id")
	private String id;
	
	@Field("codigo")
	@Indexed(unique = true)
	@NotNull(message="El Código no puede ser null")
	@NotBlank
	private String codigo;
	
	@Field("nombre")
	@Indexed(unique = true)
	@NotNull(message="El Nombre no puede ser null")
	@NotBlank
	private String nombre;
	
	@Field("descripcion")
	@NotBlank
	private String descripcion;
	
	@Field("categoria")
	@NotNull(message="La categoría no puede ser null")
	@NotBlank
	private String categoria;
	
	@Field("marca")
	@NotNull(message="La marca no puede ser null")
	@NotBlank
	private String marca;
	
	@Field("imagen")
	//@NotNull(message="La Imagen no puede ser null")
	private String imagen;
	
	@Field("hoja_datos")
	//@NotNull(message="La Hoja De Datos no puede ser null")
	private String hojaDatos;
	
	@Field("stock")
	@NotNull(message="El Stock no puede ser null")
	@NotBlank
	private int stock;
	
	@Field("precio")
	@NotNull(message="El Precio no puede ser null")
	@NotBlank
	private int precio;
	
	@Field("fecha")
	@NotNull(message="La Fecha no puede ser null")
	@NotBlank
	private String fecha;
	
	@Field("hora")
	@NotNull(message="La Hora no puede ser null")
	@NotBlank
	private String hora;

}
