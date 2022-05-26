package com.api.rest.v1.services.productos;

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
public class ProductoServiceImpl implements I_ProductoService {

	@Autowired
	I_ProductoRepository iProductoRepositoryMongo;

	// =============== LOGS ====================
	private static final Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ProductoServiceImpl.class);

	// Fecha y Hora Formateado
	LocalDate fecha = LocalDate.now();
	LocalTime hora = LocalTime.now();

	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.US);

	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

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

			} else {
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
	@Override
	public void deleteProducto(String id) {
		try {
			Optional<ProductoEntity> producto = iProductoRepositoryMongo.findById(id);

			// Si esta vacio es nulo
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
	// ===== GET ALL ====
	// ===================
	// ------- LISTADO PAGINADO ---------
	@Override
	public Page<ProductoEntity> getAllProductos(Pageable pageable) {
		try {

			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findAll(pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty()) {
				logger.error("ERROR getAllProducto : NO SE HAN LISTADO LOS PRODUCTOS!!");
				throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS");
			} else {
				return productosPaginados;

			}

		} catch (Exception e) {
			logger.error("ERROR getAllProducto : NO SE HAN LISTADO LOS PRODUCTOS. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS ", e);
		}
	}

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	// =====================
	// ===== GET BY ID =====
	// =====================
	@Override
	public ProductoEntity getById(String id) {
		try {
			Optional<ProductoEntity> producto = iProductoRepositoryMongo.findById(id);

			// Si esta vacio es nulo
			if (producto.isEmpty() || id == " ") {
				logger.error("ERROR getById : EL PRODUCTO CON EL ID " + id + " NO EXISTE!!");
				throw new ProductoIdMismatchException("EL PRODUCTO CON EL ID NO EXISTE EN LA DB");
			} else {
				return iProductoRepositoryMongo.findById(id).orElseThrow(ProductoIdMismatchException::new);

			}
		} catch (Exception e) {
			logger.error("ERROR getById : NO SE HA ENCONTRADO EL PRODUCTO SEGÚN EL ID SOLICITADO. CAUSADO POR " + e);
			throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL PRODUCTO CON EL ID " + id, e);
		}
	}

	// ==========================
	// ===== GET BY CODIGO =====
	// ==========================
	@Override
	public Page<ProductoEntity> getByCodigo(String codigo, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByCodigo(codigo, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || codigo == " ") {
				logger.error("ERROR getByCodigo : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN EL CODIGO " + codigo
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO CON EL CODIGO " + codigo);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByCodigo : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN EL CODIGO SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO CON EL CODIGO " + codigo, e);
		}
	}

	// ==========================
	// ===== GET BY NOMBRE =====
	// ==========================
	@Override
	public Page<ProductoEntity> getByNombre(String nombre, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByNombre(nombre, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || nombre == " ") {
				logger.error("ERROR getByNombre : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN EL NOMBRE " + nombre
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO CON EL NOMBRE " + nombre);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByNombre : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN EL NOMBRE SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO CON EL NOMBRE " + nombre, e);
		}
	}

	// ===============================
	// ===== GET BY DESCRIPCION =====
	// ===============================
	@Override
	public Page<ProductoEntity> getByDescripcion(String descripcion, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByDescripcion(descripcion, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || descripcion == " ") {
				logger.error("ERROR getByDescripcion : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA DESCRIPCIÓN "
						+ descripcion + " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA DESCRIPCIÓN " + descripcion);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByDescripcion : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA DESCRIPCIÓN SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA DESCRIPCIÓN " + descripcion, e);
		}
	}

	// =============================
	// ===== GET BY CATEGORIA =====
	// =============================
	@Override
	public Page<ProductoEntity> getByCategoria(String categoria, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByCategoria(categoria, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || categoria == " ") {
				logger.error("ERROR getByCategoria : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA CATEGORIA " + categoria
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA CATEGORIA " + categoria);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByCategoria : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA CATEGORIA SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA CATEGORIA " + categoria, e);
		}
	}

	// ==========================
	// ===== GET BY MARCA ======
	// ==========================
	@Override
	public Page<ProductoEntity> getByMarca(String marca, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByMarca(marca, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || marca == " ") {
				logger.error("ERROR getByMarca : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA MARCA " + marca
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA MARCA " + marca);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByMarca : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA MARCA SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA MARCA " + marca, e);
		}

	}
	

	// =========================
	// ===== GET BY IMAGEN =====
	// =========================
	@Override
	public Page<ProductoEntity> getByImagen(String imagen, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByImagen(imagen, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || imagen == " ") {
				logger.error("ERROR getByImagen : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA IMAGEN "
						+ imagen + " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA IMAGEN " + imagen);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByImagen : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA IMAGEN SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA IMAGEN " + imagen, e);
		}
	}
	

	// =================================
	// ===== GET BY HOJA DE DATOS =====
	// =================================
	@Override
	public Page<ProductoEntity> getByHojaDatos(String hojaDatos, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByHojaDatos(hojaDatos, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || hojaDatos == " ") {
				logger.error("ERROR getByHojaDatos : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA HOJA DE DATOS "
						+ hojaDatos + " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA HOJA DE DATOS " + hojaDatos);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByHojaDatos : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA HOJA DE DATOS SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN LA HOJA DE DATOS " + hojaDatos, e);
		}
	}

	// ==========================
	// ===== GET BY STOCK ======
	// ==========================
	@Override
	public Page<ProductoEntity> getByStock(int stock, Pageable pageable) {
		try {
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByStock(stock, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || stock < 0) {
				logger.error("ERROR getByStock : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU STOCK " + stock
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU STOCK " + stock);
			} else {
				return productosPaginados;

			}
		} catch (Exception e) {
			logger.error(
					"ERROR getByStock : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU STOCK SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU STOCK " + stock, e);
		}
	}

	// ==========================
	// ===== GET BY PRECIO =====
	// ==========================
	@Override
	public Page<ProductoEntity> getByPrecio(int precio, Pageable pageable) {
		
		try {
			
		Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByPrecio(precio, pageable);

		// Si esta vacio es nulo
		if (productosPaginados.isEmpty() || precio < 0) {
			logger.error("ERROR getByPrecio : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU PRECIO " + precio
					+ " NO EXISTE!!");
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU PRECIO " + precio);
		} else {
			return productosPaginados;
		}
	}catch(Exception e)
	{
		logger.error(
				"ERROR getByPrecio : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU PRECIO SOLICITADO. CAUSADO POR "
						+ e);
		throw new ProductoNotFoundException(
				"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU PRECIO " + precio, e);
	}
	}

	// ==========================
	// ===== GET BY FECHA =====
	// ==========================
	@Override
	public Page<ProductoEntity> getByFecha(String fecha, Pageable pageable) {
		try {
			
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByFecha(fecha, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || fecha == " ") {
				logger.error("ERROR getByFecha : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU FECHA " + fecha
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU FECHA " + fecha);
			} else {
				return productosPaginados;
			}
		}catch(Exception e)
		{
			logger.error(
					"ERROR getByFecha : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU FECHA SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU FECHA " + fecha, e);
		}
	}

	// ==========================
	// ===== GET BY HORA =====
	// ==========================
	@Override
	public Page<ProductoEntity> getByHora(String hora, Pageable pageable) {
	try {
			
			Page<ProductoEntity> productosPaginados = iProductoRepositoryMongo.findByHora(hora, pageable);

			// Si esta vacio es nulo
			if (productosPaginados.isEmpty() || hora == " ") {
				logger.error("ERROR getByHora : EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU HORA " + hora
						+ " NO EXISTE!!");
				throw new ProductoNotFoundException(
						"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU HORA " + hora);
			} else {
				return productosPaginados;
			}
		}catch(Exception e)
		{
			logger.error(
					"ERROR getByHora : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU HORA SOLICITADO. CAUSADO POR "
							+ e);
			throw new ProductoNotFoundException(
					"NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS O PRODUCTO SEGÚN SU HORA " + hora, e);
		}
	}
	
	// =============== MÉTODOS PARA GRAFICO ====================

		// ===============
		// ===== GET =====
		// ===============
		// ------ STOCK POR MARCA ------
		public int getStockPorMarca(Page<ProductoEntity> listComp, String marca) {

			try {
				return (int) (listComp.stream().filter(obj -> obj.getMarca().equalsIgnoreCase(marca))
						.mapToInt(obj -> obj.getStock()).sum());	
			} catch (Exception e) {
			    logger.error("ERROR getStockPorMarca : NO SE HA ENCONTRADO EL LISTADO DE PRODUCTOS CON EL STOCK POR MARCA SOLICITADO. CAUSADO POR "+e);
				throw new ProductoNotFoundException("NO SE PUDO ENCONTRAR EL LISTADO DE PRODUCTOS CON EL STOCK POR MARCA SOLICITADO ", e);
			}
			
		}
	
	
	
	
	
	
	
	

}
