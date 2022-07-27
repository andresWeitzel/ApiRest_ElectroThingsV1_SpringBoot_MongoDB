package com.api.rest.v1.security.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.rest.v1.security.entities.Usuario;

@Repository
public interface I_UsuarioRepository extends MongoRepository<Usuario, String> {
	
	// ============= MÉTODOS DE BÚSQUEDA ===================
	
	@Query("{'id': ?0}")
	Optional<Usuario> findById(String id);

	@Query(value = "{'nombre': {$regex : ?0, $options: 'i'}}")
	Page<Usuario> findByNombre(String nombre, Pageable pageable);

	@Query(value = "{'apellido': {$regex : ?0, $options: 'i'}}")
	Page<Usuario> findByApellido(String apellido, Pageable pageable);

	@Query(value = "{'username': {$regex : ?0, $options: 'i'}}")
	Page<Usuario> findByUsername(String username, Pageable pageable);
	
	
	@Query(value = "{'username': {$regex : ?0, $options: 'i'}}")
	Optional<Usuario> findByUsername(String username);
	
	@Query(value = "{'password': {$regex : ?0, $options: 'i'}}")
	Page<Usuario> findByPassword(String password, Pageable pageable);
	


	@Query(value = "{'email': {$regex : ?0, $options: 'i'}}")
	Page<Usuario> findByEmail(String email, Pageable pageable);
	
	
	
	Page<Usuario> findAll(Pageable pageable);
	
	
	// ============= MÉTODOS DE COMPROBACIONES ===================


    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
	
	


}
