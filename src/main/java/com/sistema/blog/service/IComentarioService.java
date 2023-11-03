package com.sistema.blog.service;

import com.sistema.blog.dto.ComentarioDTO;

public interface IComentarioService {

	
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO); 
}
