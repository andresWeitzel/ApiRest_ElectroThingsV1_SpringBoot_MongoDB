package com.api.rest.v1.security.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.rest.v1.exceptions.producto.ProductoNotFoundException;
import com.api.rest.v1.security.dto.SigninUsuarioDTO;
import com.api.rest.v1.security.entities.Rol;
import com.api.rest.v1.security.entities.Usuario;
import com.api.rest.v1.security.enums.TipoRol;
import com.api.rest.v1.security.exc.usuario.UsuarioIdMismatchException;
import com.api.rest.v1.security.exc.usuario.UsuarioNotFoundException;
import com.api.rest.v1.security.repositories.I_UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements I_UsuarioService {

	@Autowired
	I_UsuarioRepository iUsuarioRepository;
	
	@Autowired
	RolService rolService;

	// =============== LOGS ====================
	private static final Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(UsuarioServiceImpl.class);
	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	// ==================
	// ===== INSERT =====
	// ==================
	@Override
	public void addUsuarioDTO(SigninUsuarioDTO usuarioDTO) {

		try {
			if (usuarioDTO == null) {
				logger.error("ERROR addUsuario : EL USUARIO " + usuarioDTO + " ES NULO!!");
				throw new UsuarioNotFoundException("EL USUARIO ES NULO");
			} else {

				Usuario usuarioEncode = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getUsername(),
						new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()), usuarioDTO.getEmail());

				Set<Rol> roles = new HashSet<>();

				if (usuarioDTO.getRoles().contains("user")) {
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				if (usuarioDTO.getRoles().contains("admin")) {
					roles.add(rolService.getByRol(TipoRol.ROLE_ADMIN).get());
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				usuarioEncode.setRoles(roles);

				iUsuarioRepository.save(usuarioEncode);
			}

		} catch (Exception e) {
			logger.error("ERROR addUsuario : EL USUARIO " + usuarioDTO + " NO SE HA INSERTADO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO AGREGAR EL USUARIO ", e, false, true);
		}

	}
	
	
	// ==================
	// ===== INSERT =====
	// ==================
	@Override
	public void addUsuario(Usuario usuario) {

		try {
			if (usuario == null) {
				logger.error("ERROR addUsuario : EL USUARIO " + usuario + " ES NULO!!");
				throw new UsuarioNotFoundException("EL USUARIO ES NULO");
			}else if (usuario.getNombre() == "" || usuario.getApellido() == ""
					|| usuario.getPassword() == "" || usuario.getEmail() == ""){
				logger.error("ERROR addProducto : LOS VALORES DE LOS CAMPOS DEL USUARIO " 
					+ usuario + " NO SON VÁLIDOS!!");
				throw new UsuarioNotFoundException("VALORES DE CAMPOS NO VÁLIDOS");
			
			} else {

	
				iUsuarioRepository.save(usuario);
			}

		} catch (Exception e) {
			logger.error("ERROR addUsuario : EL USUARIO " + usuario + " NO SE HA INSERTADO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO AGREGAR EL USUARIO ", e, false, true);
		}

	}

	// ==================
	// ===== UPDATE =====
	// ==================
	@Override
	public void updateUsuario(ObjectId id, SigninUsuarioDTO usuarioDTO) {
		try {
			if (usuarioDTO == null) {
				logger.error("ERROR updateUsuario : EL USUARIO " + usuarioDTO + " ES NULO!!");
				throw new UsuarioNotFoundException("EL USUARIO ES NULO");
			}else if (id == null){
				logger.error("ERROR updateUsuario : EL ID  ES NULO!!");
				throw new UsuarioNotFoundException("EL ID DEL USUARIO ES NULO");
			
			}else if (usuarioDTO.getNombre() == "" || usuarioDTO.getApellido() == ""
					|| usuarioDTO.getPassword() == "" || usuarioDTO.getEmail() == ""){
				logger.error("ERROR updateProducto : LOS VALORES DE LOS CAMPOS DEL USUARIO " 
					+ usuarioDTO + " NO SON VÁLIDOS!!");
				throw new UsuarioNotFoundException("VALORES DE CAMPOS NO VÁLIDOS");
			
			}else {
				
				Usuario usuarioEncode = new Usuario(id, usuarioDTO.getNombre(), usuarioDTO.getUsername(),
						new BCryptPasswordEncoder().encode(usuarioDTO.getPassword()), usuarioDTO.getEmail());

				Set<Rol> roles = new HashSet<>();

				if (usuarioDTO.getRoles().contains("user")) {
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				if (usuarioDTO.getRoles().contains("admin")) {
					roles.add(rolService.getByRol(TipoRol.ROLE_ADMIN).get());
					roles.add(rolService.getByRol(TipoRol.ROLE_USER).get());
				}

				usuarioEncode.setRoles(roles);

				iUsuarioRepository.save(usuarioEncode);
				
				logger.info("SE HA ACTUALIZADO CORRECTAMENTE EL USUARIO " + usuarioDTO);
			}
		} catch (Exception e) {
			logger.error("ERROR updateUsuario : EL USUARIO " + usuarioDTO + " NO SE HA ACTUALIZADO EN LA DB!!");
			throw new UsuarioNotFoundException("NO SE PUDO ACTUALIZAR EL USUARIO ", e, false, true);
		}

	}

	// ==================
	// ===== DELETE =====
	// ==================
	@Override
	public void deleteUsuario(ObjectId id) {
		try {
			Optional<Usuario> usuario = iUsuarioRepository.findById(id);

			if (usuario.isEmpty()) {
				logger.error("ERROR deleteUsuario : EL USUARIO " + usuario + " ES NULO!!");
				throw new UsuarioIdMismatchException("EL USUARIO CON EL ID NO EXISTE EN LA DB");
			} else {
				iUsuarioRepository.deleteById(id);
				logger.info("SE HA ELIMINADO CORRECTAMENTE EL USUARIO CON EL ID " + id);
			}
		} catch (Exception e) {
			logger.error("ERROR deleteUsuario : EL USUARIO CON EL ID " + id + " NO SE HA ELIMINADO DE LA DB!!");
			throw new UsuarioNotFoundException("NO SE PUDO ELIMINAR EL USUARIO ", e, false, true);
		}

	}

	// ===================
	// ===== GET ALL ====
	// ===================
	// ------- LISTADO PAGINADO ---------
	@Override
	public Page<Usuario> getAllUsuario(Pageable pageable) {

		try {
			Page<Usuario> usuariosPaginados = iUsuarioRepository.findAll(pageable);

			// Si esta vacio es nulo
			if (usuariosPaginados.isEmpty()) {
				logger.error("ERROR getAllUsuarios : NO SE HAN LISTADO LOS PRODUCTOS!!");
				throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE USUARIOS");
			} else {
				return usuariosPaginados;
			}

		} catch (Exception e) {
			logger.error("ERROR getAllUsuario : NO SE HAN LISTADO LOS USUARIOS. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE USUARIOS ", e);
		}

	}
	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// =====================
	// ===== GET BY ID =====
	// =====================
	@Override
	public Optional<Usuario> getById(ObjectId id) {
		try {
			Optional<Usuario> usuario = iUsuarioRepository.findById(id);

			if (usuario.isEmpty() || id == null) {
				logger.error("ERROR getById : EL USUARIO CON EL ID " + id + " NO EXISTE!!");
				throw new UsuarioIdMismatchException("EL USUARIO CON EL ID NO EXISTE EN LA DB");
			} else {
				return iUsuarioRepository.findById(id);

			}
		} catch (Exception e) {
			logger.error("ERROR getById : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL ID SOLICITADO. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL ID " + id, e);
		}
	}

	// =========================
	// ===== GET BY NOMBRE =====
	// =========================
	@Override
	public Page<Usuario> getByNombre(String nombre, Pageable pageable) {
		try {
			Page<Usuario> usuariosPaginados = iUsuarioRepository.findByNombre(nombre, pageable);

			if (usuariosPaginados.isEmpty() || nombre == " ") {
				logger.error("ERROR getByNombre : EL USUARIO CON EL NOMBRE " + nombre + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL NOMBRE " + nombre + " NO EXISTE EN LA DB");
			} else {
				return usuariosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByNombre : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL NOMBRE SOLICITADO. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL NOMBRE " + nombre, e);
		}
	}

	// =========================
	// ===== GET BY APELLIDO =====
	// =========================
	@Override
	public Page<Usuario> getByApellido(String apellido, Pageable pageable) {
		try {
			Page<Usuario> usuariosPaginados = iUsuarioRepository.findByApellido(apellido, pageable);

			if (usuariosPaginados.isEmpty() || apellido == " ") {
				logger.error("ERROR getByApellido : EL USUARIO CON EL APELLIDO " + apellido + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL APELLIDO " + apellido + " NO EXISTE EN LA DB");
			} else {
				return usuariosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByApellido : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL APELLIDO SOLICITADO. CAUSADO POR "
							+ e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL APELLIDO " + apellido, e);
		}
	}

	// =========================
	// ===== GET BY USERNAME =====
	// =========================
	@Override
	public Page<Usuario> getByUsername(String username, Pageable pageable) {
		try {
			Page<Usuario> usuariosPaginados = iUsuarioRepository.findByUsername(username, pageable);

			if (usuariosPaginados.isEmpty() || username == " ") {
				logger.error("ERROR getByUsername : EL USUARIO CON EL USERNAME " + username + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL USERNAME " + username + " NO EXISTE EN LA DB");
			} else {
				return usuariosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByUser : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL USERNAME SOLICITADO. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL USERNAME " + username, e);
		}
	}
	
	
	// =========================
	// ===== GET BY USERNAME =====
	// =========================
	@Override
	public Optional<Usuario> getByUsername(String username) {
		try {
			Optional<Usuario> usuario = iUsuarioRepository.findByUsername(username);

			if (usuario == null ||  username == " ") {
				logger.error("ERROR getByUsername : EL USUARIO CON EL USERNAME " + username + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL USERNAME " + username + " NO EXISTE EN LA DB");
			} else {
				return usuario;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByUsername : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL USERNAME SOLICITADO. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL USERNAME " + username, e);
		}
	}

	// =========================
	// ===== GET BY PASSWORD =====
	// =========================
	@Override
	public Page<Usuario> getByPassword(String password, Pageable pageable) {
		try {
			Page<Usuario> usuariosPaginados = iUsuarioRepository.findByPassword(password, pageable);

			if (usuariosPaginados.isEmpty() || password == " ") {
				logger.error("ERROR getByPassword : EL USUARIO CON EL PASSWORD " + password + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL PASSWORD " + password + " NO EXISTE EN LA DB");
			} else {
				return usuariosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByPassword : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL PASSWORD SOLICITADO. CAUSADO POR "
							+ e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL PASSWORD " + password, e);
		}
	}
	
	
	
	// =========================
	// ===== GET BY EMAIL ======
	// =========================
	@Override
	public Page<Usuario> getByEmail(String email, Pageable pageable) {
		try {
			Page<Usuario> usuariosPaginados = iUsuarioRepository.findByEmail(email, pageable);

			if (usuariosPaginados.isEmpty() || email == " ") {
				logger.error("ERROR getByEmail : EL USUARIO CON EL EMAIL " + email + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL EMAIL " + email + " NO EXISTE EN LA DB");
			} else {
				return usuariosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByEmail : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL EMAIL SOLICITADO. CAUSADO POR "
							+ e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL EMAIL " + email, e);
		}
	}
	
	
	// ============= MÉTODOS DE COMPROBACIONES ===================

	
	
	// ==============================
	// ===== EXISTS BY USERNAME =====
	// ==============================
	public boolean existsByUsername(String username) {
		
		try {
			
			boolean checkUsuario = iUsuarioRepository.existsByUsername(username);
			
			if(username.isEmpty() || username == " ") {
				logger.error("ERROR existsByUsername : EL USUARIO CON EL USERNAME " + username + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL USERNAME " + username + " NO EXISTE EN LA DB");
			} else {
				return checkUsuario;

			}
			
		} catch (Exception e) {
			logger.error(
					"ERROR existsByUsername : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL USERNAME SOLICITADO. CAUSADO POR "
							+ e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL USERNAME " + username, e);
		
		}
	}
	
	
	// ===========================
	// ===== EXISTS BY EMAIL =====
	// ===========================
	public boolean existsByEmail(String email) {
		
		try {
			
			boolean checkEmail = iUsuarioRepository.existsByEmail(email);
			
			if(email.isEmpty() || email == " ") {
				logger.error("ERROR existsByEmail : EL USUARIO CON EL EMAIL " + email + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL EMAIL " + email + " NO EXISTE EN LA DB");
			} else {
				return checkEmail;

			}
			
		} catch (Exception e) {
			logger.error(
					"ERROR existsByEmail : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL EMAIL SOLICITADO. CAUSADO POR "
							+ e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL EMAIL " + email, e);
		
		}
	}
	
  

}


