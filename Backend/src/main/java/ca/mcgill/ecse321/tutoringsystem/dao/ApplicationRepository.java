package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Application;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "applications", path = "applications")
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
	Application findApplicationByApplicationId(int applicationId);

	Application findApplicationByEmail(String email);
}
