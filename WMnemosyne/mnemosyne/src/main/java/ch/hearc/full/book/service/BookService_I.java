package ch.hearc.full.book.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.model.Person;

public interface BookService_I {

	/**
	 * Sauvegarde un nouveau livre
	 * 
	 * @param book le livre
	 */
	public void addBookToLibrary(Book book);

	/**
	 * Retourne tout les livres
	 * 
	 * @return la liste des livres
	 */
	public List<Book> getAllBooksFromLibraryByPerson(Person person);

	public void deleteBook(Long id);

	public Book updateBook(Book book);

	public Book getBookById(Long id);

	public Page<Book> getBooksByUserPaginated(Pageable pageable, Person person);

	// RestFull method
	public List<Book> getAllBooks();

	public Book deleteBookById(Long id);

	public Book updateBookById(Long id, Book book);
}
