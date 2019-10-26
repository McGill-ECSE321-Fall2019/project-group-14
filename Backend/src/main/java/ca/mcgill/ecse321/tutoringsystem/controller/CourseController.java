package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.CourseDto;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.service.CourseService;
import ca.mcgill.ecse321.tutoringsystem.service.InstitutionService;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {
	
	@Autowired
	CourseService courseService;
	@Autowired
	InstitutionService institutionService;
	
	@GetMapping(value = { "/courses", "/courses/" })
	public List<CourseDto> getAllCourses() {
		List<CourseDto> courseDtos = new ArrayList<>();
		for (Course course : courseService.getAllCourses()) {
			courseDtos.add(DtoConverter.toDto(course));
		}
		return courseDtos;
	}
	
	@GetMapping(value = { "/courses", "/courses/" })
	public List<CourseDto> getCourseBySubject(@RequestParam(name = "subject") String subject) throws IllegalArgumentException {
		List<CourseDto> courseDtos = new ArrayList<>();
		for (Course course : courseService.getAllCoursesWithSubject(subject)) {
			courseDtos.add(DtoConverter.toDto(course));
		}
		return courseDtos;
	}
	
	@GetMapping(value = { "/courses/{course}", "/courses/{course}/" })
	public CourseDto getCourseByName(@RequestParam(name = "course") String course) throws IllegalArgumentException {
		return DtoConverter.toDto(courseService.getCourse(course));
	}
	
	@PostMapping(value = { "/courses/create", "/courses/create/" })
	public CourseDto createCourse(@RequestParam(name = "name") String name,
			@RequestParam(name = "institution") String institution, @RequestParam(name = "subject") String subject) throws IllegalArgumentException {
		Course c = courseService.createCourse(name, institutionService.getInstitution(institution), subject);
		return DtoConverter.toDto(c);
	}
}
