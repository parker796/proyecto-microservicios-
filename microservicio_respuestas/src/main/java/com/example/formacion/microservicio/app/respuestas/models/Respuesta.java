package com.example.formacion.microservicio.app.respuestas.models;

import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.formacion.microservicio.common.students.models.entity.Student;
import com.example.formacion.microservicio.examenes.models.Pregunta;

@Entity
@Table(name = "respuestas")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String texto;

	@ManyToOne(fetch = FetchType.LAZY)
	private Student alumno;

	@OneToOne(fetch = FetchType.LAZY)
	private Pregunta pregunta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Student getAlumno() {
		return alumno;
	}

	public void setAlumno(Student alumno) {
		this.alumno = alumno;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
