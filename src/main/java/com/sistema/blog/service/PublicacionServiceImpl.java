package com.sistema.blog.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;
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
	public PublicacionRespuesta obtenerTodaslasPublicaciones(int numPagina, int medidaPagina, String ordenarPor, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
		
		Pageable pageable = PageRequest.of(numPagina, medidaPagina, sort);
		Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);

		List<Publicacion> listapublicaciones = publicaciones.getContent();
		List<PublicacionDTO> contenido = listapublicaciones.stream().map(publicacion -> mapearDTO(publicacion))
				.collect(Collectors.toList());
		PublicacionRespuesta publicacionResponse = new PublicacionRespuesta();
		publicacionResponse.setContenido(contenido);
		publicacionResponse.setNumPagina(publicaciones.getNumber());
		publicacionResponse.setMedidaPagina(publicaciones.getSize());
		publicacionResponse.setTotalElementos(publicaciones.getTotalElements());
		publicacionResponse.setTotalPaginas(publicaciones.getTotalPages());
		publicacionResponse.setUltima(publicaciones.isLast());
		return publicacionResponse;
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

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicaciones", "id", id));

		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		Publicacion publicacion = publicacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicaciones", "id", id));
		publicacionRepository.delete(publicacion);

	}

}
