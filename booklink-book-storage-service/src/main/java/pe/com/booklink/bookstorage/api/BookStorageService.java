package pe.com.booklink.bookstorage.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.booklink.bookstorage.domain.BookStorage;
import pe.com.booklink.bookstorage.domain.BookStorageRepository;
import pe.com.booklink.domain.dto.RegistrationBookRequest;
import pe.com.booklink.util.TopicsBooklink;

@Service
public class BookStorageService {
	
	private BookStorageRepository bookStorageRepository;
	
	@Autowired
	public void setBookStorageRepository(BookStorageRepository bookStorageRepository) {
		this.bookStorageRepository = bookStorageRepository;
	}
	
	@KafkaListener(topics = TopicsBooklink.TOPIC_REGISTRATION_BOOK, groupId = "save-book-storage")
	public void listenBookRegistration(@Payload RegistrationBookRequest request) {
		request = saveInDB(request);
	}
	
//	@Transactional("transactionManager")
	@Transactional
	public RegistrationBookRequest saveInDB(RegistrationBookRequest request) {
		Optional<BookStorage> op = bookStorageRepository.findById(request.getBook().getUuid());
		BookStorage bookStorage = null; 
		if(op.isPresent()) {
			bookStorage = op.get();
			Integer newQuantity = new Integer(bookStorage.getQuantity().intValue() + 1);
			bookStorage.setQuantity(newQuantity);
		}else {
			bookStorage = new BookStorage();
			bookStorage.setUuidBook(request.getBook().getUuid());
			bookStorage.setQuantity(new Integer(1));
		}
		bookStorage = bookStorageRepository.save(bookStorage);
		return request;
	}
}
