package com.api.rest.v1.security.jwt;


import com.api.rest.v1.security.dto.JwtDTO;
import com.api.rest.v1.security.entities.UsuarioDetails;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;



@Component
public class JwtProvider {
	
	 private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	    private String secret="secret";

	    //private int expiration= 300000 * 1;//5min * x --> 5min
	    //private int expiration= 300000 * 10;
	    private int expiration= 300000 / 5;
	    

	    public String generateToken(Authentication authentication){
	    	
	        UsuarioDetails usuarioPrincipal = (UsuarioDetails) authentication.getPrincipal();
	        
	        
	        List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	        
	        
	        return Jwts
	        		.builder()
	        		.setSubject(usuarioPrincipal.getUsername())
	        		 .claim("roles", roles)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(new Date().getTime() + expiration))
	                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
	                .compact();
	    }

	    public String getUsernameFromToken(String token){
	        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateToken(String token){
	        try {
	            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
	            return true;
	        }catch (MalformedJwtException e){
	            logger.error("token mal formado");
	        }catch (UnsupportedJwtException e){
	            logger.error("token no soportado");
	        }catch (ExpiredJwtException e){
	            logger.error("token expirado");
	        }catch (IllegalArgumentException e){
	            logger.error("token vacío");
	        }catch (SignatureException e){
	            logger.error("fail en la firma");
	        }
	        return false;
	    }
	    
	    
	    
	    public String refreshToken(JwtDTO jwtDto) throws ParseException {
	    	
	    	try {
	        	Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtDto.getToken());

	    	}catch(ExpiredJwtException e) {
	        	
	    	
	    	JWT jwt = JWTParser.parse(jwtDto.getToken());
	    	
	    	JWTClaimsSet claims = jwt.getJWTClaimsSet();
	    	
	    	String username = claims.getSubject();
	    	
	    	//roles pasado desde el .claim de Jwts.builder
	    	List<String> roles = (List<String>)claims.getClaim("roles");
	    	
	    	//actualizamos un nuevo token
	    	 return Jwts.builder()
	         		.setSubject(username)
	         	    .claim("roles", roles)
	                 .setIssuedAt(new Date())
	                 .setExpiration(new Date(new Date().getTime() + expiration))
	                 .signWith(SignatureAlgorithm.HS512, secret.getBytes())
	                 .compact();
	    }
	    	
	    	return null;
	    	
	    }
	    

	    
	    

}
