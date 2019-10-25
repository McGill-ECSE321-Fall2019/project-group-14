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
}
