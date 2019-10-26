package ca.mcgill.ecse321.tutoringsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@PostMapping(value = { "/manager/create", "/manager/create/" })
	public ManagerDto createPerson(@RequestParam("name") String name, @PathVariable("email") String email,
			@PathVariable("password") String password) throws IllegalArgumentException {
		Manager manager = managerService.createManager(name, email, password);
		return DtoConverter.toDto(manager);
	}

	@GetMapping(value = { "/manager/{id}" })
	public ManagerDto getManagerById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return DtoConverter.toDto(managerService.getManager(id));
	}

	@GetMapping(value = { "/manager/{email}" })
	public ManagerDto getManagerById(@PathVariable("email") String email) throws IllegalArgumentException {
		return DtoConverter.toDto(managerService.getManager(email));
	}

}
