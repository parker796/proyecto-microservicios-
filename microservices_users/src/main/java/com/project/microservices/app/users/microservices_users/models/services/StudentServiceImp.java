package com.project.microservices.app.users.microservices_users.models.services;

import com.project.microservices.app.users.microservices_users.models.entity.Student;

import com.project.microservices.app.users.microservices_users.models.repository.StudentRepository;

import services.CommonServiceGenericImp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;



@Service
public class StudentServiceImp extends CommonServiceGenericImp<Student, StudentRepository>implements StudentService{

	public StudentServiceImp(StudentRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	
   /*
    private StudentRepository studentrepository;

    @Autowired //la cereza del pastel DI por constructor mas seguridad
    public StudentServiceImp(StudentRepository studentrepository){
        this.studentrepository = studentrepository;
    }

    @Override
    @Transactional(readOnly = true) //este viene de spring framework para consulta leemos solo en lectura
    public Iterable<Student> findAll() {
        return studentrepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return studentrepository.findById(id);
    }

    @Override
    @Transactional//hacemos una escritura
    public Student save(Student student) {
        return studentrepository.save(student);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentrepository.deleteById(id);
    }
    */

}
