package ca.mcgill.ecse321.tutoringsystem.dao;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "timeslots", path = "timeslots")
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer> {
	TimeSlot findTimeSlotByTimeSlotId(int timeSlotId);

	List<TimeSlot> findTimeSlotByDateAndTime(Date date, Time time);
}
