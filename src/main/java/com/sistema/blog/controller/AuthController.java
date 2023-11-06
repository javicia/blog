package com.sistema.blog.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistema.blog.dto.LoginDTO;
import com.sistema.blog.dto.RegistroDTO;
import com.sistema.blog.model.Rol;
import com.sistema.blog.model.Users;
import com.sistema.blog.repository.IRolRepository;
import com.sistema.blog.repository.IUsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("Ha iniciado sesion con éxito !", HttpStatus.OK);
		
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
		if(usuarioRepository.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe. ", HttpStatus.BAD_REQUEST);
		}
		if(usuarioRepository.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe. ", HttpStatus.BAD_REQUEST);
		}
		Users user = new Users();
		user.setNombre(registroDTO.getNombre());
		user.setUsername(registroDTO.getUsername());
		user.setEmail(registroDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		
		Rol roles = rolRepository.findByNombre("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));
		
		usuarioRepository.save(user);
		return new ResponseEntity<>("Usuario registrado con éxito. ", HttpStatus.OK);
	}
}
