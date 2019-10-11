package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
  Application findApplicationById(int applicationId);
  Application findApplicationByEmail(String email);
}
