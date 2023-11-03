package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.model.Comentario;

public interface IComentarioRepository extends JpaRepository<Comentario, Long>{

}
