package com.sistema.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
import com.sistema.blog.service.IPublicacionService;
import com.sistema.blog.utility.AppConstans;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

	@Autowired
	private IPublicacionService publicacionService;

	@GetMapping
	public PublicacionRespuesta listarPublicaciones(
			@RequestParam(value = "pageNo", defaultValue = AppConstans.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numPagina,
			@RequestParam(value = "pageSize", defaultValue = AppConstans.MEDIDA_DE_PAGINA_DEFECTO, required = false) int medidaPagina,
			@RequestParam(value = "sortBy", defaultValue = AppConstans.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstans.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String ShortDir) {
		return publicacionService.obtenerTodaslasPublicaciones(numPagina, medidaPagina, ordenarPor, ShortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
	}

	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO,
			@PathVariable(name = "id") long id) {
		PublicacionDTO publicacionRespuesta = publicacionService.actualizarPublicacion(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
		publicacionService.eliminarPublicacion(id);
		return new ResponseEntity<>("Publicación eliminada con éxito.", HttpStatus.OK);
	}
}
