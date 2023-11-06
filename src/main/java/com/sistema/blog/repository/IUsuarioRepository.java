package com.sistema.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.model.Users;

public interface IUsuarioRepository extends JpaRepository<Users, Long> {

	
	public Optional<Users> findByEmail(String email);
	
	public Optional<Users> findByUsernameOrEmail(String username, String email);
	
	public Optional<Users> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
}
