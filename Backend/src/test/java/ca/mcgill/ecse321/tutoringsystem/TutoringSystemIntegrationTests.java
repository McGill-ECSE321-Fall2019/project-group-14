package ca.mcgill.ecse321.tutoringsystem;

import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.FixMethodOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
		String error = "";
		try {
			JSONObject tutor = send("POST", APP_URL, "/tutors/create",
					"name=" + "MEHDITUTOR" + "&email=" + restEmail + "&password=" + restPassword);
			String tutorId = tutor.getString("userId");
			response = send("DELETE", APP_URL, "/tutors/" + tutorId, "");
		} catch (JSONException | IllegalArgumentException e) {
			error = e.getMessage();
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
			JSONArray applications = sendRequestArray("GET", APP_URL, "/applications/" + applicationId, "");
			for (int i = 0; i < applications.length(); i++) {
				JSONObject o = applications.getJSONObject(i);
				assertEquals(restName ,o.getString("name"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testDeleteApplication() {
		
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
		
	}
	
	@Test
	public void testDeleteCourse() {
		
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
		
	}

	@Test
	public void testGetInstitution() {
		
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
		
	}
	
	@Test
	public void testGetManager() {
		
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
	
	@Test
	public void testGetNotification() {
		
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
		
	}
	
	@Test
	public void testGetReview() {
		
	}

	/*
	 * REQUEST
	 */
	
	@Test
	public void testDeleteRequest() {
		
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
		
	}
	
	@Test
	public void testGetRoom() {
		
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
		
	}
	
	@Test
	public void testGetStudent() {
		
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
		
		
	}
	
	@Test
	public void testGetTimeSlot() {
		
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
		
	}
	
	@Test
	public void testGetWage() {
		
	}
	
	@Test
	public void zClearDb() {
		send("POST", APP_URL, "/flushdb", null);
		assertTrue(true);
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

	public static JSONArray sendRequestArray(String type, String appURL, String path, String parameters) {
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
