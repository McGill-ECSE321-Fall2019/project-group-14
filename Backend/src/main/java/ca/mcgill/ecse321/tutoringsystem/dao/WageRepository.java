package ca.mcgill.ecse321.tutoringsystem.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Wage;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "wages", path = "wagess")
public interface WageRepository extends CrudRepository<Wage, Integer> {
	Wage findWageByWageId(int wageId);

	List<Wage> findWageByTutor(Tutor tutor);
}
