package com.project.microservices.app.users.microservices_users.models.controller;

import com.example.formacion.microservicio.common.students.models.entity.Student;


//import com.project.microservices.app.users.microservices_users.models.entity.Student;

import com.project.microservices.app.users.microservices_users.models.services.StudentService;

import controllers.CommontControllerGeneric;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class StudentController extends CommontControllerGeneric<Student, StudentService>{
   
	//esto ya no van porque lo estamos heredando
	/*private StudentService studentservice;
    @Autowired
    public StudentController(StudentService studentservice){
        this.studentservice = studentservice;
    }*/
    
    public StudentController(StudentService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}

	/*
    @GetMapping
    //construimos la respuesta en el body y un status
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(studentservice.findAll());//construimos ya el json para el fronted
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Student> o = studentservice.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();//404
        }
        return ResponseEntity.ok(o.get()); //200
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student){
        Student studentDb = studentservice.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDb); //201
    }
    */
    @PutMapping("/{id}") //siempre tiene que ir bindingresult despues de entity a validar
    public ResponseEntity<?> edit(@Valid @RequestBody Student student, BindingResult result, @PathVariable Long id){
    	if(result.hasErrors()) {
    		return this.validar(result);
    	}
       // Optional<Student> o = studentservice.findById(id);
    	Optional<Student> o = sServiceGeneric.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build(); //404
        }
        Student studentDb = o.get();
        studentDb.setName(student.getName()); //este es el parametro que viene en la peticion put
        studentDb.setLastname(student.getLastname());
        studentDb.setEmail(student.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(sServiceGeneric.save(studentDb)); //tenemos que persistir los datos en BD
    }
    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studentservice.deleteById(id);
        return (ResponseEntity<?>) ResponseEntity.noContent().build();//204
    }*/
    @GetMapping("/filtrar/{buscar}")//igual el id entre corchetes debe considir con el argumento {}
    public ResponseEntity<?> filtrarNameOrlastname(@PathVariable String buscar){
    	return ResponseEntity.ok(sServiceGeneric.findByNombreAndApellido(buscar));
    }
    
    protected ResponseEntity<?> validar(BindingResult result){
    	Map<String, Object> errores = new HashMap<>();
    	result.getFieldErrors().forEach(err -> {
    		errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
    	});
    	return ResponseEntity.badRequest().body(errores);
    }


}
