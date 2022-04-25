package com.api.rest.v1.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.v1.entities.ProductoEntity;
import com.api.rest.v1.services.I_ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/v1/productos")
public class ProductoController {

	@Autowired
	I_ProductoService iProductoService;

	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	// ================
	// ===== POST =====
	// =================
	@Operation(summary = "Inserción de un Producto Electrónico")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Insertado el Producto Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Insertar el Producto. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Inserción del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<?> addProducto(@RequestBody ProductoEntity producto) {
		try {
			iProductoService.addProducto(producto);
			return new ResponseEntity<ProductoEntity>(producto, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}
	
	// ================
		// ===== PUT=====
		// =================
		@Operation(summary = "Actualización de un Producto")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Se ha Actualizado el Producto Correctamente", content = {
						@Content(mediaType = "application/json") }),
				@ApiResponse(responseCode = "400", description = "No se pudo Actualizar el Producto. Comprobar la Solicitud", content = @Content),
				@ApiResponse(responseCode = "404", description = "La Actualización del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
				@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
		@PutMapping("/")
		public void updateProducto(@RequestBody ProductoEntity producto) {
			iProductoService.updateProducto(producto);
		}
		
		
		// ==================
		// ===== DELETE =====
		// ==================
		@Operation(summary = "Eliminación de un Producto por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Se ha Eliminado el Producto Correctamente", content = {
						@Content(mediaType = "application/json") }),
				@ApiResponse(responseCode = "400", description = "No se pudo Eliminar el Producto. Comprobar la Solicitud", content = @Content),
				@ApiResponse(responseCode = "404", description = "La Eliminación del Producto no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
				@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
		@DeleteMapping("/{id}")
		public void deleteProducto(@PathVariable("id") String id) {
			iProductoService.deleteProducto(id);
		}
		
		
		// ===============
		// ===== GET =====
		// ===============
		// ---LISTADO PAGINADO Y COMPLETO---
		@Operation(summary = "Listado Paginado de Productos Electrónicos")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Productos", content = {
						@Content(mediaType = "application/json") }),
				@ApiResponse(responseCode = "400", description = "No se pudo traer el Listado de Productos. Comprobar la Solicitud", content = @Content),
				@ApiResponse(responseCode = "404", description = "El Listado de Productos no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
				@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
		@GetMapping("/listado")
		public Page<ProductoEntity> getAll(Pageable pageable) {
			return iProductoService.getAllProductos(pageable);
		}
		
		
		
		
		// ==================================================
		// ============= MÉTODOS HTTP BÚSQUEDA =============
		// ==================================================
	
	
	
	
	
	
	
	
}
