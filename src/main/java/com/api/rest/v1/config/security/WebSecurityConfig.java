package com.api.rest.v1.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.rest.v1.config.security.jwt.JwtFilterConfiguration;
import com.api.rest.v1.config.security.usuario.UsuarioDetailsService;
import com.api.rest.v1.services.usuarios.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	// ========= INYECCIÓN DE DEPENDENCIAS ==========
		@Autowired
		@Qualifier("UsuarioDetailsService")
		UsuarioDetailsService usuarioDetailsService;

		/*
		@Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }*/
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Método para comprobar el user y password en la db
		//auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(usuarioDetailsService);
 
		
		/*
		// Podemos Cargar el Usuario y Contraseña en Memoria sin usar la db
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
		*/
		
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
        .antMatchers("/login").permitAll() //permitimos el acceso a /login a cualquiera
        .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
        .and()
        // Las peticiones /login pasaran previamente por este filtro
        .addFilterBefore(new LoginFilterConfig("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
            
        // Las demás peticiones pasarán por este filtro para validar el token
        .addFilterBefore(new JwtFilterConfiguration(),
                UsernamePasswordAuthenticationFilter.class);
	}
	
}

	
	
