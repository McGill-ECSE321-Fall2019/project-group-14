package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
  Student findStudentById(int studenId);
  Student findStudentByEmail(String email);
}
