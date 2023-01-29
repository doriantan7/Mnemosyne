package ch.hearc.full.person.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.full.book.model.Person;

public interface PersonRepository_I extends CrudRepository<Person, Long> {

	Person findById(long id);

	Person findByPseudo(String name);

}
