package ch.hearc.full.book.model;

import java.util.Comparator;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookRest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String page;
	private String commentary;

	public BookRest() {

	}

	public BookRest(String name, String page, String commentary) {
		this.name = name;
		this.page = page;
		this.commentary = commentary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, page);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookRest other = (BookRest) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Comparateur par rapport au nom des livres
	 */
	public static Comparator<BookRest> BookNameComparator = new Comparator<BookRest>() {

		public int compare(BookRest b1, BookRest b2) {
			String bookName1 = b1.getName().toUpperCase();
			String bookName2 = b2.getName().toUpperCase();

			return bookName1.compareTo(bookName2);

		}
	};

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
