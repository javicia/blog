package com.sistema.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;
	private String email;
	private String cuerpo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publicacion_id", nullable = false)
	private Publicacion publicacion;

	public Comentario() {
		super();
	}

	public Comentario(long id, String nombre, String email, String cuerpo, Publicacion publicacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.cuerpo = cuerpo;
		this.publicacion = publicacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

}
