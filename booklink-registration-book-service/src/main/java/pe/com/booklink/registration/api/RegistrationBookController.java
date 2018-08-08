package pe.com.booklink.registration.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.booklink.domain.dto.RegistrationBookRequest;

@RestController
@RequestMapping("/api/book")
public class RegistrationBookController {
	
	private RegistrationBookService registrationBookService;
	
	@Autowired
	public void setRegistrationBookService(RegistrationBookService registrationBookService) {
		this.registrationBookService = registrationBookService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegistrationBookRequest request){
		registrationBookService.registerMessage(request);
		return ResponseEntity.ok().build();
	}
}
