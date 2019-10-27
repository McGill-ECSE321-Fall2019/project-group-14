package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Date;
import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public TimeSlotDto createTimeSlot(@PathVariable("id") Integer tutorId, @PathVariable("date") Date date,
      @PathVariable("time") Time time) throws IllegalArgumentException {
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

  @GetMapping(value = {"/timeslots/{date}/{time}", "/timeslots/{date}/{time}/"})
  public TimeSlotDto getTimeSlotByDateAndTime(@PathVariable("date") Date date, @PathVariable("time") Time time)
      throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(date, time);
    return DtoConverter.toDto(timeSlot);
  }
}
