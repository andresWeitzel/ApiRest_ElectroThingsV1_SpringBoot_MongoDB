package com.api.rest.v1.security.config.jwt;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.api.rest.v1.entities.UsuarioEntity;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	//=== ATRIBUTOS DEL application.properties ====
	//password encriptada
	@Value("${jwt.secret}")
    private static String secret;
	//tiempo base 3600
    @Value("${jwt.expiration}")
    private static int expiration;
    
    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public String generateToken(Authentication authentication){
        
    	//Creamos la autenticacion del usuario
    	UsuarioEntity usuarioEntity = (UsuarioEntity) authentication.getPrincipal();
        
        
        return Jwts
        		.builder()
        		.setSubject(usuarioEntity.getUser())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))//tiempo de expiracion
                .signWith(SignatureAlgorithm.HS512, secret)//token
                .compact();
    }
	
	
	
    public String getUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    
    //Metodo Especifico para validar el Token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("TOKEN MAL FORMADO");
        }catch (UnsupportedJwtException e){
            logger.error("TOKEN NO SOPORTADO");
        }catch (ExpiredJwtException e){
            logger.error("TOKEN EXPIRADO");
        }catch (IllegalArgumentException e){
            logger.error("TOKEN VACIO");
        }catch (SignatureException e){
            logger.error("FALLO EN LA FIRMA DEL TOKEN");
        }
        return false;
    }
    
}
