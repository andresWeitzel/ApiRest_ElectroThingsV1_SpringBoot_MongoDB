package com.api.rest.v1.entities;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotBlank(message="El Código no puede estar en blanco")
	@Size(min = 6, max = 255, message = "El Código debe ser mayor o igual a 6 caracteres y menor o igual a 255 caracteres")
	private String codigo;
	
	@Field("nombre")
	@Indexed(unique = true)
	@NotNull(message="El Nombre no puede ser null")
	@NotEmpty(message="El Nombre no puede ser vacío")
	@NotBlank(message="El Nombre no puede estar en blanco")
	@Size(min = 4, max = 30, message = "El Nombre debe ser mayor o igual a 4 caracteres y menor o igual a 30 caracteres")
	private String nombre;
	
	@Field("descripcion")
	@NotNull(message="La Descripción no puede ser null")
	@NotEmpty(message="La Descripción no puede ser vacío")
	@NotBlank(message="La Descripción no puede estar en blanco")
	@Size(min = 4, max = 200, message = "La Descripción debe ser mayor o igual a 4 caracteres y menor o igual a 200 caracteres")
	private String descripcion;
	
	@Field("categoria")
	@NotNull(message="La Categoría no puede ser null")
	@NotEmpty(message="La Categoría no puede ser vacía")
	@NotBlank(message="La Categoría no puede estar en blanco")
	@Size(min = 4, max = 100, message = "La Categoría debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres")
	private String categoria;
	
	@Field("marca")
	@NotNull(message="La Marca no puede ser null")
	@NotEmpty(message="La Marca no puede ser vacía")
	@NotBlank(message="La Marca no puede estar en blanco")
	@Size(min = 2, max = 100, message = "La Marca debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres")
	private String marca;
	
	@Field("imagen")
	@NotNull(message="La Imagen no puede ser null")
	@NotBlank(message="La imagen no puede estar en blanco")
	@Size(min = 20, max = 600, message = "La Imagen debe ser mayor o igual a 20 caracteres y menor o igual a 600 caracteres")
	private String imagen;
	
	@Field("hoja_datos")
	@NotNull(message="La Hoja De Datos no puede ser null")
	@NotBlank(message="La Hoja De Datos no puede estar en blanco")
	@Size(min = 20, max = 600, message = "La Hoja De Datos debe ser mayor o igual a 20 caracteres y menor o igual a 600 caracteres")
	private String hojaDatos;
	
	@Field("stock")
	@NotNull(message="El Stock no puede ser null")
	@Min(value = 10, message = "El Stock debe ser mayor o igual a 10 Unidades")
    @Max(value = 10000, message = "El Stock debe ser menor o igual a 10000 Unidades")
	//No podemos validar con mas opciones los enteros
	private int stock;
	
	@Field("precio")
	@NotNull(message="El Precio no puede ser null")
	@Min(value = 200, message = "El Precio debe ser mayor o igual a $200")
    @Max(value = 100000, message = "El Precio debe ser menor o igual a $100000")
	//No podemos validar con mas opciones los enteros
	private int precio;
	
	@Field("fecha")
	@NotNull(message="La Fecha no puede ser null")
	@NotEmpty(message="La Fecha no puede ser vacía")
	@NotBlank(message="La Fecha no puede estar en blanco")
	@Size(min = 7, max = 20, message = "La Fecha debe ser mayor o igual a 7 caracteres y menor o igual a 20 caracteres")
	private String fecha;
	
	@Field("hora")
	@NotNull(message="La Hora no puede ser null")
	@NotEmpty(message="La Hora no puede ser vacía")
	@NotBlank(message="La Fecha no puede estar en blanco")
	@Size(min = 5, max = 10, message = "La Hora debe ser mayor o igual a 5 caracteres y menor o igual a 10 caracteres")
	private String hora;

}
