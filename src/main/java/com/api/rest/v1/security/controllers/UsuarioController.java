package com.api.rest.v1.security.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.v1.security.dto.SigninUsuarioDTO;
import com.api.rest.v1.security.entities.Usuario;
import com.api.rest.v1.security.services.UsuarioServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/v1/admin/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	// ==============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ==============================================

	// ================
	// ===== POST =====
	// ================
	@ApiOperation(value = "Inserción de un Usuario", notes="Devolución del Usuario agregado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Insertado el Usuario Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Insertado el Usuario Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Insertar el Usuario. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para insertar un Usuario. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido insertar el Usuario. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Inserción del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405,  message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407,  message = "La autenticación debe estar hecha a partir de un proxy."),
			@ApiResponse(code = 408,  message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409,  message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410,  message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422,  message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503,  message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505,  message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507,  message = "Almacenamiento Insuficiente por parte del Servidor.")
			})
	@PostMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> addUsuario(@RequestBody SigninUsuarioDTO usuarioDTO) {

		try {
			usuarioService.addUsuarioDTO(usuarioDTO);

			return new ResponseEntity<String>("Se ha agregado el usuario en la db correctamente",HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// ================
	// ===== PUT =====
	// ================
	@ApiOperation(value = "Actualización de un Usuario", notes="Devolución del Usuario Actualizado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Actualizado el Usuario Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Actualizado el Usuario Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Actualizar el Usuario. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para actualizar el Usuario. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido Actualizar el Usuario. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Actualización del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405,  message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407,  message = "La autenticación debe estar hecha a partir de un proxy."),
			@ApiResponse(code = 408,  message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409,  message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410,  message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422,  message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503,  message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505,  message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507,  message = "Almacenamiento Insuficiente por parte del Servidor.")
			})
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updateUsuario(@PathVariable("id") String id,@RequestBody SigninUsuarioDTO usuarioDTO) {

		try {
			usuarioService.updateUsuario(id, usuarioDTO);

			return new ResponseEntity<String>("Se ha Actualizado el usuario en la db correctamente",HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// ================
	// ===== DELETE =====
	// ================
	@ApiOperation(value = "Eliminación de un Usuario", notes="Devolución del Usuario Eliminado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se ha Eliminado el Usuario Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Eliminado el Usuario Correctamente"),
			@ApiResponse(code = 400,  message = "No se pudo Eliminar el Usuario. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para Eliminar el Usuario. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido Eliminar el Usuario. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "La Eliminacion del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405,  message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407,  message = "La autenticación debe estar hecha a partir de un proxy."),
			@ApiResponse(code = 408,  message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409,  message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410,  message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422,  message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503,  message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505,  message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507,  message = "Almacenamiento Insuficiente por parte del Servidor.")
			})
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUsuario(@PathVariable("id") String id) {

		try {
			usuarioService.deleteUsuario(id);

			return new ResponseEntity<String>("Se ha eliminado el usuario en la db correctamente",HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// =================
	// ===== GET ALL ====
	// =================
	@ApiOperation(value = "Listado Paginado de Usuarios", notes="Devolución del Listado Paginado de Usuarios")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Se han Listado los Usuarios Correctamente"),
			@ApiResponse(code = 201, message = "Se han Listado los Usuarios Correctamente"),
			@ApiResponse(code = 400,  message = "No se ha podido Listar los Usuarios. Comprobar la Solicitud"),
			@ApiResponse(code = 401,  message = "No está autorizado para Listar los Usuarios. Verificar credenciales"),
			@ApiResponse(code = 403,  message = "No se ha podido Listar los Usuarios. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404,  message = "El Listado de Usuarios no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
			@ApiResponse(code = 405,  message = "El recurso ha sido deshabilitado."),
			@ApiResponse(code = 407,  message = "La autenticación debe estar hecha a partir de un proxy."),
			@ApiResponse(code = 408,  message = "Se ha superado el tiempo de espera entre la solicitud y el servidor. Intentar nuevamente"),
			@ApiResponse(code = 409,  message = "Se ha generado un conflicto en el servidor. Intentar nuevamente"),
			@ApiResponse(code = 410,  message = "El Contenido solicitado se ha Eliminado del Servidor."),
			@ApiResponse(code = 422,  message = "Se ha producido un error ya que los valores pasados no son correctos. Verificar campos"),
			@ApiResponse(code = 500,  message = "Se ha producido un error interno en el Servidor"),
			@ApiResponse(code = 503,  message = "Se ha producido un error de sobrecarga o mantenimiento en el Servidor. Intentar luego."),
			@ApiResponse(code = 505,  message = "Versión HTTP no es soportada por el Servidor."),
			@ApiResponse(code = 507,  message = "Almacenamiento Insuficiente por parte del Servidor.")
			})
	@GetMapping("/listado")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Page<Usuario> getAllUsuario(Pageable pageable) {

		return usuarioService.getAllUsuario(pageable);

	}

}
