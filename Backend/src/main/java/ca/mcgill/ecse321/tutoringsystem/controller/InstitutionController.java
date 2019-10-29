package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.InstitutionDto;
import ca.mcgill.ecse321.tutoringsystem.model.Institution;
import ca.mcgill.ecse321.tutoringsystem.model.SchoolLevel;
import ca.mcgill.ecse321.tutoringsystem.service.InstitutionService;

@CrossOrigin(origins = "*")
@RestController
public class InstitutionController {

	@Autowired
	InstitutionService institutionService;
	
	@GetMapping(value = { "/institutions", "/institutions/" })
	public List<InstitutionDto> getAllInstitutions() {
		List<InstitutionDto> institutionDtos = new ArrayList<>();
		for (Institution institution : institutionService.getAllInstitutions()) {
			institutionDtos.add(DtoConverter.toDto(institution));
		}
		return institutionDtos;
	}
	
	@GetMapping(value = { "/institutions/{name}", "/institutions/{name}/" })
	public InstitutionDto getInstitutionByName(@PathVariable(name = "name") String name) throws IllegalArgumentException {
		return DtoConverter.toDto(institutionService.getInstitution(name));
	}
	
	@DeleteMapping(value = { "/institutions/{name}", "/institutions/{name}/" })
	public boolean deleteInstitutionByName(@PathVariable(name = "name") String name) throws IllegalArgumentException {
		return institutionService.deleteInstitution(name);
	}
	
	@PostMapping(value = { "/institutions/create", "/institutions/create/" })
	public InstitutionDto createInstitution(@RequestParam(name = "name") String name, @RequestParam(name = "level") String level) throws IllegalArgumentException {
		Institution i = institutionService.createInstitution(name, Enum.valueOf(SchoolLevel.class, level));
		return DtoConverter.toDto(i);
	}
}
