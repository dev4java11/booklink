package pe.com.booklink.domain.dto;

import java.util.List;

import pe.com.booklink.ddd.Autor;
import pe.com.booklink.ddd.Book;

public class RegistrationBookRequest {
	
	private String id;
	private Book book;
	private List<Autor> autors;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public List<Autor> getAutors() {
		return autors;
	}
	
	public void setAutors(List<Autor> autors) {
		this.autors = autors;
	}
}
