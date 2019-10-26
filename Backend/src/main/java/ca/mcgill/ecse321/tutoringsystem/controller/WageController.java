package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.WageDto;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.model.Wage;
import ca.mcgill.ecse321.tutoringsystem.service.WageService;


@CrossOrigin(origins = "*")
@RestController
public class WageController {
  @Autowired
  WageService wageService;

  @PostMapping(value = {"/wage/create", "/wage/create/"})
  public WageDto createTimSlot(@PathVariable("Id") Tutor tutor, @PathVariable("Course") Course course,
      @PathVariable("Time") Integer nb) throws IllegalArgumentException {
    Wage wage = wageService.createWage(tutor, course, nb);
    return DtoConverter.toDto(wage);
  }

  @PostMapping(value = {"/wage", "/wage/"})
  public WageDto getWageById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    Wage wage = wageService.getWage(id);
    return DtoConverter.toDto(wage);
  }

  @PostMapping(value = {"/allwages", "/allwages/"})
  public Set<WageDto> getAllWages() throws IllegalArgumentException {
    Set<Wage> wageSet = new HashSet<Wage>(wageService.getAllWages());
    return DtoConverter.wageSetToDto(wageSet);
  }

  @PostMapping(value = {"/allwagesbytutor", "/allwagesbytutor/"})
  public Set<WageDto> getAllWagesByTutor(@PathVariable("Tutor") Tutor tutor) throws IllegalArgumentException {
    Set<Wage> wageSet = new HashSet<Wage>(wageService.getTutorWages(tutor));
    return DtoConverter.wageSetToDto(wageSet);
  }

  @PostMapping(value = {"/allwagesbycourse", "/allwagesbycourse/"})
  public Set<WageDto> getAllWagesByCourse(@PathVariable("Course") Course course) throws IllegalArgumentException {
    Set<Wage> wageSet = new HashSet<Wage>(wageService.getCourseWages(course));
    return DtoConverter.wageSetToDto(wageSet);
  }

}
