package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Institution;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "institutions", path = "institutions")
public interface InstitutionRepository extends CrudRepository<Institution, Integer> {
	Institution findInstitutionByInstitutionName(String insitutionName);
}
