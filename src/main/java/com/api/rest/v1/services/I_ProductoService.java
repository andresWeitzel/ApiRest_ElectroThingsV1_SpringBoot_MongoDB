package com.api.rest.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.rest.v1.entities.ProductoEntity;

public interface I_ProductoService {

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

	public abstract ProductoEntity findById(String id);

	public abstract Page<ProductoEntity> findByCodigo(String codigo, Pageable pageable);

	public abstract Page<ProductoEntity> findtByNombre(String nombre, Pageable pageable);

	public abstract Page<ProductoEntity> findByDescripcion(String descripcion, Pageable pageable);

	public abstract Page<ProductoEntity> findByCategoria(String categoria, Pageable pageable);

	public abstract Page<ProductoEntity> findByMarca(String marca, Pageable pageable);

	public abstract Page<ProductoEntity> findByHojaDatos(String hojaDatos, Pageable pageable);

	public abstract Page<ProductoEntity> findByStock(int stock, Pageable pageable);

	public abstract Page<ProductoEntity> findByPrecio(int precio, Pageable pageable);

}
