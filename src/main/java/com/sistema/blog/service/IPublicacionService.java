package com.sistema.blog.service;


import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;

public interface IPublicacionService {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public PublicacionRespuesta obtenerTodaslasPublicaciones(int numPagina, int medidaPagina, String ordenarPor, String sortDir);
	
	public PublicacionDTO obtenerPublicacionPorId(long id);
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);

	public void eliminarPublicacion(long id);

}
