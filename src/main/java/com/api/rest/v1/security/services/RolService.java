package com.api.rest.v1.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.rest.v1.security.entities.Rol;
import com.api.rest.v1.security.enums.TipoRol;
import com.api.rest.v1.security.repositories.I_RolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    I_RolRepository rolRepository;

    public Optional<Rol> getByRol(TipoRol rol){
        return rolRepository.findByRol(rol);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
	
}
