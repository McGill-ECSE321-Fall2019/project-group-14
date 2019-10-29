package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.WageDto;
import ca.mcgill.ecse321.tutoringsystem.model.Wage;
import ca.mcgill.ecse321.tutoringsystem.service.CourseService;
import ca.mcgill.ecse321.tutoringsystem.service.TutorService;
import ca.mcgill.ecse321.tutoringsystem.service.WageService;

@CrossOrigin(origins = "*")
@RestController
public class WageController {
  @Autowired
  WageService wageService;
  @Autowired
  TutorService tutorService;
  @Autowired
  CourseService courseService;

  @PostMapping(value = {"/wages/create", "/wages/create/"})
  public WageDto createTimeSlot(@RequestParam("tutorId") Integer tutorId, @RequestParam("course") String courseName,
      @PathVariable("wage") Integer wageAmount) throws IllegalArgumentException {
    Wage wage = wageService.createWage(tutorService.getTutor(tutorId), courseService.getCourse(courseName), wageAmount);
    return DtoConverter.toDto(wage);
  }

  @GetMapping(value = {"/wages/{id}", "/wages/{id}/"})
  public WageDto getWageById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    Wage wage = wageService.getWage(id);
    return DtoConverter.toDto(wage);
  }
  
  @DeleteMapping(value = {"/wages/{id}", "/wages/{id}/"})
  public boolean deleteWageById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    return wageService.deleteWage(id);
  }

  @GetMapping(value = {"/wages", "/wages/"})
  public Set<WageDto> getAllWages() throws IllegalArgumentException {
    Set<Wage> wageSet = new HashSet<Wage>(wageService.getAllWages());
    return DtoConverter.wageSetToDto(wageSet);
  }

  @GetMapping(value = {"/wages/tutor/{tutor}", "/wages/tutor/{tutor}/"})
  public Set<WageDto> getAllWagesByTutor(@PathVariable("tutor") Integer tutorId) throws IllegalArgumentException {
    Set<Wage> wageSet = new HashSet<Wage>(wageService.getTutorWages(tutorService.getTutor(tutorId)));
    return DtoConverter.wageSetToDto(wageSet);
  }

  @GetMapping(value = {"/wages/course/{course}", "/wages/course/{course}/"})
  public Set<WageDto> getAllWagesByCourse(@PathVariable("course") String courseName) throws IllegalArgumentException {
    Set<Wage> wageSet = new HashSet<Wage>(wageService.getCourseWages(courseService.getCourse(courseName)));
    return DtoConverter.wageSetToDto(wageSet);
  }

}
