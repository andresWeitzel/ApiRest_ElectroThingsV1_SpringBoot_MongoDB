package com.api.rest.v1.services.usuarios;

import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.rest.v1.entities.ProductoEntity;
import com.api.rest.v1.entities.UsuarioEntity;
import com.api.rest.v1.exceptions.producto.ProductoNotFoundException;
import com.api.rest.v1.exceptions.usuario.UsuarioIdMismatchException;
import com.api.rest.v1.exceptions.usuario.UsuarioNotFoundException;
import com.api.rest.v1.repositories.I_ProductoRepository;
import com.api.rest.v1.repositories.I_UsuarioRepository;
import com.api.rest.v1.services.productos.ProductoServiceImpl;

@Service
public class UsuarioServiceImpl implements I_UsuarioService {

	@Autowired
	I_UsuarioRepository iUsuarioRepositoryMongo;

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
	public void addUsuario(UsuarioEntity usuario) {

		try {
			if (usuario == null) {
				logger.error("ERROR addUsuario : EL USUARIO " + usuario + " ES NULO!!");
				throw new UsuarioNotFoundException("EL USUARIO ES NULO");
			} else {
				iUsuarioRepositoryMongo.save(usuario);
				logger.info("SE HA INSERTADO CORRECTAMENTE EL USUARIO CON EL ID " + usuario.getId());
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
	public void updateUsuario(UsuarioEntity usuario) {
		try {
			if (usuario == null) {
				logger.error("ERROR updateUsuario : EL USUARIO " + usuario + " ES NULO!!");
				throw new UsuarioNotFoundException("EL USUARIO ES NULO");
			} else {
				iUsuarioRepositoryMongo.save(usuario);
				logger.info("SE HA ACTUALIZADO CORRECTAMENTE EL USUARIO CON EL ID " + usuario.getId());
			}
		} catch (Exception e) {
			logger.error("ERROR updateUsuario : EL USUARIO " + usuario + " NO SE HA ACTUALIZADO EN LA DB!!");
			throw new UsuarioNotFoundException("NO SE PUDO ACTUALIZAR EL USUARIO ", e, false, true);
		}

	}

	// ==================
	// ===== DELETE =====
	// ==================
	@Override
	public void deleteUsuario(String id) {
		try {
			Optional<UsuarioEntity> usuario = iUsuarioRepositoryMongo.findById(id);

			if (usuario.isEmpty()) {
				logger.error("ERROR deleteUsuario : EL USUARIO " + usuario + " ES NULO!!");
				throw new UsuarioIdMismatchException("EL USUARIO CON EL ID NO EXISTE EN LA DB");
			} else {
				iUsuarioRepositoryMongo.deleteById(id);
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
	public Page<UsuarioEntity> getAllUsuarios(Pageable pageable) {

		try {
			Page<UsuarioEntity> usuariosPaginados = iUsuarioRepositoryMongo.findAll(pageable);

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
	public UsuarioEntity getById(String id) {
		try {
			Optional<UsuarioEntity> usuario = iUsuarioRepositoryMongo.findById(id);

			if (usuario.isEmpty() || id == " ") {
				logger.error("ERROR getById : EL USUARIO CON EL ID " + id + " NO EXISTE!!");
				throw new UsuarioIdMismatchException("EL USUARIO CON EL ID NO EXISTE EN LA DB");
			} else {
				return iUsuarioRepositoryMongo.findById(id).orElseThrow(UsuarioIdMismatchException::new);

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
	public Page<UsuarioEntity> getByNombre(String nombre, Pageable pageable) {
		try {
			Page<UsuarioEntity> usuariosPaginados = iUsuarioRepositoryMongo.findByNombre(nombre, pageable);

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
	public Page<UsuarioEntity> getByApellido(String apellido, Pageable pageable) {
		try {
			Page<UsuarioEntity> usuariosPaginados = iUsuarioRepositoryMongo.findByApellido(apellido, pageable);

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
	// ===== GET BY USER =====
	// =========================
	@Override
	public Page<UsuarioEntity> getByUser(String user, Pageable pageable) {
		try {
			Page<UsuarioEntity> usuariosPaginados = iUsuarioRepositoryMongo.findByUser(user, pageable);

			if (usuariosPaginados.isEmpty() || user == " ") {
				logger.error("ERROR getByUser : EL USUARIO CON EL USER " + user + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL USER " + user + " NO EXISTE EN LA DB");
			} else {
				return usuariosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByUser : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL USER SOLICITADO. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL USER " + user, e);
		}
	}
	
	
	// =========================
	// ===== GET BY USER =====
	// =========================
	@Override
	public UsuarioEntity getByUser(String user) {
		try {
			UsuarioEntity usuario = iUsuarioRepositoryMongo.findByUser(user);

			if (usuario == null ||  user == " ") {
				logger.error("ERROR getByUser : EL USUARIO CON EL USER " + user + " NO EXISTE!!");
				throw new UsuarioNotFoundException("EL USUARIO CON EL USER " + user + " NO EXISTE EN LA DB");
			} else {
				return usuario;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByUser : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL USER SOLICITADO. CAUSADO POR " + e);
			throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL USER " + user, e);
		}
	}

	// =========================
	// ===== GET BY PASSWORD =====
	// =========================
	@Override
	public Page<UsuarioEntity> getByPassword(String password, Pageable pageable) {
		try {
			Page<UsuarioEntity> usuariosPaginados = iUsuarioRepositoryMongo.findByPassword(password, pageable);

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
		// ===== GET BY ROL =====
		// =========================
		@Override
		public Page<UsuarioEntity> getByRol(String rol, Pageable pageable) {
			try {
				Page<UsuarioEntity> usuariosPaginados = iUsuarioRepositoryMongo.findByRol(rol, pageable);

				if (usuariosPaginados.isEmpty() || rol == " ") {
					logger.error("ERROR getByRol : EL USUARIO CON EL ROL " + rol + " NO EXISTE!!");
					throw new UsuarioNotFoundException("EL USUARIO CON EL ROL " + rol + " NO EXISTE EN LA DB");
				} else {
					return usuariosPaginados;

				}
			} catch (Exception e) {
				logger.error(
						"ERROR getByRol : NO SE HA ENCONTRADO EL USUARIO  SEGÚN EL ROL SOLICITADO. CAUSADO POR "
								+ e);
				throw new UsuarioNotFoundException("NO SE PUDO ENCONTRAR EL USUARIO CON EL ROL " + rol, e);
			}
		}

}
