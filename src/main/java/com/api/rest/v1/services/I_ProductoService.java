package com.api.rest.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import com.api.rest.v1.entities.ProductoEntity;

public interface I_ProductoService {

	/*SPRING DATA MONGO TRABAJA LA PERSISTENCIA DIFERENTE A SPRING DATA JPA. SE DESARROLLA 
	 * UNA INTERFAZ PARA EL SERVICE YA QUE USAREMOS LOS METODOS DEFINIDOS EN LA MISMA. 
	 * SE PUEDE TENER PROBLEMAS AL PISAR LOS NOMBRES DE LOS METODOS DEL REPOSITORIO DE MONGO
	 * POR ESO LA REDEFINICION AQUI
	 */
	
	
	// ===============================================
	// ============= MÉTODOS HTTP CRUD ==============
	// ===============================================

	public abstract void addProducto(ProductoEntity producto);

	public abstract void updateProducto(ProductoEntity producto);

	public abstract void deleteProducto(String id);

	public abstract Page<ProductoEntity> getAllProductos(Pageable pageable);

	// ==================================================
	// ============= MÉTODOS HTTP BÚSQUEDA =============
	// ==================================================

	public abstract ProductoEntity getById(String id);

	public abstract Page<ProductoEntity> getByCodigo(String codigo, Pageable pageable);

	public abstract Page<ProductoEntity> getByNombre(String nombre, Pageable pageable);

	public abstract Page<ProductoEntity> getByDescripcion(String descripcion, Pageable pageable);

	public abstract Page<ProductoEntity> getByCategoria(String categoria, Pageable pageable);

	public abstract Page<ProductoEntity> getByMarca(String marca, Pageable pageable);

	public abstract Page<ProductoEntity> getByImagen(String imagen, Pageable pageable);
	
	public abstract Page<ProductoEntity> getByHojaDatos(String hojaDatos, Pageable pageable);

	public abstract Page<ProductoEntity> getByStock(int stock, Pageable pageable);

	public abstract Page<ProductoEntity> getByPrecio(int precio, Pageable pageable);
	
	public abstract Page<ProductoEntity> getByFecha(String fecha, Pageable pageable);
	
	public abstract Page<ProductoEntity> getByHora(String hora, Pageable pageable);
	
	
	// ==================================================
	// ============= MÉTODOS GRÁFICO =============
	// ==================================================

	public int getStockPorMarca(Page<ProductoEntity> listComp, String marca);

}
