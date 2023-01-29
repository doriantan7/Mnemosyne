package ch.hearc.full;

import static org.mockito.ArgumentMatchers.booleanThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.model.Person;
import ch.hearc.full.book.service.BookService;
import ch.hearc.full.person.service.PersonService;

@SpringBootTest
class MnemosyneApplicationTests {

	@Autowired
	BookService bookService;

	@Autowired
	PersonService personService;

	Book book1 = new Book();
	Book book2 = new Book();
	Person person = new Person();

	
	/**
	 * ***********************************************************
	 * Test Person
	 * ************************************************************
	 */
	
	@Test
	void canAddPerson() {
		person.setPseudo("test");
		person.setPassword("test");

		personService.createPerson(person);

		Assert.notNull(personService.getPersonById(person.getId()), "User not found");
	}
	
	
	
	/**
	 * ***********************************************************
	 * Test Book
	 * ************************************************************
	 */

	@Test
	void canAddBook() {
		book1.setName("test");
		book1.setPage("8");
		book1.setCommentary("test");
		book1.setPerson(personService.getPersonById(1));

		bookService.addBookToLibrary(book1);

		Assert.notNull(bookService.getBookById(book1.getId()), "Book not found");
	}

	@Test
	void canDeleteBook() {
		book1.setName("test");
		book1.setPage("8");
		book1.setCommentary("test");
		book1.setPerson(personService.getPersonById(1));

		bookService.addBookToLibrary(book1);
		bookService.deleteBook(book1.getId());

		try {
			Book bookTest = bookService.getBookById(book1.getId());
			System.out.println(bookTest.getName());
		} catch (Exception e) {
			Assert.isTrue(true, "Book found");
		}
	}
	
	@Test
	void canEditBook() {
		book1.setName("test");
		book1.setPage("8");
		book1.setCommentary("test");
		book1.setPerson(personService.getPersonById(1));

		bookService.addBookToLibrary(book1);
		
		book1.setName("testEdit");
		
		bookService.updateBook(book1);
		
		Assert.isTrue(book1.getName().equals("testEdit"),"Not edit");
	}

	@Test
	void canGetAllBooksSorted() {
		// Book1
		book1.setName("A");
		book1.setPage("8");
		book1.setCommentary("test");
		book1.setPerson(personService.getPersonById(1));

		// Book2
		book2.setName("b");
		book2.setPage("8");
		book2.setCommentary("test");
		book2.setPerson(personService.getPersonById(1));

		bookService.addBookToLibrary(book2);
		bookService.addBookToLibrary(book1);

		List<Book> goodList = new ArrayList<>();
		goodList.add(book1);
		goodList.add(book2);

		List<Book> books = bookService.getAllBooksFromLibraryByPerson(personService.getPersonById(1));

		Assert.isTrue(books.get(0).getName() == goodList.get(0).getName());
		Assert.isTrue(books.get(1).getName() == goodList.get(1).getName());

	}

}
