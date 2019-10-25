package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

public class DtoConverter {

	private static ApplicationDto toDto(Application a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Application!");
		}
		ApplicationDto applicationDto = new ApplicationDto(a.getIsExistingUser(), a.getName(), a.getEmail(),
				a.getCourses(), a.getApplicationId());
		return applicationDto;
	}

	public static ManagerDto toDto(Manager m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Manager!");
		}
		ManagerDto managerDto = new ManagerDto(m.getName(), m.getEmail(), m.getPassword());
		return managerDto;
	}

	public static NotificationDto toDto(Notification n) {
		if (n == null) {
			throw new IllegalArgumentException("There is no such Notification!");
		}
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setRequest(createRequestDtoForNotification(n));
		return notificationDto;
	}
	
	public static Set<NotificationDto> toDto(Set<Notification> n) {
		if (n == null) {
			throw new IllegalArgumentException("There is no such Notification!");
		}
		Set<NotificationDto> notifications = new HashSet<>();
		for (Notification notification : n) {
			notifications.add(toDto(notification));
		}
		return null;
		
	}
	
	public static RequestDto createRequestDtoForNotification(Notification n) {
		
		return null;	
	}
	
	public static RequestDto toDto(Request r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Request!");
		}
		RequestDto requestDto = new RequestDto();
		return requestDto;
	}
}
