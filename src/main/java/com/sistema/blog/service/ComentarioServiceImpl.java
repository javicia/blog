package com.sistema.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.dto.ComentarioDTO;
import com.sistema.blog.model.Comentario;
import com.sistema.blog.repository.IComentarioRepository;
import com.sistema.blog.repository.IPublicacionesRepository;

@Service
public class ComentarioServiceImpl implements IComentarioService{

	
	@Autowired
	private IComentarioRepository comentarioRepository;
	
	@Autowired
	private IPublicacionesRepository publicacionesRepository;
	
	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	//mapeando a DTO
	private ComentarioDTO mapearDTO(Comentario comentario ) {
		ComentarioDTO comentarioDTO = new ComentarioDTO();
		comentarioDTO.setId(comentario.getId());
		comentarioDTO.setNombre(comentario.getNombre());
		comentarioDTO.setEmail(comentario.getEmail());
		comentarioDTO.setCuerpo(comentario.getCuerpo());
		return comentarioDTO;
	}
	
	//mapeando a entindad
	private Comentario mapearEntidad(ComentarioDTO comentarioDTO ) {
		Comentario comentario = new Comentario();
		comentario.setId(comentarioDTO.getId());
		comentario.setNombre(comentarioDTO.getNombre());
		comentario.setEmail(comentarioDTO.getEmail());
		comentario.setCuerpo(comentarioDTO.getCuerpo());
		return comentario;
	}
}
