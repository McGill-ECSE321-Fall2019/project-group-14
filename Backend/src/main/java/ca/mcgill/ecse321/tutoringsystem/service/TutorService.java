 package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

@Service
public class TutorService {
  @Autowired
  TutorRepository tutorRepository; 
  @Transactional
  public Tutor createTutor(String name, String email, String password) {
      if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0
              || password.trim().length() == 0) {
          throw new IllegalArgumentException("Tutor name, email or password cannot be empty!");
      }
      if (getTutor(email) != null) {
    	  throw new IllegalArgumentException("Email is already in use!");
      }
      Tutor t = new Tutor();
      t.setName(name);
      t.setEmail(email);
      t.setPassword(password);
      tutorRepository.save(t);
      return t;
  }

  @Transactional
  public Tutor getTutor(Integer id) {
      if (id == null) {
          throw new IllegalArgumentException("Tutor Id cannot be empty!");
      }
      Tutor t = tutorRepository.findTutorByUserId(id);
      return t;
  }

  @Transactional
  public Tutor getTutor(String email) {
      if (email == null || email.trim().length() == 0) {
          throw new IllegalArgumentException("Tutor email cannot be empty!");
      }
      Tutor t = tutorRepository.findTutorByEmail(email);
      return t;
  }

  @Transactional
  public List<Tutor> getAllTutors() {
      return toList(tutorRepository.findAll());
  }
  
  @Transactional
  public boolean deleteTutor(Integer id) {
      Tutor t = tutorRepository.findTutorByUserId(id);
      if (t == null) {
          throw new NullPointerException("No Tutor by this id.");
      }
      tutorRepository.delete(t);
      return true;
  }
  
  @Transactional
  public boolean deleteTutor(String email) {
      Tutor t = tutorRepository.findTutorByEmail(email);
      if (t == null) {
          throw new NullPointerException("No Tutor by this email.");
      }
      tutorRepository.delete(t);
      return true;
  }
  
  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
  
  /**
   * This method simply changes all the settings of the Tutor in 1 method.
   * It checks for invalid inputs before setting it
   * @param id
   * @param name
   * @param password
   * @return error or Tutor
   */
  @Transactional
  public Tutor changeTutorSettings(Integer id, String name, String password) {
    if (id == null) {
    	throw new IllegalArgumentException("Tutor Id cannot be empty! ");
    }
    Tutor t = getTutor(id);
    if (t == null) { return null; }
    if (name != null) {
    	t.setName(name);
    }
    if (password != null) {
    	t.setPassword(password);
    }
    tutorRepository.save(t);
    return t;
  }

}