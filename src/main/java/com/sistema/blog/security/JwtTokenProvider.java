package com.sistema.blog.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sistema.blog.exceptions.BlogAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	//Metodo para generar el token
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);

		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date()).setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return token;

	}
	
	//Metodo para obetenr el usuario
	public String obtenerUsernameJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	//Método para validar token
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no válida");
		}catch (MalformedJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no válida");
		}catch (ExpiredJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
		}catch (UnsupportedJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compartido");
		}catch (IllegalArgumentException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT está vacía.");
		}
		
	}
}
