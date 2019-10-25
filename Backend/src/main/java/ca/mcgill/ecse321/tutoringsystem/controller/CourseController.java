package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.ApplicationDto;
import ca.mcgill.ecse321.tutoringsystem.dto.CourseDto;
import ca.mcgill.ecse321.tutoringsystem.model.Application;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.service.ApplicationService;
import ca.mcgill.ecse321.tutoringsystem.service.CourseService;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping(value = { "/courses", "/courses/" })
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courseDtos = new ArrayList<>();
		for (Course course : courseService.getAllCourses()) {
			courseDtos.add(DtoConverter.toDto(course));
		}
		return courseDtos;
	}
	
	@GetMapping(value = { "/applications/{input}", "/applications/{input}/" })
	public List<ApplicationDto> getApplicationBy(@RequestParam(name = "input") String input) {
		List<ApplicationDto> applicationDtos = new ArrayList<>();
		if (input.chars().allMatch(Character::isDigit)) {
			// input is a number, get application by id
			Application application = applicationService.getApplication(Integer.parseInt(input));
			applicationDtos.add(DtoConverter.toDto(application));
			return applicationDtos;
		} else {
			for (Application application : applicationService.getApplication(input)) {
				applicationDtos.add(DtoConverter.toDto(application));
			}
			return applicationDtos;
		}
	}
	
	@PostMapping(value = { "/applications/apply", "/applications/apply/" })
	public ApplicationDto registerPersonForEvent(@RequestParam(name = "existing") Boolean isExistingUser,
		@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
		@RequestParam(name = "courses") String courses) throws IllegalArgumentException {

		Application a = applicationService.createApplication(isExistingUser, name, email, courses);
		return DtoConverter.toDto(a);
	}
}
