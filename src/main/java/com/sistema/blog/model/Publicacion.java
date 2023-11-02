package com.sistema.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="publicaciones", uniqueConstraints= {@UniqueConstraint(columnNames = {"titulo"})})
public class Publicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="titulo", nullable = false )
	private String titulo;
	
	@Column(name="descripcion" , nullable = false)
	private String descripcion;
	
	@Column(name="contenido", nullable = false)
	private String contenido;

	public Publicacion() {
		
	}

	public Publicacion(Long id, String titulo, String descripcion, String contenido) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	
	
}
