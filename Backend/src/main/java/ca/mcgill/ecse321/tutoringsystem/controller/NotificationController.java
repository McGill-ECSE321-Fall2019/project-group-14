package ca.mcgill.ecse321.tutoringsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	RequestController controller;
	
	@PostMapping(value = { "/notifications", "/notification/"})
	public ManagerDto createPerson(@PathVariable("requestId") Integer requestId) throws IllegalArgumentException {
		Notification manager = notificationService.createNotification(request);
		return convertToDto(manager);
	}

	private NotificationDto convertToDto(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}
}
