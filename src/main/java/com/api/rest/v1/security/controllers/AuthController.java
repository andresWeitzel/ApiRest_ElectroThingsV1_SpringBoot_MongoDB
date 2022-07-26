package com.api.rest.v1.security.controllers;



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
import org.springframework.security.core.userdetails.UserDetails;
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
import com.api.rest.v1.security.entities.Rol;
import com.api.rest.v1.security.entities.Usuario;
import com.api.rest.v1.security.enums.TipoRol;
import com.api.rest.v1.security.jwt.JwtProvider;
import com.api.rest.v1.security.services.RolService;
import com.api.rest.v1.security.services.UsuarioServiceImpl;



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
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@Valid @RequestBody SigninUsuarioDTO signinUsuario, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity("Campos o Email Inválidos", HttpStatus.BAD_REQUEST);
		}

		if (usuarioServiceImpl.existsByUsername(signinUsuario.getUsername())) {
			return new ResponseEntity("El Username del Usuario ya existe en la DB", HttpStatus.BAD_REQUEST);
		}

		if (usuarioServiceImpl.existsByEmail(signinUsuario.getEmail())) {
			 return new ResponseEntity("El Email del Usuario ya existe en la DB", HttpStatus.BAD_REQUEST);
		}
		
		if(signinUsuario.getNombre().isBlank() 
				|| signinUsuario.getUsername().isBlank() 
				|| signinUsuario.getPassword().isBlank()
				|| signinUsuario.getPassword().isBlank()
				|| signinUsuario.getEmail().isBlank())
		{
			return new ResponseEntity("No se permiten campos vacios", HttpStatus.BAD_REQUEST);
		}
			
		Usuario usuario = new Usuario(signinUsuario.getNombre(), signinUsuario.getUsername()
				, passwordEncoder.encode(signinUsuario.getPassword()),signinUsuario.getEmail());
		
		Set<Rol> roles = new HashSet<>();
		
		if(signinUsuario.getRoles().contains("user") 
				|| signinUsuario.getRoles().contains("admin") 
				|| signinUsuario.getRoles().contains("")) {
    		
    		roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
    	}

		if (signinUsuario.getRoles().contains("admin")) {
			roles.add(rolService.getByRol(TipoRol.ROLE_ADMIN).get());
		}
			
		usuario.setRoles(roles);
		
		usuarioServiceImpl.addUsuario(usuario);
		
		return new ResponseEntity("Usuario Insertado Correctamente", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuarioDTO loginUsuario, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity("Campos Inválidos", HttpStatus.BAD_REQUEST);
		}
			

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		//JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		JwtDTO jwtDto = new JwtDTO(jwt);

		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

}
