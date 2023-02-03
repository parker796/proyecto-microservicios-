package services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//va ser una interfaz generica para cualquier servicio que utilicemos
public interface CommonServiceGeneric<E> {
    public Iterable<E> findAll(); //puede ser una lista
    public Page<E> findAll(Pageable pageable ); //agregamos paginacion
    public Optional<E> findById(Long id);
    public E save(E entity);
    public void deleteById(Long id);
}
