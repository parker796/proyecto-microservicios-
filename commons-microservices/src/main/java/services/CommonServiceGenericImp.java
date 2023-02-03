package services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
//buenas practicas para nuestros microservicios solo necesitamos heredarlas en lugar de crud pagin para paginacion
public class CommonServiceGenericImp<E, R extends PagingAndSortingRepository<E,Long>> implements CommonServiceGeneric<E>{
    //private R repository; //en lugar de private protected
	protected R repository; //para que se reutilize en las clases hijas
    @Autowired //la cereza del pastel DI por constructor mas seguridad
    public CommonServiceGenericImp(R repository){
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true) //este viene de spring framework para consulta leemos solo en lectura
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional//hacemos una escritura
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
