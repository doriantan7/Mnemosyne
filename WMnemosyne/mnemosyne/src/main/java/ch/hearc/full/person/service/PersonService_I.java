package ch.hearc.full.person.service;


import ch.hearc.full.book.model.Person;

public interface PersonService_I {

	
	public Person getPersonById(long id);
	public Person getPersonByPseudo(String pseudo);
	
	public void createPerson(Person person);
}
