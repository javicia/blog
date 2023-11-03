package com.sistema.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.model.Comentario;

public interface IComentarioRepository extends JpaRepository<Comentario, Long>{
	
	public List<Comentario> findByPublicacionId (long publicacionId);

}
