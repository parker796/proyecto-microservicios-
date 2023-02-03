package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    
    @GetMapping("/pagina")
    //construimos la respuesta en el body y un status
    public ResponseEntity<?> listPaginable(Pageable pageable){
        return ResponseEntity.ok().body(sServiceGeneric.findAll(pageable));//construimos ya el json para el fronted
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
    public ResponseEntity<?> create(@Validated @RequestBody E eEntity, BindingResult result){
    	if(result.hasErrors()) {
    		return this.validar(result);
    	}
        E eEntityDB = sServiceGeneric.save(eEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(eEntityDB); //201
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        sServiceGeneric.deleteById(id);
        return (ResponseEntity<?>) ResponseEntity.noContent().build();//204
    }

    protected ResponseEntity<?> validar(BindingResult result){
    	Map<String, Object> errores = new HashMap<>();
    	result.getFieldErrors().forEach(err -> {
    		errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
    	});
    	return ResponseEntity.badRequest().body(errores);
    }

}
