package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, Integer>{
  Tutor findTutorbyId(int tutorId);
  Tutor findTutorbyEmail(String email);
}
