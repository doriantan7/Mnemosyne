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
		bookRepository.deleteById(new Long(id));

	}

	public Book updateBook(Book book) {
		bookRepository.save(book);
		return book;

	}

	public Book getBookById(Long id) {
		return bookRepository.findById(new Long(id)).get();

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
        Page<Book> bookPage = new PageImpl<Book>(pagedBooks, PageRequest.of(currentPage, pageSize), allbooks.size());

        return bookPage;
    }

}
