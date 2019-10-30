package ca.mcgill.ecse321.tutoringsystem.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Wage;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public interface WageRepository extends CrudRepository<Wage, Integer> {
	Wage findWageByWageId(int wageId);

	List<Wage> findWageByTutor(Tutor tutor);
	List<Wage> findWageByCourse(Course course);
}
