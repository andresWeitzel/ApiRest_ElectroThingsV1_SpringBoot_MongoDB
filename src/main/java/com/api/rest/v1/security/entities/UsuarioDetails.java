package com.api.rest.v1.security.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String nombre;
	
	private String apellido;
    
    private String username;

    private String password;
    
    private String email;
    
    
    private Collection<? extends GrantedAuthority> authorities;

    
    
    
    /**
     * Creacion de un usuario con su rol
     * @param usuario
     * @return
     */
    public static UsuarioDetails build(Usuario usuario){
        
    	
    	
    	List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
        
        
        return new UsuarioDetails(usuario.getNombre(), usuario.getApellido(), usuario.getUsername(), usuario.getPassword(), usuario.getEmail(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
