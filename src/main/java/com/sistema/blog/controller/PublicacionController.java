package com.sistema.blog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.service.IPublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

	@Autowired
	private IPublicacionService publicacionService;
	
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO){
		return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO),HttpStatus.CREATED);
	}
	@GetMapping
	public List<PublicacionDTO> listarPublicaciones(){
		return publicacionService.obtenerTodaslasPublicaciones();
	}
}
