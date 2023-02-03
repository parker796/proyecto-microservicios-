package com.example.formacion.microservicio.examenes.models;

import java.util.ArrayList;





import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="examenes")
public class Examen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // llave primaria
	private Long id;
	@Column(name = "nombre", nullable = false, length = 30)
	@NotEmpty
	private String nombre;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	//permitimos asignar preguntas en la relacion inversa a los examenes
	@JsonIgnoreProperties(value = {"examen"}, allowSetters = true) //aqui asignamos un propiedad porque como tenemos la relacion bidireccional
	//cada que imprimamos el json con los getters se van a imprimir las dos clases y genera un loop entonces ponemos un
	//limite en la relacion de la pregunta que contiene un examen entonces suprimimos el valor del examen
	@OneToMany(mappedBy = "examen",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //el cascade all incluye el persist y el remove
	//cuando se borre un examen se debe de borrar sus preguntas hacemos un relacion de composicion con cascade 
	//cada que eliminemos una pregunta en la lista que tiene el examen su llave foranea se debe asignar en null con el examen
	//entonces hacemos la relacion bidireccional para que la llave foranea se le asigne en null
	private List<Pregunta> preguntas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Asignatura asignatura; //muchos examenes estan asociados a una asignatura
	
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public void addPregunta(Pregunta pregunta) {
		this.preguntas.add(pregunta);
		pregunta.setExamen(this); //agregamos una pregunta a la lista de preguntas del examen pero esa misma pregunta
		//la relacionamos con el examen en donde estamos parado la relacion inversa 
	}
	public void removePregunta(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
		pregunta.setExamen(null); //hacemos que la llave foranea sea en null quitamos la pregunta con la relacion del examen
		//por eso es importante el orphanRemoval
	}
	
	
	public Examen() {
		this.preguntas = new ArrayList<>(); //inicalizamos nuestra lista de preguntas
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
	//	this.preguntas = preguntas; //aqui tenemos un problema porque por cada pregunta no se relacion con el examen 
		//en la relacion inversa lo corregimos
		/*
		preguntas.forEach(p -> { //esta relacion se puede optimizar de tal manera
			this.addPregunta(p); //la relacion inversa se construya
		});*/
		this.preguntas.clear(); //reseteamos las preguntas de tal manera que jpa sepa cuales se borraron
		preguntas.forEach(this::addPregunta); //asume que pasamos un argumento a addPregunta
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
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
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true; //se refiere a que la clase student son iguales al objeto que pasamos
		}
		
		if(!(obj instanceof Examen)) {
			return false;
		}
		Examen exa = (Examen) obj;
		
		return this.id != null && this.id.equals(exa.getId()); //mismo objeto con el id
	}
	
}
