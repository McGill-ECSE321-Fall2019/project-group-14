package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.InstitutionRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Institution;
import ca.mcgill.ecse321.tutoringsystem.model.SchoolLevel;

@Service
public class InstitutionService {
  @Autowired
  InstitutionRepository institutionRepository;

  @Transactional
  public Institution createInstitution(String institutionName, SchoolLevel institutionLevel) {
    if (institutionName == null) {
      throw new IllegalArgumentException("Institution name cannot be null!");
    }
    Institution i = new Institution();
    i.setInstitutionName(institutionName);
    i.setInstitutionLevel(institutionLevel);
    institutionRepository.save(i);
    return i;
  }

  @Transactional
  public Institution getInstitution(String institutionName) {
    Institution i = institutionRepository.findInstitutionByInstitutionName(institutionName);
    return i;
  }

  @Transactional
  public List<Institution> getAllInstitutions() {
    return toList(institutionRepository.findAll());
  }

  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
}
