package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

/**
 * This class converts all the model classes to its Dto counter parts
 */
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
		CourseDto courseDto = new CourseDto(c.getCourseName(), toDto(c.getInstitution()), c.getSubjectName(),
				wageSetToDto(c.getWage()));
		return courseDto;
	}

	public static Set<CourseDto> courseSetToDto(Set<Course> c) {
		if (c == null) {
			return null;
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
		InstitutionDto institutionDto = new InstitutionDto(i.getInstitutionName(), toDto(i.getInstitutionLevel()));
		return institutionDto;
	}

	public static SchoolLevelDto toDto(SchoolLevel l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such SchoolLevel!");
		}
		if (l.equals(SchoolLevel.CEGEP)) {
			return SchoolLevelDto.CEGEP;
		} else if (l.equals(SchoolLevel.HighSchool)) {
			return SchoolLevelDto.HighSchool;
		} else {
			return SchoolLevelDto.University;
		}
	}
	
	public static NotificationTypeDto toDto(NotificationType l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such NotificationType!");
		}
		if (l.equals(NotificationType.Accepted)) {
			return NotificationTypeDto.Accepted;
		} else if (l.equals(NotificationType.Rejected)) {
			return NotificationTypeDto.Rejected;
		} else {
			return NotificationTypeDto.Requested;
		}
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
		NotificationDto notificationDto = new NotificationDto(
				toDto(n.getRequest()), toDto(n.getTutor()), n.getNotificationId(), toDto(n.getNotificationType()));
		return notificationDto;
	}

	public static Set<NotificationDto> notificationSetToDto(Set<Notification> n) {
		if (n == null) {
			return null;
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
		RequestDto requestDto = new RequestDto(toDto(r.getTutor()), toDto(r.getStudent()), r.getTime(), r.getDate(), toDto(r.getCourse()), toDto(r.getRoom()), r.getRequestId());
		return requestDto;
	}

	public static ReviewDto toDto(Review r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Review!");
		}
		ReviewDto reviewDto = new ReviewDto(r.getRating(), r.getComment(), toDto(r.getFrom()), toDto(r.getTo()),
				r.getReviewId());
		return reviewDto;
	}

	public static PersonDto toDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getName(), p.getEmail(), p.getUserId(), p.getPassword());
		return personDto;
	}
	
	public static StudentDto toDto(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("There is no such Student!");
		}
		StudentDto studentDto = new StudentDto(s.getName(), s.getEmail(), s.getUserId(), s.getPassword(), reviewSetToDto(s.getReview()), requestSetToDto(s.getRequest()));
		return studentDto;
	}

	public static RoomDto toDto(Room r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such Room!");
		}
		RoomDto roomDto = new RoomDto(r.getCapacity(), r.getRoomNumber(), requestSetToDto(r.getRequest()));
		return roomDto;
	}

	public static Set<RequestDto> requestSetToDto(Set<Request> r) {
		if (r == null) {
			return null;
		}
		Set<RequestDto> requests = new HashSet<>();
		for (Request request : r) {
			requests.add(toDto(request));
		}
		return requests;
	}

	public static TimeSlotDto toDto(TimeSlot t) {
		if (t == null) {
			throw new IllegalArgumentException("There is no such TimeSlot!");
		}
		TimeSlotDto timeSlotDto = new TimeSlotDto(t.getDate(), t.getTime(), t.getTimeSlotId());
		return timeSlotDto;
	}

	public static Set<TimeSlotDto> timeSlotSetToDto(Set<TimeSlot> t) {
		if (t == null) {
			return null;
		}
		Set<TimeSlotDto> timeSlots = new HashSet<>();
		for (TimeSlot timeSlot : t) {
			timeSlots.add(toDto(timeSlot));
		}
		return timeSlots;
	}

	public static Set<ReviewDto> reviewSetToDto(Set<Review> r) {
		if (r == null) {
			return null;
		}
		Set<ReviewDto> reviews = new HashSet<>();
		for (Review review : r) {
			reviews.add(toDto(review));
		}
		return reviews;
	}

	public static TutorDto toDto(Tutor t) {
		if (t == null) {
			throw new IllegalArgumentException("There is no such Tutor!");
		}
		Set<TimeSlotDto> timeSlotDto = timeSlotSetToDto(t.getTimeslot()); 
		Set<WageDto> wageDto = wageSetToDto(t.getWage());
		Set<NotificationDto> notificationDto = notificationSetToDto(t.getNotification());
		Set<ReviewDto> reviewDto = reviewSetToDto(t.getReview());

		TutorDto tutorDto = new TutorDto(t.getName(), t.getEmail(), t.getUserId(), t.getPassword(), timeSlotDto,
				wageDto, notificationDto, reviewDto);
		return tutorDto;
	}
	
	public static Set<TutorDto> tutorSetToDto(Set<Tutor> t) {
	  if (t == null) {
		return null;
	  }
	  Set<TutorDto> tutors = new HashSet<>();
	  for (Tutor tutor: t) {
	    tutors.add(toDto(tutor));
	  }
	  return tutors;
	}

	public static Set<WageDto> wageSetToDto(Set<Wage> w) {
		if (w == null) {
			return null;
		}
		Set<WageDto> wages = new HashSet<>();
		for (Wage wage : w) {
			wages.add(toDto(wage));
		}
		return wages;
	}
	
	public static WageDto toDto(Wage w) {
		if (w == null) {
			throw new IllegalArgumentException("There is no such Wage!");
		}
		WageDto wageDto = new WageDto(w.getWage(), w.getWageId(), w.getCourse().getCourseName());
		return wageDto;
	}

}
