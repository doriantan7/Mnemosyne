package ch.hearc.full.book.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.service.BookService;
import ch.hearc.full.person.service.PersonService;

@RestController
@RequestMapping("/api")
public class BookControllerRest {

	@Autowired
	BookService bookService;

	@Autowired
	PersonService personService;

	@GetMapping(value = { "/books" })
	public List<Book> getAllBooksByUser() {
		return bookService.getAllBooks();
	}

	// GET maintenant du coup
	@GetMapping(value = { "/book/{id}" })
	public Book getBook(@PathVariable("id") Long id) {
		return bookService.getBookById(id);
	}
}
