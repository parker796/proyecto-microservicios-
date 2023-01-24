package services;


import java.util.Optional;

//va ser una interfaz generica para cualquier servicio que utilicemos
public interface CommonServiceGeneric<E> {
    public Iterable<E> findAll(); //puede ser una lista
    public Optional<E> findById(Long id);
    public E save(E entity);
    public void deleteById(Long id);
}
