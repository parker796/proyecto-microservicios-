package controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import services.CommonServiceGeneric;

//es un controlador generico solamente el put se quita porque ese si cambia pero los demas son iguales
public class CommontControllerGeneric<E,S extends CommonServiceGeneric<E>>{ //la entidad E y el service S{
   // private StudentService studentservice;
	protected S sServiceGeneric; //para poder reutilizarlo bajo la herencia
    @Autowired
    public CommontControllerGeneric(S sServiceGeneric){
        this.sServiceGeneric = sServiceGeneric;
    }
    
    @GetMapping
    //construimos la respuesta en el body y un status
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(sServiceGeneric.findAll());//construimos ya el json para el fronted
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<E> o = sServiceGeneric.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();//404
        }
        return ResponseEntity.ok(o.get()); //200
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody E eEntity){
        E eEntityDB = sServiceGeneric.save(eEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(eEntityDB); //201
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        sServiceGeneric.deleteById(id);
        return (ResponseEntity<?>) ResponseEntity.noContent().build();//204
    }


}
