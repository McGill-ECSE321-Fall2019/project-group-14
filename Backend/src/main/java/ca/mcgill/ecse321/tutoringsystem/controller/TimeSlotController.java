package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Date;
import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.service.TimeSlotService;

@CrossOrigin(origins = "*")
@RestController
public class TimeSlotController {

  @Autowired
  TimeSlotService timeSlotService;

  @PostMapping(value = {"/timeslot/create", "/timeslot/create"})
  public TimeSlotDto createTimeSlot(@PathVariable("Id") Tutor id, @PathVariable("Date") Date date,
      @PathVariable("Time") Time time) throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.createTimeSlot(id, date, time);
    return DtoConverter.toDto(timeSlot);
  }

  @PostMapping(value = {"/timeslot", "/timeslot/"})
  public TimeSlotDto getTimeSlotById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(id);
    return DtoConverter.toDto(timeSlot);
  }

  @PostMapping(value = {"/timeslot", "/timeslot/"})
  public TimeSlotDto getTimeSlotByDateAndTime(@PathVariable("Date") Date date, @PathVariable("Time") Time time)
      throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(date, time);
    return DtoConverter.toDto(timeSlot);
  }
}
