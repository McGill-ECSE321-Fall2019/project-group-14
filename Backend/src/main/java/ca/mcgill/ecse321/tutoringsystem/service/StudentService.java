package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Student;

@Service
public class StudentService {
  @Autowired
  StudentRepository studentRepository;

  @Transactional
  public Student createStudent(String name, String email, String password) {
    if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0
        || password.trim().length() == 0) {
      throw new IllegalArgumentException("Student name, email or password cannot be empty!");
    }
    Student s = new Student();
    s.setName(name);
    s.setEmail(email);
    s.setPassword(password);
    studentRepository.save(s);
    return s;
  }

  @Transactional
  public Student getStudent(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Tutor Id cannot be empty!");
    }
    Student s = studentRepository.findStudentByUserId(id);
    return s;
  }

  @Transactional
  public Student getStudent(String email) {
    if (email == null) {
      throw new IllegalArgumentException("Tutor email cannot be empty!");
    }
    Student s = studentRepository.findStudentByEmail(email);
    return s;
  }

  @Transactional
  public List<Student> getAllStudents() {
    return toList(studentRepository.findAll());
  }
  
  @Transactional
  public void deleteStudent(Integer id) {
      Student s = studentRepository.findStudentByUserId(id);
      if (s == null) {
          throw new NullPointerException("No Student by this id.");
      }
      studentRepository.delete(s);
      return;
  }
  
  @Transactional
  public void deleteStudent(String email) {
      Student s = studentRepository.findStudentByEmail(email);
      if (s == null) {
          throw new NullPointerException("No Student by this email.");
      }
      studentRepository.delete(s);
      return;
  }
  
  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }

}
