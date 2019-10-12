package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
  Manager findManagerByUserId(int userId);
  Manager findManagerByEmail(String email);
}
