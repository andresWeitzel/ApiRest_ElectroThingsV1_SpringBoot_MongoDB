package com.api.rest.v1.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;



import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.rest.v1.entities.ProductoEntity;
import com.api.rest.v1.exceptions.producto.ProductoIdMismatchException;
import com.api.rest.v1.exceptions.producto.ProductoNotFoundException;
import com.api.rest.v1.repositories.I_ProductoRepository;


@Service
public class ProductoServiceImplementation implements I_ProductoService {

	@Autowired
	I_ProductoRepository iProductoRepositoryMongo;

	// =============== LOGS ====================
	private static final Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ProductoServiceImplementation.class);

	// =============== MÉTODOS CRUD ====================

	// Fecha y Hora Formateado
	LocalDate fecha = LocalDate.now();
	LocalTime hora = LocalTime.now();
	
	
	
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.US);

	// ==================
	// ===== INSERT =====
	// ==================
	@Override
	public void addProducto(ProductoEntity producto) {

		try {
			if (producto == null) {
				logger.error("ERROR addProducto : EL PRODUCTO " + producto + " ES NULO!!");
				throw new ProductoNotFoundException("EL PRODUCTO ES NULO");
			} else {
				producto.setFecha(fecha.format(formatoFecha));
				producto.setHora(hora.format(formatoHora));
				iProductoRepositoryMongo.save(producto);
				
				logger.info("SE HA INSERTADO CORRECTAMENTE EL PRODUCTO CON EL ID " + producto.getId());
			}
		} catch (Exception e) {
			logger.error("ERROR addProducto : EL PRODUCTO " + producto + " NO SE HA INSERTADO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO AGREGAR EL PRODUCTO ", e, false, true);
		}

	}

	// ==================
	// ===== UPDATE =====
	// ==================
	@Override
	public void updateProducto(ProductoEntity producto) {
		try {
			
			
			if (producto == null) {
				logger.error("ERROR updateProducto : EL PRODUCTO " + producto + " ES NULO!!");
				throw new ProductoNotFoundException("EL PRODUCTO ES NULO");
			
			}else {
				producto.setFecha(fecha.format(formatoFecha));
				producto.setHora(hora.format(formatoHora));
				iProductoRepositoryMongo.save(producto);
				
				logger.info("SE HA ACTUALIZADO CORRECTAMENTE EL PRODUCTO CON EL ID " + producto.getId());
			}
		} catch (Exception e) {
			logger.error("ERROR updateProducto : EL PRODUCTO " + producto + " NO SE HA ACTUALIZADO EN LA DB!!");
			throw new ProductoNotFoundException("NO SE PUDO ACTUALIZAR EL PRODUCTO ", e, false, true);
		}
	}

	// ==================
	// ===== DELETE =====
	// ==================
	public void deleteProducto(String id) {
		try {
			Optional<ProductoEntity> producto = iProductoRepositoryMongo.findById(id);
			
			//Si esta vacio es nulo
			if (producto.isEmpty()) {
				logger.error("ERROR deleteProducto : EL PRODUCTO CON EL ID " + id + " NO EXISTE!!");
				throw new ProductoIdMismatchException("EL PRODUCTO CON EL ID NO EXISTE EN LA DB");
			} else {
				iProductoRepositoryMongo.deleteById(id);
				
				logger.info("SE HA ELIMINADO CORRECTAMENTE EL PRODUCTO CON EL ID " + id);
			}
		} catch (Exception e) {
			logger.error("ERROR deleteProducto : EL PRODUCTO CON EL ID " + id
					+ " NO SE HA ELIMINADO DE LA DB!! . CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ELIMINAR EL PRODUCTO ", e, false, true);
		}
	}

	// ===================
	// ===== GET ALL =====
	// ===================
	// ------- LISTADO PAGINADO ---------
	public Page<ProductoEntity> getAllProductos(Pageable pageable) {
		try {
			return iProductoRepositoryMongo.findAll(pageable);
		} catch (Exception e) {
			logger.error("ERROR getAllProducto : NO SE HAN LISTADO LOS PRODUCTOS. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS ", e);
		}
	}

	@Override
	public ProductoEntity findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByCodigo(String codigo, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findtByNombre(String nombre, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByDescripcion(String descripcion, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByCategoria(String categoria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByMarca(String marca, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByHojaDatos(String hojaDatos, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByStock(int stock, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProductoEntity> findByPrecio(int precio, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
