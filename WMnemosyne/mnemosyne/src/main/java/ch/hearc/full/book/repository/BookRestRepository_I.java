package ch.hearc.full.book.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.full.book.model.BookRest;

/**
 * Interface de gestion des données des livres
 * 
 * @author Dorian
 *
 */
public interface BookRestRepository_I extends CrudRepository<BookRest, Long> {

	BookRest findById(long id);

	BookRest findByName(String name);
}
