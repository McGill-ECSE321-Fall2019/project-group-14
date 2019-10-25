package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

public class DtoConverter {

	public static ApplicationDto toDto(Application a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Application!");
		}
		ApplicationDto applicationDto = new ApplicationDto(a.getIsExistingUser(), a.getName(), a.getEmail(),
				a.getCourses(), a.getApplicationId());
		return applicationDto;
	}

	public static CourseDto toDto(Course c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Course!");
		}
		CourseDto courseDto = new CourseDto(c.getCourseName(), toDto(c.getInstitution()), c.getSubjectName(), toDto(c.getWage())); 
		return courseDto;
	}
	
	public static Set<CourseDto> toDto(Set<Course> c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Course!");
		}
		Set<CourseDto> courses = new HashSet<>();
		for (Course course : c) {
			courses.add(toDto(course));
		}
		return courses;
	}
	
	public static InstitutionDto toDto(Institution i) {
		if (i == null) {
			throw new IllegalArgumentException("There is no such Institution!");
		}
		InstitutionDto institutionDto = new InstitutionDto(i.getInstitutionName(), toDto(i.getCourses()), toDto(i.getInstitutionLevel()));
		return institutionDto;
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
		NotificationDto notificationDto = new NotificationDto(toDto(n.getRequest(), toDto(n.getTutor()), n.getNotificationId()));
		return notificationDto;
	}
	
	public static Set<NotificationDto> notificationSetToDto(Set<Notification> n) {
		if (n == null) {
			throw new IllegalArgumentException("There is no such Notification!");
		}
		Set<NotificationDto> notifications = new HashSet<>();
		for (Notification notification : n) {
			notifications.add(toDto(notification));
		}
		return notifications;
		
	}
	
	public static RequestDto toDto(Request r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Request!");
		}
		RequestDto requestDto = new RequestDto(toDto(r.getTutor(), toDto(r.getStudent(), r.getTime(), r.getDate(), toDto(r.getNotification(), toDto(r.getCourse(), toDto(r.getCourse(), toDto(r.getRoom(), r.getRequestId())))))));
		return requestDto;
	}
}
