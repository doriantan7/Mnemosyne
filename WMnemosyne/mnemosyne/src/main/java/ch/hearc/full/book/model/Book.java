package ch.hearc.full.book.model;

import java.util.Comparator;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	private String name;
	private String page;
	private String commentary;

	public Book() {

	}

	public Book(String name, String page, String commentary, Person person) {
		this.name = name;
		this.page = page;
		this.commentary = commentary;
		this.person = person;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, page, person.getPseudo());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Comparateur par rapport au nom des livres
	 */
	public static Comparator<Book> BookNameComparator = new Comparator<Book>() {

		public int compare(Book b1, Book b2) {
			String bookName1 = b1.getName().toUpperCase();
			String bookName2 = b2.getName().toUpperCase();

			return bookName1.compareTo(bookName2);

		}
	};

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getPersonPseudo() {
		return person.getPseudo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getCommentary() {
		return this.commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
}
