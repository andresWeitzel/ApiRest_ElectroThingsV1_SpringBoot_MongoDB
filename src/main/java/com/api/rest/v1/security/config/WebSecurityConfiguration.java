package com.api.rest.v1.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.rest.v1.security.config.jwt.JwtTokenFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	 @Bean
	public PasswordEncoder bCryptPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//Método para comprobar el user y password en la db
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		
		

	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		http.csrf().disable().authorizeRequests()
        .antMatchers("/login")
        .permitAll() //permitimos el acceso a /login a cualquiera
        .anyRequest()
        .authenticated() //cualquier otra peticion requiere autenticacion
        .and()
        // Las peticiones /login pasaran previamente por este filtro
        .addFilterBefore(new LoginFilterConfiguration("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
            
        // Las demás peticiones pasarán por este filtro para validar el token
        .addFilterBefore(new JwtFilterConfiguration(),
                UsernamePasswordAuthenticationFilter.class);
	*/
		

		
		http
		.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/v1/**").permitAll()
        .antMatchers("/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   

	}
	
}
