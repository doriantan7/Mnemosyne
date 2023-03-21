package ch.hearc.full.book.model.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ch.hearc.full.book.model.Book;
import ch.hearc.full.book.model.BookRest;
import ch.hearc.full.book.model.Person;
import ch.hearc.full.book.repository.BookRepository_I;
import ch.hearc.full.book.repository.BookRestRepository_I;
import ch.hearc.full.person.repository.PersonRepository_I;

@Component
public class DBSeeder implements CommandLineRunner {

	@Autowired
	BookRepository_I bookRepo;

	@Autowired
	BookRestRepository_I bookRestRepo;

	@Autowired
	PersonRepository_I personRepo;

	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	private void loadData() {
		if (personRepo.count() > 0 || bookRepo.count() > 0) {
			System.out.println("Database already seeded");
			return;
		}

		// Password encoder
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		Person person1 = new Person("user1", passwordEncoder.encode("123"));
		Person person2 = new Person("user2", passwordEncoder.encode("456"));
		Person person3 = new Person("user3", passwordEncoder.encode("789"));

		personRepo.save(person1);
		personRepo.save(person2);
		personRepo.save(person3);

		bookRepo.save(new Book(books[0][0], books[0][1], books[0][2], person1));
		bookRepo.save(new Book(books[1][0], books[1][1], books[1][2], person1));
		bookRepo.save(new Book(books[2][0], books[2][1], books[2][2], person1));
		bookRepo.save(new Book(books[3][0], books[3][1], books[3][2], person1));

		bookRepo.save(new Book(books[4][0], books[4][1], books[4][2], person2));
		bookRepo.save(new Book(books[5][0], books[5][1], books[5][2], person2));
		bookRepo.save(new Book(books[6][0], books[6][1], books[6][2], person2));
		bookRepo.save(new Book(books[7][0], books[7][1], books[7][2], person2));

		bookRepo.save(new Book(books[8][0], books[8][1], books[8][2], person3));
		bookRepo.save(new Book(books[9][0], books[9][1], books[9][2], person3));
		bookRepo.save(new Book(books[10][0], books[10][1], books[10][2], person3));
		bookRepo.save(new Book(books[11][0], books[11][1], books[11][2], person3));

		bookRestRepo.save(new BookRest(books[0][0], books[0][1], books[0][2]));
		bookRestRepo.save(new BookRest(books[1][0], books[1][1], books[1][2]));
		bookRestRepo.save(new BookRest(books[2][0], books[2][1], books[2][2]));
		bookRestRepo.save(new BookRest(books[3][0], books[3][1], books[3][2]));

		bookRestRepo.save(new BookRest(books[4][0], books[4][1], books[4][2]));
		bookRestRepo.save(new BookRest(books[5][0], books[5][1], books[5][2]));
		bookRestRepo.save(new BookRest(books[6][0], books[6][1], books[6][2]));
		bookRestRepo.save(new BookRest(books[7][0], books[7][1], books[7][2]));

		bookRestRepo.save(new BookRest(books[8][0], books[8][1], books[8][2]));
		bookRestRepo.save(new BookRest(books[9][0], books[9][1], books[9][2]));
		bookRestRepo.save(new BookRest(books[10][0], books[10][1], books[10][2]));
		bookRestRepo.save(new BookRest(books[11][0], books[11][1], books[11][2]));

		System.out.println("Database OK");

	}

	private String[][] books = { { "TODAG", "400", "Tales of demons and gods" },
			{ "SSOASK", "32", "Survival story of a sword king" }, { "Dungeon reset", "41", " " },
			{ "Boruto", "75", "Beurk" }, { "Kaiju n°8", "72", "Le Kaiju n°10 veut devenir une épée" },
			{ "One piece", "684", " Desrosa, beaucoup trop long" }, { "Dandadan", "76", " " },
			{ "TBATE", "38", "The beginning after the end" }, { "UQ Holder", "161", "Arc finale" },
			{ "World trigger", "145", "A finir mais aura pas de suite" },
			{ "Grand Blue", "60.5", "Le meilleure manga humour" },
			{ "Kaiju n°8", "72", "Le Kaiju n°10 veut devenir une épée" }, };
}
