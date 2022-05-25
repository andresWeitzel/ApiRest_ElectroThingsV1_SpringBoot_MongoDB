package com.api.rest.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api.rest.v1.entities.UsuarioEntity;
import com.api.rest.v1.services.usuarios.I_UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin
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
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable("id") String id) {
		try {
			iUsuarioService.deleteUsuario(id);
			return new ResponseEntity<UsuarioEntity>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

	// ===================
	// ===== GET ALL =====
	// ===================
	// ---LISTADO PAGINADO---
	@Operation(summary = "Listado Paginado de Usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Listado de Usuarios. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Usuarios no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/listado")
	public Page<UsuarioEntity> getAll(Pageable pageable) {
		return iUsuarioService.getAllUsuarios(pageable);
	}

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// ===================
	// ===== GET BY ID ===
	// ===================
	// ---PRODUCTO POR ID---
	@Operation(summary = "Usuarios según su Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Usuario según su ID Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer el Usuario según su ID. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El  Usuario según su ID no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/id/{id}")
	public UsuarioEntity getById(@PathVariable("id") String id) {
		return iUsuarioService.getById(id);
	}

	// =======================
	// ===== GET BY NOMBRE ===
	// =======================
	// ---LISTADO DE USUARIOS O USUARIO POR NOMBRE---
	@Operation(summary = "Listado de Usuarios o Usuario según su Nombre")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios o Usuario según su NOMBRE Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Usuarios o Usuario según su NOMBRE. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Usuarios o Usuario según su NOMBRE no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/nombre/{nombre}")
	public Page<UsuarioEntity> getByNombre(@PathVariable("nombre") String nombre, Pageable pageable) {
		return iUsuarioService.getByNombre(nombre, pageable);
	}

	// =======================
	// ===== GET BY APELLIDO ===
	// =======================
	// ---LISTADO DE USUARIOS O USUARIO POR APELLIDO---
	@Operation(summary = "Listado de Usuarios o Usuario según su Apellido")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios o Usuario según su APELLIDO Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Usuarios o Usuario según su APELLIDO. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Usuarios o Usuario según su APELLIDO no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/apellido/{apellido}")
	public Page<UsuarioEntity> getByApellido(@PathVariable("apellido") String apellido, Pageable pageable) {
		return iUsuarioService.getByApellido(apellido, pageable);
	}

	// =======================
	// ===== GET BY USER ===
	// =======================
	// ---LISTADO DE USUARIOS O USUARIO POR USER---
	@Operation(summary = "Listado de Usuarios o Usuario según su User")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios o Usuario según su USER Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Usuarios o Usuario según su USER. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Usuarios o Usuario según su USER no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/user/{user}")
	public Page<UsuarioEntity> getByUser(@PathVariable("user") String user, Pageable pageable) {
		return iUsuarioService.getByUser(user, pageable);
	}

	// =======================
	// ===== GET BY PASSWORD ===
	// =======================
	// ---LISTADO DE USUARIOS O USUARIO POR PASSWORD---
	@Operation(summary = "Listado de Usuarios o Usuario según su Password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios o Usuario según su PASSWORD Correctamente", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Usuarios o Usuario según su PASSWORD. Comprobar la Solicitud", content = @Content),
			@ApiResponse(responseCode = "404", description = "El Listado de Usuarios o Usuario según su PASSWORD no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
			@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
	@GetMapping("/password/{password}")
	public Page<UsuarioEntity> getByPassword(@PathVariable("password") String password, Pageable pageable) {
		return iUsuarioService.getByPassword(password, pageable);
	}
	
	// =======================
		// ===== GET BY ROL ===
		// =======================
		// ---LISTADO DE USUARIOS O USUARIO POR ROL---
		@Operation(summary = "Listado de Usuarios o Usuario según su Rol")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Se ha Traído el Listado de Usuarios o Usuario según su ROL Correctamente", content = {
						@Content(mediaType = "application/json") }),
				@ApiResponse(responseCode = "400", description = "No se pudo traer  el Listado de Usuarios o Usuario según su ROL. Comprobar la Solicitud", content = @Content),
				@ApiResponse(responseCode = "404", description = "El Listado de Usuarios o Usuario según su ROL no está Disponible ya que el recurso pedido no existe. Comprobar solicitud", content = @Content),
				@ApiResponse(responseCode = "500", description = "Se ha producido un error interno en el Servidor", content = @Content) })
		@GetMapping("/rol/{rol}")
		public Page<UsuarioEntity> getByRol(@PathVariable("rol") String rol, Pageable pageable) {
			return iUsuarioService.getByRol(rol, pageable);
		}

}