package com.api.rest.v1.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.rest.v1.security.entities.Usuario;
import com.api.rest.v1.security.entities.UsuarioDetails;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	Usuario usuario = usuarioServiceImpl.getByUsername(username).get();
    	
        return UsuarioDetails.build(usuario);
    }

}
