package pe.com.booklink.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BooklinkBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooklinkBookServiceApplication.class, args);
	}
}
