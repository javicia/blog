package com.sistema.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.exceptions.BlogAppException;
import com.sistema.blog.exceptions.ResourceNoteFoundException;
import com.sistema.blog.model.Comentario;
import com.sistema.blog.model.Publicacion;
import com.sistema.blog.repository.IComentarioRepository;
import com.sistema.blog.repository.IPublicacionesRepository;

@Service
public class ComentarioServiceImpl implements IComentarioService{

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IComentarioRepository comentarioRepository;
	
	@Autowired
	private IPublicacionesRepository publicacionRepository;
	
	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
		Comentario comentario = mapearEntidad(comentarioDTO);
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicacion", "id", publicacionId));
		comentario.setPublicacion(publicacion);
		Comentario nuevoComentario = comentarioRepository.save(comentario);
		return mapearDTO(nuevoComentario);
	}



	@Override
	public List<ComentarioDTO> obtenerComentarioPublicacionId(long publicacionId) {
		List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
		return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioDTO obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(()-> new ResourceNoteFoundException("Comentario", "id", comentarioId));
		
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación.");
		}
		return mapearDTO(comentario);
		
	}

	@Override
	public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentario) {
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(()-> new ResourceNoteFoundException("Comentario", "id", comentarioId));
		
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación.");
		}
		comentario.setNombre(solicitudComentario.getNombre());
		comentario.setEmail(solicitudComentario.getEmail());
		comentario.setCuerpo(solicitudComentario.getCuerpo());
		
		Comentario comentarioActualizado = comentarioRepository.save(comentario);
		return mapearDTO(comentarioActualizado);
		
	}

	@Override
	public void eliminarComentario(Long publicacionId, Long comentarioId) {
		Publicacion publicacion = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNoteFoundException("Publicacion", "id", publicacionId));
		
		Comentario comentario = comentarioRepository.findById(comentarioId)
				.orElseThrow(()-> new ResourceNoteFoundException("Comentario", "id", comentarioId));
		
		if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación.");
		}
		comentarioRepository.delete(comentario);
		
	}
	//mapeando a DTO
		private ComentarioDTO mapearDTO(Comentario comentario ) {
			ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
			return comentarioDTO;
		}
		
		//mapeando a entindad
		private Comentario mapearEntidad(ComentarioDTO comentarioDTO ) {
			Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
			return comentario;
		}
}
