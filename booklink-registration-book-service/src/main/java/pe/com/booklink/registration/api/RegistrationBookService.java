package pe.com.booklink.registration.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import pe.com.booklink.domain.dto.RegistrationBookRequest;
import pe.com.booklink.registration.autor.Autor;
import pe.com.booklink.registration.autor.AutorRepository;
import pe.com.booklink.registration.autorbook.AutorBook;
import pe.com.booklink.registration.autorbook.AutorBookRepository;
import pe.com.booklink.registration.book.Book;
import pe.com.booklink.registration.book.BookRepository;
import pe.com.booklink.util.TopicsBooklink;
import pe.com.booklink.util.Util;

@Service
public class RegistrationBookService {

	private KafkaTemplate<String, RegistrationBookRequest> template;
	
	private BookRepository bookRepository;
	
	private AutorRepository autorRepository;
	
	private AutorBookRepository autorBookRepository;
	
	@Autowired
	public void setTemplate(KafkaTemplate<String, RegistrationBookRequest> template) {
		this.template = template;
	}
	
	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Autowired
	public void setAutorRepository(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
	
	@Autowired
	public void setAutorBookRepository(AutorBookRepository autorBookRepository) {
		this.autorBookRepository = autorBookRepository;
	}
	
	@Transactional("kafkaTransactionManager")
	public ListenableFuture<SendResult<String, RegistrationBookRequest>> registerMessage(RegistrationBookRequest request) {
		request = saveInDB(request);
		request.setId(Util.getRandomUUID());
		ListenableFuture<SendResult<String, RegistrationBookRequest>> future = template.send(TopicsBooklink.TOPIC_REGISTRATION_BOOK, request.getId(), request);
		return future;
	}
	
	@Transactional("transactionManager")
	public RegistrationBookRequest saveInDB(RegistrationBookRequest request) {
		Book book = new Book();
		book.setTitle(request.getBook().getTitle());
		book.setDescription(request.getBook().getDescription());
		book.setUuid(Util.getRandomUUID());
		book = bookRepository.save(book);
		
		final String uuidBook = book.getUuid();
		request.getBook().setUuid(uuidBook);
		
		for(pe.com.booklink.ddd.Autor record : request.getAutors()) {
			Autor autor = new Autor(Util.getRandomUUID(), record.getFullName());
			autor = autorRepository.save(autor);
			
			AutorBook autorBook = new AutorBook(Util.getRandomUUID(), uuidBook, autor.getUuid());
			autorBook = autorBookRepository.save(autorBook);
			
			record.setUuid(autor.getUuid());
		}
		return request;
	}
}
