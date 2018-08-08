package pe.com.booklink.book.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.booklink.book.domain.Book;
import pe.com.booklink.book.domain.BookRepository;
import pe.com.booklink.domain.dto.RegistrationBookRequest;
import pe.com.booklink.util.TopicsBooklink;

@Service
public class BookService {
	
	private KafkaTemplate<String, RegistrationBookRequest> template;
	
	private BookRepository bookRepository;
	
	@Autowired
	public void setTemplate(KafkaTemplate<String, RegistrationBookRequest> template) {
		this.template = template;
	}
	
	@Autowired
	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional
	@KafkaListener(topics = {TopicsBooklink.TOPIC_REGISTRATION_BOOK}, groupId = "save-book")
	public void listenBookRegistration(@Payload RegistrationBookRequest request) {
		Book book = new Book();
		book.setUuid(UUID.randomUUID().toString());
		book.setTitle(request.getBook().getTitle());
		book.setDescription(request.getBook().getDescription());
		
		book = bookRepository.save(book);
		
		request.getBook().setUuid(book.getUuid());
		template.send(TopicsBooklink.TOPIC_REGISTRATION_BOOK, request.getId(), request);
	}
}
