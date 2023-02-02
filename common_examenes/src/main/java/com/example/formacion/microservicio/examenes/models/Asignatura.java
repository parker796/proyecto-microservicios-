package com.example.formacion.microservicio.examenes.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "asignaturas")
public class Asignatura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnoreProperties(value = {"hijos"}) //asi generamos un arbol la entidad relacionada con si misma
	@ManyToOne(fetch = FetchType.LAZY)
	private Asignatura padre; //se esta implementado un self join por ejemplo una sub asignatura esta asociada 
	//asignatura padre por ejemplo matematicas a su vez tiene algebra, calculo entonces esas sub asignaturas
	//debemos asociarlas asi misma a su padre muchas sub asignaturas a su padre many to one
	//la relacion inversa si se borra un padre no tiene sentido tener hijos one to many
	//cuando tenemos cascade se genera un json infinito por cada padre un hijo un hijo con padre debemos
	//controlar esa parte
	@JsonIgnoreProperties(value = {"padre"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "padre", cascade = CascadeType.ALL) 
	private List<Asignatura> hijos;
	
	public Asignatura() {
		this.hijos = new ArrayList<>(); //inicializamos las subasignaturas
	}

	public Asignatura getPadre() {
		return padre;
	}

	public void setPadre(Asignatura padre) {
		this.padre = padre;
	}

	public List<Asignatura> getHijos() {
		return hijos;
	}

	public void setHijos(List<Asignatura> hijos) {
		this.hijos = hijos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String nombre;

}
