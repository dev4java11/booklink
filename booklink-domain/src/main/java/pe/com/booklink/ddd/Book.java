package pe.com.booklink.ddd;

import java.util.List;

public class Book {

	private String uuid;
	private String title;
	private String description;
	private List<Autor> autors;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Autor> getAutors() {
		return autors;
	}
	
	public void setAutors(List<Autor> autors) {
		this.autors = autors;
	}
}
