package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, Integer> {
	Tutor findTutorByUserId(int userId);

	Tutor findTutorByEmail(String email);
}
