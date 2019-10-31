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
	
	/**
	 * This method logs in the user to its corresponding role
	 * It gets the person's role (ie subclass of Person) by finding it's email
	 * It then compares the inputted password with the good password to login
	 * @param email
	 * @param password
	 * @return the user or null
	 */
	@Transactional
	public Person login(String email, String password) {
		if (email == null || email.length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty!");
		} else if (password == null || password.length() == 0) {
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
	
	@Transactional
	public Person getPersonById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id cannot be empty!");
		}
		Manager manager = managerService.getManager(id);
		Tutor tutor = tutorService.getTutor(id);
		Student student = studentService.getStudent(id);
		if (manager != null) {
			return manager;
		} else if (tutor != null) {
			return tutor;
		} else if (student != null) {
			return student;
		}
		return null;
	}
}
