package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Date;
import java.sql.Time;
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
import ca.mcgill.ecse321.tutoringsystem.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;
import ca.mcgill.ecse321.tutoringsystem.service.TimeSlotService;
import ca.mcgill.ecse321.tutoringsystem.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {

  @Autowired
  TimeSlotService timeSlotService;
  @Autowired
  TutorService tutorService;

  @PostMapping(value = {"/timeslot/create", "/timeslot/create"})
  public TimeSlotDto createTimeSlot(@RequestParam("id") Integer tutorId, @RequestParam("date") Date date,
      @RequestParam("time") Time time) throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.createTimeSlot(tutorService.getTutor(tutorId), date, time);
    return DtoConverter.toDto(timeSlot);
  }

  @GetMapping(value = {"/timeslots/{id}", "/timeslots/{id}/"})
  public TimeSlotDto getTimeSlotById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(id);
    return DtoConverter.toDto(timeSlot);
  }
  
  @DeleteMapping(value = {"/timeslots/{id}", "/timeslots/{id}/"})
  public boolean deleteTimeSlotById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    return timeSlotService.deleteTimeSlot(id);
  }
  
  @GetMapping(value = {"/timeslots/tutor/{tutor}", "/timeslots/tutor/{tutor}/"})
  public Set<TimeSlotDto> getTimeSlotByTutor(@PathVariable("tutor") Integer tutorId) throws IllegalArgumentException {
    Set<TimeSlot> timeSlotSet = new HashSet<TimeSlot>(timeSlotService.getTimeSlotByTutor(tutorService.getTutor(tutorId)));
    return DtoConverter.timeSlotSetToDto(timeSlotSet);
  }

  @GetMapping(value = {"/timeslots/{date}/{time}", "/timeslots/{date}/{time}/"})
  public TimeSlotDto getTimeSlotByDateAndTime(@PathVariable("date") Date date, @PathVariable("time") Time time)
      throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(date, time);
    return DtoConverter.toDto(timeSlot);
  }
}
