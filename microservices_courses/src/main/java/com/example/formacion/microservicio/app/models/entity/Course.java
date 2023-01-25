package com.example.formacion.microservicio.app.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.formacion.microservicio.common.students.models.entity.Student;

@Entity
@Table(name = "Courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // llave primaria
	private Long id;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Student> Students;
	
	
	
	public Course() {
		//aqui en el constructor inicializamos la lista de alumnos para ir agregando uno a uno
		this.Students = new ArrayList<>();
	}

	public List<Student> getStudents() {
		return Students;
	}

	public void setStudents(List<Student> students) {
		Students = students;
	}
	
	public void addStudent(Student student) {
		this.Students.add(student); //necesitamos inicializar la lista de alumnos porque esta en nulo
	}//agregando alumnos uno a uno
	
	public void removeStudent(Student student) {
		this.Students.remove(student); //necesitamos inicializar la lista de alumnos porque esta en nulo
	}//eliminamos alumno al curso

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
