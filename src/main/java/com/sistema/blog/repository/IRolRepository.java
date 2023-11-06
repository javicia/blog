package com.sistema.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.model.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
}
