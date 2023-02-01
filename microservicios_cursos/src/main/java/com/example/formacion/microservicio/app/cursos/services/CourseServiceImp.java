package com.example.formacion.microservicio.app.cursos.services;

import services.CommonServiceGenericImp;


import org.springframework.stereotype.Service;

import com.example.formacion.microservicio.app.cursos.entity.Curso;
import com.example.formacion.microservicio.app.cursos.repository.CourseRepository;



@Service
public class CourseServiceImp extends CommonServiceGenericImp<Curso, CourseRepository>implements CourseService{

	public CourseServiceImp(CourseRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
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
