package com.sistema.blog.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.exceptions.ResourceNoteFoundException;
import com.sistema.blog.model.Publicacion;
import com.sistema.blog.repository.IPublicacionesRepository;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

	@Autowired
	private IPublicacionesRepository publicacionRepository;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = mapearEntidad(publicacionDTO);
		Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
		PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);
		return publicacionRespuesta;
	}

	@Override
	public List<PublicacionDTO> obtenerTodaslasPublicaciones() {
		List<Publicacion> publicaciones = publicacionRepository.findAll();
		return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

	}

	// Método para convertir entidad en DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();

		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());

		return publicacionDTO;

	}

	// Método para convertir DTO en entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = new Publicacion();

		publicacion.setId(publicacionDTO.getId());
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		return publicacion;

	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicaciones", "id", id));
		return mapearDTO(publicacion);
	}

}
