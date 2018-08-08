package pe.com.booklink.registration.autor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@Column(name = "uuid", length = 100)
	private String uuid;
	@Column(name = "fullname", length = 300)
	private String fullName;
	
	public Autor() {
		
	}
	
	public Autor(String uuid, String fullName) {
		this.uuid = uuid;
		this.fullName = fullName;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
