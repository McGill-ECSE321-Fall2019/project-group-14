package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Set<CourseDto> getAllCourses() {
		Set<Course> courses = new HashSet<Course>(courseService.getAllCourses());
		return DtoConverter.courseSetToDto(courses);
	}
	
	@GetMapping(value = { "/courses/subject/{subject}", "/courses/subject/{subject}/" })
	public Set<CourseDto> getCourseBySubject(@PathVariable(name = "subject") String subject) throws IllegalArgumentException {
		Set<Course> courses = new HashSet<Course>(courseService.getAllCoursesWithSubject(subject));
		return DtoConverter.courseSetToDto(courses);
	}
	
	@GetMapping(value = { "/courses/institution/{institution}", "/courses/institution/{institution}/" })
	public Set<CourseDto> getCourseByInstitution(@PathVariable(name = "institution") String institution) throws IllegalArgumentException {
		Set<Course> courses = new HashSet<Course>(courseService.getCoursesByInstitution(institution));
		return DtoConverter.courseSetToDto(courses);
	}
	
	@GetMapping(value = { "/courses/{course}", "/courses/{course}/" })
	public CourseDto getCourseByName(@PathVariable(name = "course") String course) throws IllegalArgumentException {
		return DtoConverter.toDto(courseService.getCourse(course));
	}
	
	@PostMapping(value = { "/courses/create", "/courses/create/" })
	public CourseDto createCourse(@RequestParam(name = "name") String name,
			@RequestParam(name = "institution") String institution, @RequestParam(name = "subject") String subject) throws IllegalArgumentException {
		Course c = courseService.createCourse(name, institutionService.getInstitution(institution), subject);
		return DtoConverter.toDto(c);
	}
	
	@DeleteMapping(value = { "/courses/{course}", "/courses/{course}/" })
	public boolean deleteCourseByName(@PathVariable(name = "course") String course) throws IllegalArgumentException {
		return courseService.deleteCourse(course);
	}
}
