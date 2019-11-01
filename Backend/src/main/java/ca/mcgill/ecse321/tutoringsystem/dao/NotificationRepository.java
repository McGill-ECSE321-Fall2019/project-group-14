package ca.mcgill.ecse321.tutoringsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Notification;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findNotificationByNotificationId(int notificationId);
	List<Notification> findNotificationByTutor(Tutor tutor);
}
