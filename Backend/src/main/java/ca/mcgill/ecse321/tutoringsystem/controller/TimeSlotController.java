package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping(value = {"/timeslot", "/timeslot"})
  public TimeSlotDto createTimeSlot(@PathVariable("id") Tutor id, @PathVariable("Date") Date date, @PathVariable("Time") Time time) throws IllegalArgumentException {
      TimeSlot timeSlot = timeSlotService.createTimeSlot(id, date, time);
      return convertToDto(timeSlot);
  }
  
  private TimeSlotDto convertToDto(TimeSlot timeSlot) {
    if (timeSlot == null) {
      throw new IllegalArgumentException("There is no such TimeSlot!");
    }
    TimeSlotDto timeSlotDto = new TimeSlotDto(timeSlot.getDate(),timeSlot.getTime(),timeSlot.getTimeSlotId());
    return timeSlotDto;
  }
}
