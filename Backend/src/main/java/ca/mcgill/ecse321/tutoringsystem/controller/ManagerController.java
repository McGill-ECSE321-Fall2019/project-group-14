package ca.mcgill.ecse321.tutoringsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@PostMapping(value = { "/managers/create", "/managers/create/" })
	public ManagerDto createPerson(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password) throws IllegalArgumentException {
		Manager manager = managerService.createManager(name, email, password);
		return DtoConverter.toDto(manager);
	}

	@GetMapping(value = { "/managers/{id}", "/managers/{id}/"})
	public ManagerDto getManagerById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return DtoConverter.toDto(managerService.getManager(id));
	}

	@GetMapping(value = { "/managers/email/{email}", "/managers/email/{email}/" })
	public ManagerDto getManagerById(@PathVariable("email") String email) throws IllegalArgumentException {
		return DtoConverter.toDto(managerService.getManager(email));
	}
	
	@DeleteMapping(value = { "/managers/{input}", "/managers/{input}/" })
	public boolean deleteManagerBy(@PathVariable(name = "input") String input) throws IllegalArgumentException {
		if (input.chars().allMatch(Character::isDigit)) {
			// input is a number, get application by id
			return managerService.deleteManager(Integer.parseInt(input));
		} else {
			return managerService.deleteManager(input);
		}
	}

}
