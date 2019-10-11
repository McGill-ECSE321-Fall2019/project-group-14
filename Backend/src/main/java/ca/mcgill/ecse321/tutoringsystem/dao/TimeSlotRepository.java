package ca.mcgill.ecse321.tutoringsystem.dao;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer>{
  TimeSlot findTimeSlotbyId(int timeSlotId);
  List<TimeSlot> findTimeSlotByDateAndTime(Date date, Time time);
}
