package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Course;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	Course findCourseByCourseName(String courseName);

	List<Course> findCourseBySubjectName(String subjectName);
}
