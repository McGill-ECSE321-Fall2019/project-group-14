package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
	Application findApplicationByApplicationId(int applicationId);

	Application findApplicationByEmail(String email);
}
