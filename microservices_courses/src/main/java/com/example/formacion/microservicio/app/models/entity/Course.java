package com.example.formacion.microservicio.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Courses")
public class Course {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)//llave primaria
	private Long id;
	@Column(name="name", nullable = false, length=50)
	private String name;
	 @Column(name = "create_at")
	 @Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	 @PrePersist
	 public void prePersist(){
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
