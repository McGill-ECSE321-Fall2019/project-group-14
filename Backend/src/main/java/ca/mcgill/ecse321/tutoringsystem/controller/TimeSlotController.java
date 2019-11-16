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

  @PostMapping(value = {"/timeslots/create", "/timeslots/create"})
  public TimeSlotDto createTimeSlot(@RequestParam("id") Integer tutorId, @RequestParam("date") String date,
      @RequestParam("time") String time) throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.createTimeSlot(tutorService.getTutor(tutorId), Date.valueOf(date), Time.valueOf(time));
    return DtoConverter.toDto(timeSlot);
  }

  @GetMapping(value = {"/timeslots/{id}", "/timeslots/{id}/"})
  public TimeSlotDto getTimeSlotById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(id);
    return DtoConverter.toDto(timeSlot);
  }
  
  @GetMapping(value = {"/timeslots/tutor/{id}", "/timeslots/tutor/{id}/"})
  public Set<TimeSlotDto> getTimeSlotByTutor(@PathVariable("id") Integer id) throws IllegalArgumentException {
    Set<TimeSlot> timeslots = new HashSet<TimeSlot>(timeSlotService.getTimeSlotByTutor(tutorService.getTutor(id)));
    return DtoConverter.timeSlotSetToDto(timeslots);
  }
  
  @DeleteMapping(value = {"/timeslots/{id}", "/timeslots/{id}/"})
  public boolean deleteTimeSlotById(@PathVariable("id") Integer id) throws IllegalArgumentException {
    return timeSlotService.deleteTimeSlot(id);
  }

  /**
   * 
   * @param date
   * @param time
   * @return TimeSlot at that date and time or null
   * @throws IllegalArgumentException
   */
  @GetMapping(value = {"/timeslots/{date}/{time}", "/timeslots/{date}/{time}/"})
  public TimeSlotDto getTimeSlotByDateAndTime(@PathVariable("date") String date, @PathVariable("time") String time)
      throws IllegalArgumentException {
    TimeSlot timeSlot = timeSlotService.getTimeSlot(Date.valueOf(date), Time.valueOf(time));
    return DtoConverter.toDto(timeSlot);
  }
}
