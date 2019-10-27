package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.TutorDto;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class TutorController {
  @Autowired
  TutorService tutorService;

  @PostMapping(value = {"/tutors/create", "/tutors/create/"})
  public TutorDto createTutor(@PathVariable("name") String name, @PathVariable("email") String email,
      @PathVariable("password") String password) throws IllegalArgumentException {
    Tutor tutor = tutorService.createTutor(name, email, password);
    return DtoConverter.toDto(tutor);
  }
  
  @GetMapping(value = {"/tutors/{id}", "/tutors/{id}/"})
  public TutorDto getTutorById(@PathVariable("id") Integer id) throws IllegalArgumentException {
      Tutor tutor = tutorService.getTutor(id);
      return DtoConverter.toDto(tutor);
  }
  
  @GetMapping(value = {"/tutors/email/{email}", "/tutors/email/{email}/"})
  public TutorDto getTutorByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
      Tutor tutor = tutorService.getTutor(email);
      return DtoConverter.toDto(tutor);
  }
  
  @GetMapping(value = {"/tutors", "/tutors/"})
  public Set<TutorDto> getAllTutors() throws IllegalArgumentException {
      Set<Tutor> tutorSet= new HashSet<Tutor>(tutorService.getAllTutors());
      return DtoConverter.tutorSetToDto(tutorSet);
  }

}
