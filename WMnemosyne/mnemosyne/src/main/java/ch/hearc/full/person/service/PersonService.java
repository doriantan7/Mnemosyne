package ch.hearc.full.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.full.book.model.Person;
import ch.hearc.full.person.repository.PersonRepository_I;

@Service
public class PersonService implements PersonService_I {

	@Autowired
	PersonRepository_I personRepository; // repository d'accès aux données

	@Override
	public Person getPersonById(long id) {
		return personRepository.findById(id);
	}

	@Override
	public Person getPersonByPseudo(String pseudo) {
		return personRepository.findByPseudo(pseudo);
	}

	@Override
	public void createPerson(Person person) {
		personRepository.save(person);
	}

}
