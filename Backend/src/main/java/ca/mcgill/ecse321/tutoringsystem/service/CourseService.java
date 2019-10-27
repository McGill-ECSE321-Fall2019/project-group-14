package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Institution;

@Service
public class CourseService {
  @Autowired
  CourseRepository courseRepository;

  @Transactional
  public Course createCourse(String name, Institution institution, String subjectName) {
    String error = "";
    if (name == null || name.trim().length() == 0) {
      error = error + "Course name cannot be empty! ";
    }
    if (institution == null) {
      error = error + "Course email cannot be empty! ";
    }
    if (subjectName == null || subjectName.trim().length() == 0) {
      error = error + "Course course time cannot be empty! ";
    }
    error = error.trim();
    if (error.length() > 0) {
      throw new IllegalArgumentException(error);
    }
    Course c = new Course();
    c.setCourseName(name);
    c.setInstitution(institution);
    c.setSubjectName(subjectName);
    courseRepository.save(c);
    return c;
  }

  @Transactional
  public Course getCourse(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Course name cannot be empty!");
    }
    Course p = courseRepository.findCourseByCourseName(name);
    return p;
  }

  @Transactional
  public List<Course> getAllCourses() {
    return toList(courseRepository.findAll());
  }

  @Transactional
  public List<Course> getAllCoursesWithSubject(String subjectName) {
    if (subjectName == null) {
      throw new IllegalArgumentException("Subject name cannot be null!");
    }
    List<Course> coursesWithSubject = new ArrayList<>();
    for (Course c : courseRepository.findCourseBySubjectName(subjectName)) {
      coursesWithSubject.add(c);
    }
    return coursesWithSubject;
  }
  
  @Transactional
  public void deleteCourse(String name) {
      Course c = courseRepository.findCourseByCourseName(name);
      if (c == null) {
          throw new NullPointerException("No Course by this name.");
      }
      courseRepository.delete(c);
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
