package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Institution;

public interface InstitutionRepository extends CrudRepository<Institution, Integer> {
	Institution findInstitutionByInstitutionName(String insitutionName);
}
