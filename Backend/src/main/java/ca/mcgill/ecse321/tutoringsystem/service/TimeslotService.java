package ca.mcgill.ecse321.tutoringsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

@Service
public class TimeSlotService {
  @Autowired
  TimeSlotRepository timeslotRepository;

  public TimeSlot createTimeSlot(Tutor tutor, Date date, Time time) {
    if (tutor == null) {
      throw new IllegalArgumentException("A tutor needs to be specified!");
    }
    if (date == null | time == null) {
      throw new IllegalArgumentException("Date and time cannot be empty!");
    }
    TimeSlot t = new TimeSlot();
    t.setTutor(tutor);
    t.setDate(date);
    t.setTime(time);
    timeslotRepository.save(t);
    return t;
  }

  @Transactional
  public TimeSlot getTimeSlot(Integer timeSlotId) {
    TimeSlot t = timeslotRepository.findTimeSlotByTimeSlotId(timeSlotId);
    return t;
  }

  @Transactional
  public List<TimeSlot> getTimeSlot(Date date, Time time) {
    if (date == null || time == null) {
      throw new IllegalArgumentException("Date and time cannot be null!");
    }
    return timeslotRepository.findTimeSlotByDateAndTime(date, time);
  }

}