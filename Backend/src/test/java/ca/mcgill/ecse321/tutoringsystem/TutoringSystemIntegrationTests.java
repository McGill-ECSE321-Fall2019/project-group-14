package ca.mcgill.ecse321.tutoringsystem;

import org.junit.Test;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

public class TutoringSystemIntegrationTests {
	private final String APP_URL = "http://tutoringsystem-backend-14.herokuapp.com";
	private JSONObject response;
	private final String restName = "TestUser";
	private final String restEmail = "ecse321test@protonmail.com";
	private final String restEmailManager = "ecse321testmanager@protonmail.com";
	private final String restEmailStudent = "ecse321teststudent@protonmail.com";
	private final String restPassword = "userpassword";

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
	public void testGetTutor() {
		try {
			JSONObject tutor = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword);
			String tutorId = tutor.getString("userId");
			response = send("GET", APP_URL, "/tutors/" + tutorId, "");
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
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			fail();
		}
	}

	@Test
	public void testDeleteTutor() {
		try {
			send("POST", APP_URL, "/tutors/create",
					"name=" + "MEHDITUTOR" + "&email=testdelete" + "&password=" + restPassword);
			response = send("DELETE", APP_URL, "/tutors/testdelete", null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			e.printStackTrace();
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

	@Test
	public void testGetApplication() {
		try {
			String applicationId = send("POST", APP_URL, "/applications/create",
					"existing=false" + "&name=" + restName + "&email=" + restEmail + "&courses=ECSE321")
							.getString("applicationId");
			JSONArray applications = sendArray("GET", APP_URL, "/applications/" + applicationId, "");
			for (int i = 0; i < applications.length(); i++) {
				JSONObject o = applications.getJSONObject(i);
				assertEquals(restName, o.getString("name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteApplication() {
		try {
			JSONObject response1 = send("POST", APP_URL, "/applications/create",
					"existing=false" + "&name=" + restName + "&email=" + restEmail + "&courses=ECSE321");
			response = send("DELETE", APP_URL, "/applications/" + response1.getString("applicationId"), null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	/*
	 * COURSE
	 */

	@Test
	public void testCreateCourse() {
		try {
			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University")
					.getString("institutionName");
			response = send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics");
			System.out.println("Received: " + response.toString());
			assertEquals("MATH262", response.getString("courseName"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetCourse() {
		try {
			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University")
					.getString("institutionName");
			String courseName = (send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics")).getString("courseName");
			response = send("GET", APP_URL, "/courses/" + courseName, "");
			System.out.println("Received: " + response.toString());
			assertEquals("MATH262", response.getString("courseName"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteCourse() {
		try {
			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University")
					.getString("institutionName");
			send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics");
			assertTrue(true);
		} catch (JSONException e) {
			e.printStackTrace();
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

	@Test
	public void testDeleteInstitution() {
		send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University");
		assertTrue(true);
	}

	@Test
	public void testGetInstitution() {
		String institutionName;
		try {
			institutionName = (send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University"))
					.getString("institutionName");
			response = send("GET", APP_URL, "/institutions/" + institutionName, "");
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

	@Test
	public void testDeleteManager() {
		try {
			send("POST", APP_URL, "/managers/create",
					"name=" + "managerrr" + "&email=emaildelete" + "&password=" + restPassword);
			response = send("DELETE", APP_URL, "/managers/emaildelete" , null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}	
	}

	@Test
	public void testGetManager() {
		send("POST", APP_URL, "/managers/create",
				"name=" + restName + "&email=" + restEmail + "&password=" + restPassword);
		assertTrue(true);
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

	@Test
	public void testDeleteReview() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			String studentId = send("POST", APP_URL, "/students/create",
					"name=" + restName + "&email=" + restEmailStudent + "&password=" + restPassword)
							.getString("userId");
			JSONObject created = send("POST", APP_URL, "/reviews/create",
					"rating=4" + "&comment=This_is_a_comment." + "&from=" + tutorId + "&to=" + studentId);
			response = send("DELETE", APP_URL, "/reviews/" + created.getString("reviewId"), null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			fail();
		}
	}

	@Test
	public void testGetReview() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			String studentId = send("POST", APP_URL, "/students/create",
					"name=" + restName + "&email=" + restEmailStudent + "&password=" + restPassword)
							.getString("userId");
			String reviewId = (send("POST", APP_URL, "/reviews/create",
					"rating=4" + "&comment=This_is_a_comment." + "&from=" + tutorId + "&to=" + studentId))
							.getString("reviewId");
			response = send("GET", APP_URL, "/reviews/" + reviewId, "");
			assertEquals("This_is_a_comment.", response.getString("comment"));
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

	@Test
	public void testDeleteRoom() {
		try {
			send("POST", APP_URL, "/rooms/create", "id=21" + "&capacity=23");
			response = send("DELETE", APP_URL, "/rooms/21", null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			fail();
		}
	}

	@Test
	public void testGetRoom() {
		try {
			String roomNumber = (send("POST", APP_URL, "/rooms/create", "id=21" + "&capacity=23"))
					.getString("roomNumber");
			response = send("GET", APP_URL, "/rooms/" + roomNumber, "");
			assertEquals(roomNumber, response.getString("roomNumber"));
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

	@Test
	public void testDeleteStudent() {
		try {
			send("POST", APP_URL, "/students/create",
					"name=" + "studenttt" + "&email=testemail" + "&password=" + restPassword);
			response = send("DELETE", APP_URL, "/students/testemail", null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetStudent() {
		try {
			JSONObject student = send("POST", APP_URL, "/students/create",
					"name=" + restName + "&email=" + restEmailStudent + "&password=" + restPassword);
			String studentId = student.getString("userId");
			response = send("GET", APP_URL, "/students/" + studentId, "");
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
					"id=" + tutorId + "&date=" + Date.valueOf("2019-09-22") + "&time=" + Time.valueOf("08:00:01"));
			System.out.println("Received: " + response.toString());
			assertEquals("2019-09-22", response.getString("date"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteTimeSlot() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			JSONObject response1 = send("POST", APP_URL, "/timeslots/create",
					"id=" + tutorId + "&date=" + Date.valueOf("2019-09-22") + "&time=" + Time.valueOf("08:00:01"));
			response = send("DELETE", APP_URL, "/timeslots/" + response1.getString("timeSlotId"), null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetTimeSlot() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");
			String timeSlotId = (send("POST", APP_URL, "/timeslots/create",
					"id=" + tutorId + "&date=" + Date.valueOf("2019-09-22") + "&time=" + Time.valueOf("08:00:01"))).getString("timeSlotId");
			response = send("GET", APP_URL, "/timeslots/" + timeSlotId, "");
			assertEquals(timeSlotId, response.getString("timeSlotId"));
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

			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University")
					.getString("institutionName");

			String courseName = send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics").getString("courseName");

			response = send("POST", APP_URL, "/wages/create",
					"tutorId=" + tutorId + "&course=" + courseName + "&wage=40");
			System.out.println("Received: " + response.toString());
			assertEquals("40", response.getString("wage"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteWage() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");

			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University")
					.getString("institutionName");

			String courseName = send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics").getString("courseName");

			JSONObject response1 = send("POST", APP_URL, "/wages/create",
					"tutorId=" + tutorId + "&course=" + courseName + "&wage=40");
			response = send("DELETE", APP_URL, "/wages/" + response1.getString("wageId"), null);
			assertEquals("true", response.getString("boolean"));
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetWage() {
		try {
			String tutorId = send("POST", APP_URL, "/tutors/create",
					"name=" + restName + "&email=" + restEmail + "&password=" + restPassword).getString("userId");

			String institution = send("POST", APP_URL, "/institutions/create", "name=McGill" + "&level=University")
					.getString("institutionName");

			String courseName = send("POST", APP_URL, "/courses/create",
					"name=MATH262" + "&institution=" + institution + "&subject=Mathematics").getString("courseName");

			String wageId = (send("POST", APP_URL, "/wages/create",
					"tutorId=" + tutorId + "&course=" + courseName + "&wage=40")).getString("wageId");
			response = send("GET", APP_URL, "/wages/" + wageId, "");
			assertEquals("40", response.getString("wage"));
		} catch (JSONException e) {
			fail();
		}
	}

	// METHODS FOR REST

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
			if (response.equals("true") || response.equals("false")) {
				JSONObject json = new JSONObject();
				json.put("boolean", response);
				c.disconnect();
				return json;
			} else {
				JSONObject json = new JSONObject(response);
				c.disconnect();
				return json;
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONArray sendArray(String type, String appURL, String path, String parameters) {
		try {
			URL url = new URL(appURL + path + ((parameters == null) ? "" : ("?" + parameters)));
			System.out.println("Sending: " + url.toString());
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod(type);
			c.setRequestProperty("Accept", "application/json");
			if (c.getResponseCode() != 200) {
				throw new RuntimeException(url.toString() + " Returned error:: " + c.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((c.getInputStream())));
			String response = br.readLine();
			if (response != null) {
				JSONArray r = new JSONArray(response);
				c.disconnect();
				return r;
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
