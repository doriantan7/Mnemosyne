package ch.hearc.full.book.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.model.Person;
import ch.hearc.full.book.service.BookService;
import ch.hearc.full.person.service.PersonService;
import jakarta.servlet.http.HttpSession;

@RestController
public class BookControllerRest {

	@Autowired
	BookService bookService;

	@Autowired
	PersonService personService;

	//@GetMapping(value = { "/", "/accueil" })
//	public String showAccueilPage(Model model, HttpSession session, @RequestParam("page") Optional<Integer> page) {
//		int currentPage = page.orElse(1);
//		Person person = (Person) session.getAttribute("person");
//		if (person != null) {
//			model.addAttribute("logged", true);
//
//			Page<Book> bookPage = bookService.getBooksByUserPaginated(PageRequest.of(currentPage - 1, 5), person);
//			int totalPages = bookPage.getTotalPages();
//			if (totalPages > 0) {
//				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//				model.addAttribute("pageNumbers", pageNumbers);
//			}
//			model.addAttribute("books", bookPage);
//
//			// model.addAttribute("books",
//			// bookService.getAllBooksFromLibraryByPerson(person));
//		} else {
//			return "redirect:/login";
//		}
//		return "accueil";
//	}

	@GetMapping(value = { "/", "/accueil" })
	public List<Book> getAllBooksByUser(HttpSession session) {
		Person person = (Person) session.getAttribute("person");
		return bookService.getAllBooksFromLibraryByPerson(person);
	}

	@PostMapping(value = { "/save-book" })
	public String saveBook(@ModelAttribute Book book, BindingResult errors, Model model, HttpSession session) {
		Person p = (Person) session.getAttribute("person");
		book.setPerson(p);
		bookService.addBookToLibrary(book);
		return "redirect:/accueil";
	}

	@PostMapping(value = { "/delete-book" })
	public String deleteBook(@ModelAttribute Book book, BindingResult errors, Model model, HttpSession session) {
		;
		bookService.deleteBook(book.getId());
		return "redirect:/accueil";
	}

	@GetMapping(value = { "/edit-book/{id}" })
	public String showEditBookPage(@ModelAttribute Book book, Model model, HttpSession session) {
		Person person = (Person) session.getAttribute("person");
		if (person != null) {
			model.addAttribute("logged", true);
			model.addAttribute("book", bookService.getBookById(book.getId()));
		} else {
			return "redirect:/login";
		}
		return "edit-book";
	}

	@PostMapping(value = { "/update-book" })
	public String updateBook(@ModelAttribute Book book, BindingResult errors, Model model, HttpSession session) {
		Person p = (Person) session.getAttribute("person");
		book.setPerson(p);
		bookService.deleteBook(book.getId());
		bookService.addBookToLibrary(book);
		return "redirect:/accueil";
	}

}
