package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findNotificationByNotificationId(int notificationId);
}
