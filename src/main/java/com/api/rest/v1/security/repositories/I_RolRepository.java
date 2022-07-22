package com.api.rest.v1.security.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.rest.v1.security.entities.Rol;
import com.api.rest.v1.security.enums.TipoRol;

@Repository
public interface I_RolRepository extends MongoRepository<Rol, String>{
	
	
	
	@Query("{'id': ?0}")
	Optional<Rol> findById(ObjectId id);

	@Query("{'rol': ?0}")
	Optional<Rol> findByRol(TipoRol rol);

	
	
}
