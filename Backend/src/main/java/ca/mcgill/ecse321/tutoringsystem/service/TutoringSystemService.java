package ca.mcgill.ecse321.tutoringsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutoringSystemService {
  @Autowired
  TutorService tutorService; //
  @Autowired
  StudentService studentService; //
  @Autowired
  ManagerService managerService; //
  @Autowired
  RequestService requestService; //
  @Autowired
  CourseService courseService; //
  @Autowired
  RoomService roomService; //
  @Autowired
  NotificationService notificationService; //
  @Autowired
  ReviewService reviewService; //
  @Autowired
  ApplicationService applicationService; //
  @Autowired
  InstitutionService institutionService; //
  @Autowired
  WageService wageService;
  @Autowired
  TimeSlotService timeslotService; //
}
