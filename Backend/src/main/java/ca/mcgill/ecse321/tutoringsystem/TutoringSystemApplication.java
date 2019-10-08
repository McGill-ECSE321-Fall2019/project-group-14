package ca.mcgill.ecse321.tutoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class TutoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutoringSystemApplication.class, args);
	}
	
	@RequestMapping("/")
  	public String greeting(){
    		return "Hello world!";
  	}
}
