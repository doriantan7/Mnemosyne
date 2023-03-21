package ch.hearc.full.book.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.full.book.model.BookRest;
import ch.hearc.full.book.repository.BookRestRepository_I;

@Service
public class BookRestService implements BookRestService_I {

	@Autowired
	BookRestRepository_I bookRestRepository; // repository d'accès aux données

	public BookRest getBookById(Long id) {
		return bookRestRepository.findById(id).get();

	}

	@Override
	public List<BookRest> getAllBooks() {
		List<BookRest> result = new ArrayList<BookRest>();
		bookRestRepository.findAll().forEach(result::add);
		Collections.sort(result, BookRest.BookNameComparator);
		return result;
	}

	@Override
	public BookRest deleteBookById(Long id) {
		BookRest book = bookRestRepository.findById(id).get();
		bookRestRepository.deleteById(id);
		return book;
	}

	public BookRest updateBookById(Long id, BookRest book) {
		deleteBookById(id);
		bookRestRepository.save(book);
		return book;
	}

	@Override
	public void addBookToLibrary(BookRest book) {
		bookRestRepository.save(book);
	}
}
