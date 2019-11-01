package ca.mcgill.ecse321.tutoringsystem;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.tutoringsystem.dao.ApplicationRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.InstitutionRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.NotificationRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RequestRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.WageRepository;
import ca.mcgill.ecse321.tutoringsystem.model.TimeSlot;
import ca.mcgill.ecse321.tutoringsystem.model.Wage;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class TutoringSystemIntegrationTests {

	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private WageRepository wageRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;

	private final String APP_URL = "http://tutoringsystem-backend-14.herokuapp.com";
	private JSONObject response;
	private final String restName = "TestUser";
	private final String restEmail = "ecse321test@protonmail.com";
	private final String restEmailManager = "ecse321testmanager@protonmail.com";
	private final String restEmailStudent = "ecse321teststudent@protonmail.com";
	private final String restPassword = "userpassword";

	@Test
	public void contextLoads() {
		System.out.println("ran Integration file");
	}

	@After
	public void clearDatabase() {
//		requestRepository.deleteAll();
//		tutorRepository.deleteAll();
//		managerRepository.deleteAll();
//		studentRepository.deleteAll();
//		timeslotRepository.deleteAll();
//		wageRepository.deleteAll();
//		institutionRepository.deleteAll();
//		applicationRepository.deleteAll();
//		reviewRepository.deleteAll();
//		notificationRepository.deleteAll();
//		roomRepository.deleteAll();
//		courseRepository.deleteAll();
	}

	/*
	 * TESTING
	 */

	@Test
	public void testCreateTutor() {
		try {
			response = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword);
			System.out.println("Received: " + response.toString());
			assertEquals(restName, response.getString("name"));
		} catch (JSONException e) {
			fail();
		}
	}

	@Test
	public void testUpdateTutorPassword() {
		try {
			String newPassword = "userpassword321";
			response = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword);
			System.out.println(response.getString("userId"));
			response = send("PUT", APP_URL, "/tutors/update/" + response.getString("userId"),
					"name=" + restName + "&email=" + restEmail + "&password=" + newPassword + "&timeslots=&wage=");
			assertEquals("true", response.toString());
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * APPLICATION
	 */

	@Test
	public void testCreateApplication() {
		try {
			response = send("POST", APP_URL, "/applications/create",
					"existing=false" + "&name=" + restName + "&email=" + restEmail + "&courses=ECSE321");
			System.out.println("Received: " + response.toString());
			assertEquals(restName, response.getString("name"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * COURSE
	 */

	@Test
	public void testCreateCourse() {
		try {
			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University").getString("institutionName");
			response = send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics");
			System.out.println("Received: " + response.toString());
			assertEquals("MATH262", response.getString("name"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * INSTITUTION
	 */

	@Test
	public void testCreateInstitution() {
		try {
			response = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University");
			System.out.println("Received: " + response.toString());
			assertEquals("McGill", response.getString("institutionName"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * MANAGER
	 */

	@Test
	public void testCreateManager() {
		try {
			response = send("POST", APP_URL, "/managers/create",
					"name=" + restName + "&email=" + restEmailManager + "&password=" + restPassword);
			System.out.println("Received: " + response.toString());
			assertEquals(restName, response.getString("name"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * NOTIFICATION
	 */

	@Test
	public void testCreateNotifcation() {
		try {
//			JSONObject request = send("POST", APP_URL, "/requests/create", "time=" + Time.valueOf("08:00:01") +"&");
			response = send("POST", APP_URL, "/notifications/create", "requestId=null" + "&notificationType=Accepted");
			System.out.println("Received: " + response.toString());
			assertEquals(restName, response.getString("name"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * REVIEW
	 */

	@Test
	public void testCreateReview() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			String studentId = send("POST", APP_URL, "/students/create",
					"name=" + restName + "&email=" + restEmailStudent + "&password=" + restPassword)
							.getString("userId");
			response = send("POST", APP_URL, "/reviews/create",
					"rating=4" + "&comment=This_is_a_comment." + "&from=" + tutorId + "&to=" + studentId);
			System.out.println("Received: " + response.toString());
			assertEquals("4", response.getString("rating"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * ROOM
	 */

	@Test
	public void testCreateRoom() {
		try {
			response = send("POST", APP_URL, "/rooms/create", "id=21" + "&capacity=23");
			System.out.println("Received: " + response.toString());
			assertEquals("23", response.getString("capacity"));
		} catch (JSONException e) {
			fail();
		}
	}

	/*
	 * STUDENT
	 */

	@Test
	public void testCreateStudent() {
		try {
			response = send("POST", APP_URL, "/students/create",
					"name=" + restName + "&email=" + restEmailStudent + "&password=" + restPassword);
			System.out.println("Received: " + response.toString());
			assertEquals(restEmailStudent, response.getString("email"));
		} catch (JSONException e) {
			fail();
		}
	}
	
	/*
	 * TIMESLOT
	 */
	
	@Test
	public void testCreateTimeSlot() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			response = send("POST", APP_URL, "/timeslots/create",
					"id=" + tutorId + "&date=" +  Date.valueOf("2019-09-22") + "&time=" + Time.valueOf("08:00:01"));
			System.out.println("Received: " + response.toString());
			assertEquals(tutorId, response.getString("id"));
		} catch (JSONException e) {
			fail();
		}
	}
	
	/*
	 * WAGE
	 */
	
	@Test
	public void testCreateWage() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			
			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University").getString("institutionName");
			
			String courseName = send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics").getString("courseName");
			
			response = send("POST", APP_URL, "/wages/create",
					"tutorId=" + tutorId + "&course=" + courseName + "&wage=40");
			System.out.println("Received: " + response.toString());
			assertEquals(tutorId, response.getString("id"));
		} catch (JSONException e) {
			fail();
		}
	}
	
	public JSONObject send(String type, String appURL, String path, String parameters) {
		try {
			URL URL = new URL(appURL + path + ((parameters == null) ? "" : ("?" + parameters)));
			System.out.println("Sending: " + URL.toString());
			HttpURLConnection c = (HttpURLConnection) URL.openConnection();
			c.setRequestMethod(type);
			c.setRequestProperty("Accept", "application/json");
			System.out.println(c.getContentType());
			if (c.getResponseCode() != 200) {
				throw new RuntimeException(URL.toString() + " Returned error: " + c.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((c.getInputStream())));
			String response = br.readLine();
			if (response != null) {
				JSONObject json = new JSONObject(response);
				c.disconnect();
				return json;
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
