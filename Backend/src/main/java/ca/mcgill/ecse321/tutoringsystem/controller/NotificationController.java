package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	@Autowired
	RequestService requestService;
	@Autowired
	TutorService tutorService;
	
	@GetMapping(value = {"/notifications/tutor/{id}", "/notifications/tutor/{id}"})
	public List<NotificationDto> getAllTutorNotifications(@PathVariable("id") Integer id) {
		List<NotificationDto> notificationDtos = new ArrayList<>();
		for (Notification notification : notificationService.getNotificationByTutor(tutorService.getTutor(id))) {
			notificationDtos.add(DtoConverter.toDto(notification));
		}
		return notificationDtos;
	}
	
	@GetMapping(value = { "/notifications/{id}", "/notifications/{id}/"})
	public NotificationDto getNotificationById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return DtoConverter.toDto(notificationService.getNotification(id));
	}
	
	@GetMapping(value = {"/notifications", "/notifications/"})
	public List<NotificationDto> getAllNotifications() {
		List<NotificationDto> notificationDtos = new ArrayList<>();
		for (Notification notification : notificationService.getAllNotifications()) {
			notificationDtos.add(DtoConverter.toDto(notification));
		}
		return notificationDtos;	
	}
	
	@DeleteMapping(value = { "/notifications/{id}", "/notifications/{id}/" })
	public boolean deleteNotificationById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return notificationService.deleteNotification(id);
	}
}
