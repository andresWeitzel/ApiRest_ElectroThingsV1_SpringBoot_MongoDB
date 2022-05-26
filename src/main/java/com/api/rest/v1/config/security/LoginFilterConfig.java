package com.api.rest.v1.config.security;


import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.rest.v1.config.security.jwt.JwtUtilConfiguration;
import com.api.rest.v1.config.security.usuario.UsuarioConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFilterConfig extends AbstractAuthenticationProcessingFilter{

	  public LoginFilterConfig(String url, AuthenticationManager authManager) {
	        super(new AntPathRequestMatcher(url));
	        setAuthenticationManager(authManager);
	    }

	  @Override
	    public Authentication attemptAuthentication(
	            HttpServletRequest req, HttpServletResponse res)
	            throws AuthenticationException, IOException, ServletException {

	        // obtenemos el body de la peticion que asumimos viene en formato JSON
	        InputStream body = req.getInputStream();

	        //Leemos los valores del Json (usuario y contraseña)
	        UsuarioConfiguration user = new ObjectMapper().readValue(body, UsuarioConfiguration.class);

	        // Finalmente autenticamos
	        // Spring comparará el user/password recibidos
	        // contra el que definimos en la clase SecurityConfig
	        return getAuthenticationManager().authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        user.getUser(),
	                        user.getPassword(),
	                        Collections.emptyList()
	                )
	        );
	    }
	  
	  @Override
	    protected void successfulAuthentication(
	            HttpServletRequest req,
	            HttpServletResponse res, FilterChain chain,
	            Authentication auth) throws IOException, ServletException {

	        // Si la autenticacion fue exitosa, agregamos el token a la respuesta
	        JwtUtilConfiguration.addAuthentication(res, auth.getName());
	    }

}
