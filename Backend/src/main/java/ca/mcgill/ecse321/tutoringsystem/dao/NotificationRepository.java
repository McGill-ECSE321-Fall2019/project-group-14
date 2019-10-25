package ca.mcgill.ecse321.tutoringsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Notification;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(collectionResourceRel = "notifcations", path = "notifications")
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findNotificationByNotificationId(int notificationId);
}
