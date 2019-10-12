package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
  Student findStudentByUserId(int userId);
  Student findStudentByEmail(String email);
}
