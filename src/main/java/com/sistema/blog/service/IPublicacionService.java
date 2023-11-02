package com.sistema.blog.service;

import java.util.List;

import com.sistema.blog.dto.PublicacionDTO;

public interface IPublicacionService {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public List<PublicacionDTO> obtenerTodaslasPublicaciones();
	
	public PublicacionDTO obtenerPublicacionPorId(long id);


}
