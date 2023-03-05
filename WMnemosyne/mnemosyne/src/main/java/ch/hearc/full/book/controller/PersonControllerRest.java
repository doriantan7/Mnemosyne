package ch.hearc.full.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.full.book.model.Person;
import ch.hearc.full.book.service.BookService;
import ch.hearc.full.person.repository.PersonRepository_I;
import jakarta.servlet.http.HttpSession;

@RestController
public class PersonControllerRest {

	@Autowired
	private PersonRepository_I personRepo;

	@Autowired
	BookService bookService;

	// Password encoder
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping(value = { "/login" })
	public String showLoginPage(Model model, HttpSession session) {
		model.addAttribute("logged", false);
		return "login";
	}

	@PostMapping("/login-person")
	public String loginPerson(@RequestParam String pseudo, @RequestParam String password, HttpSession session) {
		Person searchPerson = personRepo.findByPseudo(pseudo);
		if (searchPerson != null && passwordEncoder.matches(password, searchPerson.getPassword())){
			session.setAttribute("person", searchPerson);
			return "redirect:/accueil";
		}
		return "redirect:/login";
	}

	@GetMapping("/create-account")
	public String ShowCreatePersonrPage(Model model, HttpSession session) {
		model.addAttribute("logged", false);
		return "create-account";
	}

	@PostMapping("/create-person")
	public String createPerson(@RequestParam String pseudo, @RequestParam String password,
			@RequestParam String passwordCheck, HttpSession session) {
		if (password.equals(passwordCheck) && personRepo.findByPseudo(pseudo) == null) {
			Person newPerson = new Person();
			newPerson.setPseudo(pseudo);
			newPerson.setPassword(passwordEncoder.encode(password));
			personRepo.save(newPerson);
			session.setAttribute("user", newPerson);

			return "redirect:/login";
		}
		return "redirect:/create-account";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
