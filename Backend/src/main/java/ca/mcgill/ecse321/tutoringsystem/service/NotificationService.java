package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.NotificationRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Notification;
import ca.mcgill.ecse321.tutoringsystem.model.Request;

@Service
public class NotificationService {
  @Autowired
  NotificationRepository notificationRepository;
  @Transactional
  public Notification createNotification(Request request) {
      if (request == null) {
          throw new IllegalArgumentException("Notification ID cannot be null!");
      }
      Notification n = new Notification();
      n.setRequest(request);
      n.setTutor(request.getTutor());
      notificationRepository.save(n);
      return n;
  }

  @Transactional
  public Notification getNotification(Integer notificationId) {
      Notification w = notificationRepository.findNotificationByNotificationId(notificationId);
      return w;
  }

  @Transactional
  public List<Notification> getAllNotifications() {
      return toList(notificationRepository.findAll());
  }
  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
}
