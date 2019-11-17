package ca.mcgill.ecse321.tutoringsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Manager;

@Service
public class ManagerService {
  @Autowired
  ManagerRepository managerRepository;
  
  @Transactional
  public Manager createManager(String name, String email, String password) {
      if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0
              || password.trim().length() == 0) {
          throw new IllegalArgumentException("Manager name, email or password cannot be empty!");
      }
      if (getManager(email) != null) {
    	  throw new IllegalArgumentException("Email is already in use!");
      }
      Manager m = new Manager();
      m.setName(name);
      m.setEmail(email);
      m.setPassword(password);
      managerRepository.save(m);
      return m;
  }

  @Transactional
  public Manager getManager(Integer id) {
      if (id == null) {
          throw new IllegalArgumentException("Tutor Id cannot be empty!");
      }
      Manager m = managerRepository.findManagerByUserId(id);
      return m;
  }

  @Transactional
  public Manager getManager(String email) {
      if (email == null) {
          throw new IllegalArgumentException("Tutor email cannot be empty!");
      }
      Manager m = managerRepository.findManagerByEmail(email);
      return m;
  }
  
  @Transactional
  public boolean deleteManager(Integer id) {
      Manager m = managerRepository.findManagerByUserId(id);
      if (m == null) {
          throw new NullPointerException("No Manager by this id.");
      }
      managerRepository.delete(m);
      return true;
  }
  
  @Transactional
  public boolean deleteManager(String email) {
      Manager m = managerRepository.findManagerByEmail(email);
      if (m == null) {
          throw new NullPointerException("No Manager by this email.");
      }
      managerRepository.delete(m);
      return true;
  }
}
