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
import ca.mcgill.ecse321.tutoringsystem.dto.StudentDto;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
  @Autowired
  StudentService studentService;

  @PostMapping(value = {"/students/create", "/students/create/"})
  public StudentDto createStudent(@RequestParam(name = "name") String name,
      @RequestParam(name = "email") String email, @RequestParam(name = "password") String password)
      throws IllegalArgumentException {
    Student student = studentService.createStudent(name, email, password);
    return DtoConverter.toDto(student);
  }

  @GetMapping(value = {"/students/{id}", "/students/{id}/"})
  public StudentDto getStudent(@PathVariable("id") Integer studentId) throws IllegalArgumentException {
    Student student = studentService.getStudent(studentId);
    return DtoConverter.toDto(student);
  }
  
  @GetMapping(value = {"/students/email/{email}", "/students/email/{email}/"})
  public StudentDto getStudent(@PathVariable("email") String email) throws IllegalArgumentException {
    Student student = studentService.getStudent(email);
    return DtoConverter.toDto(student);
  }

  @GetMapping(value = {"/students", "/students/"})
  public List<StudentDto> getAllStudents() {
    List<StudentDto> studentDtos = new ArrayList<>();
    for (Student student : studentService.getAllStudents()) {
      studentDtos.add(DtoConverter.toDto(student));
    }
    return studentDtos;
  }
  
  @DeleteMapping(value = { "/students/{input}", "/students/{input}/" })
	public boolean deleteStudentBy(@PathVariable(name = "input") String input) throws IllegalArgumentException {
		if (input.chars().allMatch(Character::isDigit)) {
			// input is a number, get application by id
			return studentService.deleteStudent(Integer.parseInt(input));
		} else {
			return studentService.deleteStudent(input);
		}
	}
}
