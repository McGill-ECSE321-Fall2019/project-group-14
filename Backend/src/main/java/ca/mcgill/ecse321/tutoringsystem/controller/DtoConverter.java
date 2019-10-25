package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.TutorService;

public class DtoConverter {
  @Autowired
  TutorService tutorService;

  private static ApplicationDto toDto(Application a) {
    if (a == null) {
      throw new IllegalArgumentException("There is no such Application!");
    }
    ApplicationDto applicationDto =
        new ApplicationDto(a.getIsExistingUser(), a.getName(), a.getEmail(), a.getCourses(), a.getApplicationId());
    return applicationDto;
  }

  public static ManagerDto toDto(Manager m) {
    if (m == null) {
      throw new IllegalArgumentException("There is no such Manager!");
    }
    ManagerDto managerDto = new ManagerDto(m.getName(), m.getEmail(), m.getPassword());
    return managerDto;
  }

  private static TimeSlotDto toDto(TimeSlot t) {
    if (t == null) {
      throw new IllegalArgumentException("There is no such TimeSlot!");
    }
    TimeSlotDto timeSlotDto = new TimeSlotDto(t.getDate(), t.getTime(), t.getTimeSlotId());
    return timeSlotDto;
  }

  public static Set<TimeSlotDto> timeSlotSetToDto(Set<TimeSlot> t) {
    if (t == null) {
      throw new IllegalArgumentException("There is no such TimeSlot");
    }
    Set<TimeSlotDto> timeSlots = new HashSet<>();
    for (TimeSlot timeSlot : t) {
      timeSlots.add(toDto(timeSlot));
    }
    return timeSlots;
  }

  public static TutorDto toDto(Tutor t) {
    if (t == null) {
      throw new IllegalArgumentException("There is no such Tutor!");
    }
    Set<TimeSlotDto> timeSlotDto = timeSlotSetToDto(t.getTimeslot());
    Set<WageDto> wageDto = wageSetToDto(t.getWage());
    Set<RequestDto> requestDto = toDto(t.getRequest());
    Set<NotificationDto> notificationDto = toDto(t.getNotification());
    Set<ReviewDto> reviewDTo = toDto(t.getReview());

    TutorDto tutorDto = new TutorDto(t.getName(), t.getEmail(), t.getUserId(), t.getPassword(), timeSlotDto, wageDto,
        requestDto, notificationDto, reviewDto);
    return tutorDto;
  }

  public static Set<WageDto> wageSetToDto(Set<Wage> w) {
    if (w == null) {
      throw new IllegalArgumentException("There is no such Wage");
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
    WageDto wageDto = new WageDto(w.getWage(), w.getWageId());
    return wageDto;
  }

}
