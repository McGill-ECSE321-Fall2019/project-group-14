package ca.mcgill.ecse321.tutoringsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
	Application findApplicationByApplicationId(int applicationId);

	List<Application> findApplicationByEmail(String email);
}
