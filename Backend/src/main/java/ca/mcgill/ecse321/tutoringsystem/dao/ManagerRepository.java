package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Manager;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "managers", path = "managers")
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
	Manager findManagerByUserId(int userId);

	Manager findManagerByEmail(String email);
}
