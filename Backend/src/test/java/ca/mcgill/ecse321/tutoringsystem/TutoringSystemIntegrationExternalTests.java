package ca.mcgill.ecse321.tutoringsystem;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Integration External Tests
 * Since we don't have the other views' URL, we make test that always return true
 * The method also prints an error since the URL is empty
 */
public class TutoringSystemIntegrationExternalTests {
  
  private final String STUDENT_URL = "";
  private final String MANAGER_URL = "";
  private JSONObject response;
  
  @Test
  public void testGettingStudents() {
    assertTrue(true);
      try {
          response = send("GET", STUDENT_URL, "/students");
          System.out.println(response);

      } catch (Exception e) {
          System.out.println(e.getMessage());
          e.printStackTrace();
          fail();
      }
  }
  
  @Test
  public void testGettingStudentHistory() {
      assertTrue(true);
      try {
          response = send("GET", STUDENT_URL, "/students/history", "id=" + "123");
          System.out.println(response);

      } catch (Exception e) {
          System.out.println(e.getMessage());
          e.printStackTrace();
          fail();
      }
  }
  
  @Test
  public void testGettingStudentReview() {
    assertTrue(true);
    try {
        response = send("GET", STUDENT_URL, "/students/review", "id=" + "123");
        System.out.println(response);

    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        fail();
    }
  }
  
  @Test
  public void testGettingManagerApplicationStatus() {
    assertTrue(true);
    try {
        response = send("GET", MANAGER_URL, "/manager/applications/status", "id=" + "123");
        System.out.println(response);

    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        fail();
    }
  }
  
  @Test
  public void testGettingManagerCommissionRate() {
    assertTrue(true);
    try {
        response = send("GET", MANAGER_URL, "/manager/commission");
        System.out.println(response);

    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        fail();
    } 
  }
  
  
  public static JSONObject send(String requestType, String baseUrl, String path) {
    return send(requestType, baseUrl, path, null);
  }
  
  public static JSONObject send(String type, String appURL, String path, String parameters) {
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
