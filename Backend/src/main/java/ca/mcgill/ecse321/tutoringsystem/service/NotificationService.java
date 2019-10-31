package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.NotificationRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Notification;
import ca.mcgill.ecse321.tutoringsystem.model.NotificationType;
import ca.mcgill.ecse321.tutoringsystem.model.Request;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;

@Service
public class NotificationService {
  @Autowired
  NotificationRepository notificationRepository;

  @Transactional
  public Notification createNotification(Request request, NotificationType type) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null!");
    }
    if (type == null) {
        throw new IllegalArgumentException("NotificationType cannot be null!");
    }
    Notification n = new Notification();
    n.setRequest(request);
    n.setTutor(request.getTutor());
    n.setNotificationType(type);
    notificationRepository.save(n);
    notify(getNotification(n.getNotificationId()));
    return n;
  }

  public void notify(Notification n) {
	Mail mail = new Mail();
    String contenttext = "";
    String subject = "";
		switch (n.getNotificationType()) {
		case Requested:
			subject = "New Request";
			contenttext = n.getRequest().getStudent().getName() + " has requested a session for "
					+ n.getRequest().getCourse().getCourseName() + " at " + n.getRequest().getTime() + " on " + n.getRequest().getDate()
					+ ".";
			break;
		case Accepted:
			subject = "Session Confirmation";
			contenttext = "The session for " + n.getRequest().getCourse().getCourseName() + " with "
					+ n.getRequest().getStudent().getName() + " at " + n.getRequest().getTime() + " on " + n.getRequest().getDate()
					+ " has been confirmed. It will take place in Room " + n.getRequest().getRoom().getRoomNumber() + ".";
			break;
		case Rejected:
			subject = "Session Cancelled";
			contenttext = "The session for " + n.getRequest().getCourse().getCourseName() + " with "
					+ n.getRequest().getStudent().getName() + " at " + n.getRequest().getTime() + " on " + n.getRequest().getDate()
					+ " has been cancelled or could not have been created.";
			break;
		}
    mail.setFrom(new Email("tutoringSystem@mcgill.ca"));
    mail.setTemplateId("d-60dacaed87e44dfbaf071956a6ece8ac");
    Personalization personalization = new Personalization();
    personalization.addDynamicTemplateData("name", n.getTutor().getName());
    personalization.addDynamicTemplateData("contenttext", contenttext);
    personalization.addDynamicTemplateData("subject", subject);
    personalization.addTo(new Email(n.getTutor().getEmail()));
    mail.addPersonalization(personalization);
    
    SendGrid sg =
        new SendGrid("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");
    com.sendgrid.Request request2 = new com.sendgrid.Request();
    try {
      request2.setMethod(Method.POST);
      request2.setEndpoint("mail/send");
      request2.setBody(mail.build());
      sg.api(request2);
    } catch (IOException ex) {

    }
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

  @Transactional
  public boolean deleteNotification(Integer id) {
    Notification n = notificationRepository.findNotificationByNotificationId(id);
    if (n == null) {
      throw new NullPointerException("No Notification by this id.");
    }
    notificationRepository.delete(n);
    return true;
  }
}
