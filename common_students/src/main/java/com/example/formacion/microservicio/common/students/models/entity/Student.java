package com.example.formacion.microservicio.common.students.models.entity;



import java.util.Date;




import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
*/
@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //esta es la llave primaria para mariadb o mysql
    private Long id;
    // @Column(name = "name", nullable = false, length = 50)
    @NotEmpty
    private String name;
    @Column(name = "last_name", length = 50)
    @NotEmpty
    private String Lastname;
    // @Column(name = "email", nullable = false, length = 50)
    @NotEmpty
    @Email
    private String email;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Lob //nos permite esa anotacion guardar un blob objetos binarios largos
    @JsonIgnore
    private byte[] foto; //guardamos una foto en bd pero puede ser un pdf, word, excel etc

    public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	//metodo que utilizaremos en angular que es un id unico cada objeto genera uno
	public Integer getHasCode() {
		return (this.foto != null) ? this.foto.hashCode() : null;
	}
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

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
		
		if(!(obj instanceof Student)) {
			return false;
		}
		Student alum = (Student) obj;
		
		return this.id != null && this.id.equals(alum.getId()); //mismo objeto con el id
		
	}
    
    
}
