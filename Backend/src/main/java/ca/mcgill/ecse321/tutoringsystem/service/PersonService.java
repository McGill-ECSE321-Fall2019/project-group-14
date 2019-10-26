package ca.mcgill.ecse321.tutoringsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringsystem.model.Manager;
import ca.mcgill.ecse321.tutoringsystem.model.Person;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

@Service
public class PersonService {
	@Autowired
	StudentService studentService;
	@Autowired
	TutorService tutorService;
	@Autowired
	ManagerService managerService;
	
	@Transactional
	public Person login(String email, String password) {
		if (email == null) {
			throw new IllegalArgumentException("Email cannot be empty!");
		} else if (password == null) {
			throw new IllegalArgumentException("Password cannot be empty!");
		}
		Manager manager = managerService.getManager(email);
		Tutor tutor = tutorService.getTutor(email);
		Student student = studentService.getStudent(email);
		if (manager != null && manager.getPassword().equals(password)) {
			return manager;
		} else if (tutor != null && tutor.getPassword().equals(password)) {
			return tutor;
		} else if (student != null && student.getPassword().equals(password)) {
			return student;
		}
		return null;
	}
}
