package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.ApplicationRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Application;
import ca.mcgill.ecse321.tutoringsystem.model.Room;

@Service
public class ApplicationService {
  @Autowired
  ApplicationRepository applicationRepository;

  @Transactional
  public Application createApplication(Boolean isExistingUser, String name, String email, String course) {
    String error = "";
    if (isExistingUser == null) {
      error = error + "User cannot be empty! ";
    }
    if (name == null || name.trim().length() == 0) {
      error = error + "Application name cannot be empty! ";
    }
    if (email == null || email.trim().length() == 0) {
      error = error + "Application email cannot be empty! ";
    }
    if (course == null) {
      error = error + "Course cannot be empty! ";
    }
    error = error.trim();
    if (error.length() > 0) {
      throw new IllegalArgumentException(error);
    }
    Application t = new Application();
    t.setIsExistingUser(isExistingUser);
    t.setName(name);
    t.setEmail(email);
    t.setCourses(course);
    applicationRepository.save(t);
    return t;
  }

  @Transactional
  public Application getApplication(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Application id cannot be empty!");
    }
    Application p = applicationRepository.findApplicationByApplicationId(id);
    return p;
  }

  @Transactional
  public List<Application> getApplication(String email) {
    if (email == null) {
      throw new IllegalArgumentException("Application email cannot be empty!");
    }
    return toList(applicationRepository.findApplicationByEmail(email));
  }
  
  @Transactional
  public List<Application> getAllApplications() {
    return toList(applicationRepository.findAll());
  }

  private <T> List<T> toList(Iterable<T> iterable) {
	List<T> resultList = new ArrayList<T>();
	for (T t : iterable) {
		resultList.add(t);
	}
	return resultList;
  }
}
