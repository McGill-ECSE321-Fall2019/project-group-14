package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

@RepositoryRestResource(collectionResourceRel = "tutors", path = "tutors")
public interface TutorRepository extends CrudRepository<Tutor, Integer> {
	Tutor findTutorByUserId(int userId);

	Tutor findTutorByEmail(String email);
}
