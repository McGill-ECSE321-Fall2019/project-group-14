package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;
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

  @PostMapping(value = {"/tutors", "/tutors"})
  public TutorDto createTutor(@PathVariable("name") String name, @PathVariable("email") String email,
      @PathVariable("password") String password) throws IllegalArgumentException {
    Tutor tutor = tutorService.createTutor(name, email, password);
    return convertToDto(tutor);
  }

  @GetMapping(value = {"/events", "/events/"})
  public List<TutorDto> getAllTutors() {
    List<TutorDto> tutorDtos = new ArrayList<>();
    for (Tutor tutor : tutorService.getAllEvents()) {
      tutorDtos.add(convertToDto(tutor));
    }
    return tutorDtos;
  }

}
