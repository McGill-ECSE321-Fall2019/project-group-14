package ca.mcgill.ecse321.tutoringsystem.dao;

import java.sql.Time;
import java.util.List;
import java.sql.Date;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer> {
	TimeSlot findTimeSlotByTimeSlotId(int timeSlotId);

	TimeSlot findTimeSlotByDateAndTime(Date date, Time time);
	
	List<TimeSlot> findTimeSlotByTutor(Tutor tutor);
}
