package com.api.rest.v1.entities;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="productos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoEntity {

	
	@Id
	//@Field("_id")El repository de mongo no toma valido el id con esta referencia
	private String id;
	
	@Field("codigo")
	@Indexed(unique = true)
	@NotNull(message="El Código no puede ser null")
	@NotEmpty(message="El Código no puede ser vacío")
	private String codigo;
	
	@Field("nombre")
	@Indexed(unique = true)
	@NotNull(message="El Nombre no puede ser null")
	@NotEmpty(message="El Nombre no puede ser vacío")
	private String nombre;
	
	@Field("descripcion")
	@NotNull(message="La Descripción no puede ser null")
	@NotEmpty(message="La Descripción no puede ser vacío")
	private String descripcion;
	
	@Field("categoria")
	@NotNull(message="La Categoría no puede ser null")
	@NotEmpty(message="La Categoría no puede ser vacía")
	private String categoria;
	
	@Field("marca")
	@NotNull(message="La Marca no puede ser null")
	@NotEmpty(message="La Marca no puede ser vacía")
	private String marca;
	
	@Field("imagen")
	@NotNull(message="La Imagen no puede ser null")
	//@NotEmpty(message="La Imagen no puede ser vacía")
	private String imagen;
	
	@Field("hoja_datos")
	@NotNull(message="La Hoja De Datos no puede ser null")
	//@NotEmpty(message="La Hoja de Datos no puede ser vacía")
	private String hojaDatos;
	
	@Field("stock")
	@NotNull(message="El Stock no puede ser null")
	//No podemos validar con mas opciones los enteros
	private int stock;
	
	@Field("precio")
	@NotNull(message="El Precio no puede ser null")
	//No podemos validar con mas opciones los enteros
	private int precio;
	
	@Field("fecha")
	@NotNull(message="La Fecha no puede ser null")
	@NotEmpty(message="La Fecha no puede ser vacía")
	private String fecha;
	
	@Field("hora")
	@NotNull(message="La Hora no puede ser null")
	@NotEmpty(message="La Hora no puede ser vacía")
	private String hora;

}
