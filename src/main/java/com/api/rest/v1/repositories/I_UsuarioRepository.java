package com.api.rest.v1.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import com.api.rest.v1.entities.UsuarioEntity;

public interface I_UsuarioRepository extends MongoRepository<UsuarioEntity, String> {
	
	@Query("{'id': ?0}")
	Optional<UsuarioEntity> findById(String id);

	@Query(value = "{'nombre': {$regex : ?0, $options: 'i'}}")
	Page<UsuarioEntity> findByNombre(String nombre, Pageable pageable);

	@Query(value = "{'apellido': {$regex : ?0, $options: 'i'}}")
	Page<UsuarioEntity> findByApellido(String apellido, Pageable pageable);

	@Query(value = "{'user': {$regex : ?0, $options: 'i'}}")
	Page<UsuarioEntity> findByUser(String user, Pageable pageable);
	
	
	@Query(value = "{'user': {$regex : ?0, $options: 'i'}}")
	UsuarioEntity findByUser(String user);
	
	@Query(value = "{'password': {$regex : ?0, $options: 'i'}}")
	Page<UsuarioEntity> findByPassword(String password, Pageable pageable);
	
	@Query(value = "{'rol': {$regex : ?0, $options: 'i'}}")
	Page<UsuarioEntity> findByRol(String rol, Pageable pageable);



}
