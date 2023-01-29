package ch.hearc.full.book.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.model.Person;

/**
 * Interface de gestion des données des livres
 * 
 * @author Dorian
 *
 */
public interface BookRepository_I extends CrudRepository<Book, Long> {

	Book findById(long id);

	Book findByName(String name);

	List<Book> findBookByPerson(Person person);
}
