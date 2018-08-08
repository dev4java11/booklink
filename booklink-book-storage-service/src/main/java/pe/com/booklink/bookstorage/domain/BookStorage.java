package pe.com.booklink.bookstorage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookstorage")
public class BookStorage {

	@Id
	@Column(name = "uuidbook", length = 100)
	private String uuidBook;
	@Column(name = "quantity")
	private Integer quantity;
	
	public String getUuidBook() {
		return uuidBook;
	}
	
	public void setUuidBook(String uuidBook) {
		this.uuidBook = uuidBook;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
