package ca.mcgill.ecse321.tutoringsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.PersonDto;
import ca.mcgill.ecse321.tutoringsystem.dto.RequestDto;
import ca.mcgill.ecse321.tutoringsystem.model.Manager;
import ca.mcgill.ecse321.tutoringsystem.model.Person;
import ca.mcgill.ecse321.tutoringsystem.model.Request;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.service.PersonService;
import ca.mcgill.ecse321.tutoringsystem.service.RequestService;

@CrossOrigin(origins = "*")
@RestController
public class BusinessMethodController {
	@Autowired
	PersonService personService;
	@Autowired
	RequestService requestService;
	
	@PostMapping(value = { "/login", "/login/" })
	public PersonDto login(@PathVariable("email") String email, @PathVariable("password") String password) throws IllegalArgumentException {
		Person person = personService.login(email, password);
		if (person instanceof Manager) {
			return DtoConverter.toDto(person); 
		} else if (person instanceof Tutor) {
			return DtoConverter.toDto(person); 
		} else if (person instanceof Student) {
			return DtoConverter.toDto(person); 
		} else {
			// login failed, returned person null
			return null;
		}
	}
	
	@PostMapping(value = { "/accept/{id}", "/accept/{id}/" })
	public RequestDto acceptRequest(@PathVariable("id") Integer id) throws IllegalArgumentException {
		requestService.acceptRequest(id);
		Request request = requestService.getRequest(id);
		if (request.getRoom() != null) {
			return DtoConverter.toDto(request);
		} else {
			return null;
		}
	}
	
	@PostMapping(value = { "/reject/{id}", "/reject/{id}/" })
	public void rejectRequest(@PathVariable("id") Integer id) throws IllegalArgumentException {
		requestService.rejectRequest(id);
		return;
	}
	
	//TODO: reserve room for tutoring session (by manager)
	//TODO: change account info
}
