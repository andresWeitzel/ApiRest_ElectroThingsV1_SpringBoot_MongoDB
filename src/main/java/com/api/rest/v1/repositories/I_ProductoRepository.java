package com.api.rest.v1.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.rest.v1.entities.ProductoEntity;

@Repository
public interface I_ProductoRepository extends MongoRepository<ProductoEntity, String> {

	/*
	 * LOS METODOS OPTIONAL Y LAS ANNOTATIONS @QUERY(JSON Query Methods) LOS
	 * IMPLEMENTA MONGOREPOSITORY.EL MISMO NO DESARROLLA CADA METODO PARA CADA CAMPO
	 * DE NUESTRA ENTIDAD COMO LO HACE JPA, ENTONCES LO DESARROLLAMOS NOSOTROS.
	 * DEFINIMOS CADA METODO DE BUSQUEDA PARA CADA CAMPO. TODOS PAGINADOS MENOS LA
	 * BUSQUEDA POR ID
	 * 
	 * MONGOREPO IMPLEMENTA PAGINADO Y SORTING
	 * 
	 * APLICAMOS REGEX PARA NO DISTINGUIR MAYUSCULAS, MINUSCULAS Y BUSQUEDA TIPO
	 * LIKE.
	 * 
	 */

	// ==================== METODOS DE BUSQUEDA =======================

	Page<ProductoEntity> findAll(Pageable pageable);

	@Query(value = "{ $or : [ {'codigo': {$regex : ?0, $options: 'i'}}" + ",{'nombre': {$regex : ?0, $options: 'i'}}"
			+ ",{'descripcion': {$regex : ?0, $options: 'i'}}" + ",{'categoria': {$regex : ?0, $options: 'i'}}"
			+ ",{'marca': {$regex : ?0, $options: 'i'}} ] }")
	Page<ProductoEntity> findAllFilter(String filtro, Pageable pageable);

	@Query(value = "{ $nor : [ {'codigo': {$regex : ?0, $options: 'i'}}"
			+ ",{'nombre': {$regex : ?0, $options: 'i'}}" + ",{'descripcion': {$regex : ?0, $options: 'i'}}"
			+ ",{'categoria': {$regex : ?0, $options: 'i'}}" + ",{'marca': {$regex : ?0, $options: 'i'}} ] }")
	Page<ProductoEntity> findAllExcludeFilter(String excluirFiltro, Pageable pageable);

	@Query("{'id': ?0}")
	Optional<ProductoEntity> findById(String id);

	@Query(value = "{'codigo': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByCodigo(String codigo, Pageable pageable);

	@Query(value = "{'nombre': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByNombre(String nombre, Pageable pageable);

	@Query(value = "{'descripcion': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByDescripcion(String descripcion, Pageable pageable);

	@Query(value = "{'categoria': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByCategoria(String categoria, Pageable pageable);

	@Query(value = "{'marca': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByMarca(String marca, Pageable pageable);

	@Query(value = "{'imagen': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByImagen(String imagen, Pageable pageable);

	@Query(value = "{'hojaDatos': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByHojaDatos(String hojaDatos, Pageable pageable);

	@Query("{'stock': ?0}")
	Page<ProductoEntity> findByStock(int stock, Pageable pageable);

	@Query("{'stock': { $gt : 0, $lt : ?0}}")
	Page<ProductoEntity> findByStockFilter(int maxStock, Pageable pageable);

	@Query("{'stock': { $gt : ?0, $lt : ?1}}")
	Page<ProductoEntity> findByStockFilter(int minStock, int maxStock, Pageable pageable);

	@Query("{'precio': ?0}")
	Page<ProductoEntity> findByPrecio(int precio, Pageable pageable);

	@Query("{'precio': { $gt : 0, $lt : ?0}}")
	Page<ProductoEntity> findByPrecioFilter(int maxPrecio, Pageable pageable);

	@Query("{'precio': { $gt : ?0, $lt : ?1}}")
	Page<ProductoEntity> findByPrecioFilter(int minPrecio, int maxPrecio, Pageable pageable);

	@Query(value = "{'fecha': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByFecha(String fecha, Pageable pageable);

	@Query(value = "{'hora': {$regex : ?0, $options: 'i'}}")
	Page<ProductoEntity> findByHora(String hora, Pageable pageable);

	@Query(value = "{'fecha': {$regex : ?0, $options: 'i'}, 'hora': {$regex : ?1, $options: 'i'}}")
	Page<ProductoEntity> findByFechaHora(String fecha, String hora, Pageable pageable);

}
