

package com.api.rest.v1.security.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.rest.v1.security.entities.Usuario;




public interface I_UsuarioService {
	

	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	public abstract void addUsuario(Usuario usuario);

	public abstract void updateUsuario(Usuario usuario);

	public abstract void deleteUsuario(String id);

	public abstract Page<Usuario> getAllUsuario(Pageable pageable);

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	public abstract Optional<Usuario> getById(String id);

	public abstract Page<Usuario> getByNombre(String nombre, Pageable pageable);
	
	public abstract Page<Usuario> getByApellido(String apellido, Pageable pageable);
	
	public abstract Page<Usuario> getByUsername(String username, Pageable pageable);
	
	public abstract Optional<Usuario> getByUsername(String username);
	
	public abstract Page<Usuario> getByPassword(String password, Pageable pageable);
	
	public abstract Page<Usuario> getByEmail(String email, Pageable pageable);


	

}

