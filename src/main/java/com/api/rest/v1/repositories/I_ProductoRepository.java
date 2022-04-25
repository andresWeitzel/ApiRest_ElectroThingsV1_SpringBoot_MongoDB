package com.api.rest.v1.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.api.rest.v1.entities.ProductoEntity;

public interface I_ProductoRepository extends MongoRepository<ProductoEntity, String>  {

	
	
}
