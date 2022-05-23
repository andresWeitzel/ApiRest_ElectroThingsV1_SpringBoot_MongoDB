package com.api.rest.v1.controllers;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.v1.entities.ProductoEntity;
import com.api.rest.v1.entities.UsuarioEntity;
import com.api.rest.v1.services.productos.I_ProductoService;
import com.api.rest.v1.services.usuarios.I_UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin()
public class UsuarioController {

	@Autowired
	I_UsuarioService iUsuarioService;

	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	// ================
	// ===== POST =====
	// =================
	@Operation(summary = "Inserción de un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Insertado el Usuario Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Insertar el Usuario. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Inserción del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<?> addUsuario(@RequestBody UsuarioEntity usuario) {
		try {
			iUsuarioService.addUsuario(usuario);
			return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}
	
	
	
	// ================
	// ===== PUT =====
	// =================
	@Operation(summary = "Actualización de un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Actualización el Usuario Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Actualización el Usuario. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Actualización del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PutMapping("/")
	public ResponseEntity<?> updateUsuario(@RequestBody UsuarioEntity usuario) {
		try {
			iUsuarioService.updateUsuario(usuario);
			return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}
	
	
	
	
	// ================
	// ===== DELETE =====
	// =================
	@Operation(summary = "Eliminación de un Usuario")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Eliminación el Usuario Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo Eliminación el Usuario. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "La Eliminación del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable("id") String id) {
		try {
			iUsuarioService.deleteUsuario(id);
			return new ResponseEntity<UsuarioEntity>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	
	
	
}