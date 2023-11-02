package com.sistema.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.model.Publicacion;

public interface IPublicacionesRepository extends JpaRepository<Publicacion, Long> {

}
