package ca.mcgill.ecse321.tutoringsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	@PostMapping(value = { "/manager", "/manager/" })
	public ManagerDto createPerson(@PathVariable("name") String name, @PathVariable("email") String email,
			@PathVariable("password") String password) throws IllegalArgumentException {
		// @formatter:on
		Manager manager = managerService.createManager(name, email, password);
		return DtoConverter.convertToDto(manager);
	}

	@GetMapping(value = { "/manager/{id}" })
	public ManagerDto getManagerById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return DtoConverter.convertToDto(managerService.getManager(id));
	}

	@GetMapping(value = { "/manager/{name}" })
	public ManagerDto getManagerById(@PathVariable("name") String name) throws IllegalArgumentException {
		return DtoConverter.convertToDto(managerService.getManager(name));
	}

}
