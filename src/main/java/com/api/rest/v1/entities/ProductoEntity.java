package com.api.rest.v1.entities;



import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="productos")
public class ProductoEntity {

	@Id
	private String id;
	
	@Field("codigo")
	@NotNull(message="El Código no puede ser null")
	private String codigo;
	
	@Field("nombre")
	@NotNull(message="El Nombre no puede ser null")
	private String nombre;
	
	@Field("descripcion")
	private String descripcion;
	
	@Field("categoria")
	@NotNull(message="La categoría no puede ser null")
	private String categoria;
	
	@Field("marca")
	@NotNull(message="La marca no puede ser null")
	private String marca;
	
	@Field("imagen")
	@NotNull(message="La Imagen no puede ser null")
	private String imagen;
	
	@Field("hoja_datos")
	@NotNull(message="La Hoja De Datos no puede ser null")
	private String hojaDatos;
	
	@Field("stock")
	@NotNull(message="El Stock no puede ser null")
	private int stock;
	
	@Field("precio")
	@NotNull(message="El Precio no puede ser null")
	private int precio;
	
	@Field("fecha")
	@NotNull(message="La Fecha no puede ser null")
	private String fecha;
	
	@Field("hora")
	@NotNull(message="La Hora no puede ser null")
	private String hora;

}
