package ch.hearc.full.book.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.model.Person;
import ch.hearc.full.book.repository.BookRepository_I;

@Service
public class BookService implements BookService_I {

	@Autowired
	BookRepository_I bookRepository; // repository d'accès aux données

	/**
	 * Sauvegarde un nouveau livre
	 * 
	 * @param book le livre
	 */
	@Override
	public void addBookToLibrary(Book book) {
		bookRepository.save(book);
	}

	/**
	 * Retourne tous les livres dans l'ordre alphabetique
	 * 
	 * @return la liste des livres
	 */
	public List<Book> getAllBooksFromLibraryByPerson(Person person) {
		List<Book> result = new ArrayList<Book>();
		bookRepository.findBookByPerson(person).forEach(result::add);
		Collections.sort(result, Book.BookNameComparator);
		return result;
	}

	public void deleteBook(Long id) {
		bookRepository.deleteById(id);

	}

	public Book updateBook(Book book) {
		bookRepository.save(book);
		return book;

	}

	@Override
	public Page<Book> getBooksByUserPaginated(Pageable pageable, Person person) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Book> allbooks = this.getAllBooksFromLibraryByPerson(person);
		List<Book> pagedBooks;
		if (allbooks.size() < startItem) {
			pagedBooks = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, allbooks.size());
			pagedBooks = allbooks.subList(startItem, toIndex);
		}
		Page<Book> bookPage = new PageImpl<Book>(pagedBooks, PageRequest.of(currentPage, pageSize),
				allbooks.size());

		return bookPage;
	}

	// RestFull method

	public Book getBookById(Long id) {
		return bookRepository.findById(id).get();

	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> result = new ArrayList<Book>();
		bookRepository.findAll().forEach(result::add);
		// Collections.sort(result, Book.BookNameComparator);
		return result;
	}

	@Override
	public Book deleteBookById(Long id) {
		Book book = bookRepository.findById(id).get();
		bookRepository.deleteById(id);
		return book;
	}

	public Book updateBookById(Long id, Book book) {
		deleteBookById(id);
		bookRepository.save(book);
		return book;
	}
}
