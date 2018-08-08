package pe.com.booklink.registration.autorbook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autorbook")
public class AutorBook {
	
	@Id
	@Column(name = "uuid", length = 100)
	private String uuid;
	@Column(name = "uuidbook", length = 100)
	private String uuidBook;
	@Column(name = "uuidautor", length = 100)
	private String uuidAutor;
	
	public AutorBook() {
		// TODO Auto-generated constructor stub
	}
	
	public AutorBook(String uuid, String uuidBook, String uuidAutor) {
		this.uuid = uuid;
		this.uuidBook = uuidBook;
		this.uuidAutor = uuidAutor;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getUuidBook() {
		return uuidBook;
	}
	
	public void setUuidBook(String uuidBook) {
		this.uuidBook = uuidBook;
	}
	
	public String getUuidAutor() {
		return uuidAutor;
	}
	
	public void setUuidAutor(String uuidAutor) {
		this.uuidAutor = uuidAutor;
	}
}
