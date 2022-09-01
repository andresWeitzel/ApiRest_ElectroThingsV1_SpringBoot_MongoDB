package com.api.rest.v1.security.controllers;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.v1.security.dto.JwtDTO;
import com.api.rest.v1.security.dto.LoginUsuarioDTO;
import com.api.rest.v1.security.dto.SigninUsuarioDTO;
import com.api.rest.v1.security.entities.Usuario;
import com.api.rest.v1.security.enums.TipoRol;
import com.api.rest.v1.security.jwt.JwtProvider;
import com.api.rest.v1.security.services.UsuarioServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	JwtProvider jwtProvider;

	// ===============================================
	// ============= MÉTODOS HTTP ==============
	// ===============================================

	// =====================
	// ===== POST SIGNIN ===
	// =====================
	// ---INSERCIÓN DE USUARIOS---
	@ApiOperation(value = "Registro de Usuarios", notes = "Registro de Usuarios al Sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Registrado el Usuario Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Registrado el Usuario Correctamente"),
			@ApiResponse(code = 400, message = "No se pudo Registrar el Usuario. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Registrar el Usuario. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido registrar el usuario correctamente. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "La Inserción del Usuario no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
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
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody SigninUsuarioDTO signinUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Campos o Email Inválidos", HttpStatus.BAD_REQUEST);
		}

		if (usuarioServiceImpl.existsByUsername(signinUsuario.getUsername())) {
			return new ResponseEntity<String>("El Username del Usuario ya existe en la DB", HttpStatus.BAD_REQUEST);
		}

		if (usuarioServiceImpl.existsByEmail(signinUsuario.getEmail())) {
			return new ResponseEntity<String>("El Email del Usuario ya existe en la DB", HttpStatus.BAD_REQUEST);
		}

		if (signinUsuario.getNombre().isBlank() || signinUsuario.getApellido().isBlank() || signinUsuario.getUsername().isBlank()
				|| signinUsuario.getPassword().isBlank() || signinUsuario.getPassword().isBlank()
				|| signinUsuario.getEmail().isBlank()) {
			return new ResponseEntity<String>("No se permiten campos vacios", HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario(signinUsuario.getNombre(),signinUsuario.getApellido(), signinUsuario.getUsername(),
				passwordEncoder.encode(signinUsuario.getPassword()), signinUsuario.getEmail());

		Set<TipoRol> roles = new HashSet<>();

		if (signinUsuario.getRoles().contains("user") || signinUsuario.getRoles().contains("admin")
				|| signinUsuario.getRoles().contains("")) {

			roles.add(TipoRol.ROLE_USER);
		}

		if (signinUsuario.getRoles().contains("admin")) {
			roles.add(TipoRol.ROLE_ADMIN);
		}

		usuario.setRoles(roles);

		usuarioServiceImpl.addUsuario(usuario);

		return new ResponseEntity<String>("Usuario Insertado Correctamente", HttpStatus.CREATED);
	}
	
	
	
	// =====================
		// ===== POST LOGIN ===
		// =====================
		// ---VALIDACIÓN DE USUARIOS---
	@ApiOperation(value = "Acceso de Usuarios", notes = "Acceso de Usuarios al Sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Accedido al sistema Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Accedido al sistema Correctamente"),
			@ApiResponse(code = 400, message = "No se pudo acceder al sistema. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para acceder al sistema. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido acceder al sistema correctamente. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "El acceso al sistema no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
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
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuarioDTO loginUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Campos Inválidos", HttpStatus.BAD_REQUEST);
		}

		if (!(usuarioServiceImpl.existsByUsername(loginUsuario.getUsername()))) {
			return new ResponseEntity<String>("El Usuario no existe. Comprobar username y password!!",
					HttpStatus.BAD_REQUEST);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		JwtDTO jwtDto = new JwtDTO(jwt);

		return new ResponseEntity<JwtDTO>(jwtDto, HttpStatus.OK);
	}
	
	
	
	
	
	// ============================
	// ===== POST REFRESH TOKEN ===
	// ============================
	// ---VALIDACIÓN DE USUARIOS REFRESCADO---
	@ApiOperation(value = "Actualización de Token", notes = "Actualización de Token")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha Actualizado el Token Correctamente"),
			@ApiResponse(code = 201, message = "Se ha Registrado el Actualizado el Token Correctamente"),
			@ApiResponse(code = 400, message = "No se pudo Registrar el Actualizado el Token. Comprobar la Solicitud"),
			@ApiResponse(code = 401, message = "No está autorizado para Registrar el Actualizado el Token. Verificar credenciales"),
			@ApiResponse(code = 403, message = "No se ha podido Actualizar el Token correctamente. El servidor ha denegado esta operación"),
			@ApiResponse(code = 404, message = "La Actualización del Token no está Disponible ya que el recurso pedido no existe. Comprobar solicitud"),
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
	@PostMapping("/refresh-token")
	public ResponseEntity<?> refreshToken(@RequestBody JwtDTO jwtDto) throws ParseException {

		String token = jwtProvider.refreshToken(jwtDto);

		JwtDTO jwtRefresh = new JwtDTO(token);

		return new ResponseEntity<JwtDTO>(jwtRefresh, HttpStatus.OK);

	}

}
