package ca.mcgill.ecse321.tutoringsystem;

import android.os.Bundle;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String error = null;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_schedule, R.id.nav_notification, R.id.nav_reviews,
                R.id.nav_wages, R.id.nav_settings, R.id.nav_welcome)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // HIDES THE OPTIONS BEFORE LOGGING IN
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_schedule).setVisible(false);
        nav_Menu.findItem(R.id.nav_notification).setVisible(false);
        nav_Menu.findItem(R.id.nav_reviews).setVisible(false);
        nav_Menu.findItem(R.id.nav_wages).setVisible(false);
        nav_Menu.findItem(R.id.nav_settings).setVisible(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
     * WELCOME PAGE METHODS
     */

    /**
     * Allows the tutor to Log in to the app, sets the name and id on the side bar.
     * @param v
     */
    public void login(final View v) {
        error = "";
        // Get text fields
        final TextView tv = (TextView) findViewById(R.id.text_welcome);
        final EditText email = (EditText) findViewById(R.id.login_email);
        final EditText password = (EditText) findViewById(R.id.login_password);
        final TextView tutorName = (TextView) findViewById(R.id.login_name);
        final TextView tutorId = (TextView) findViewById(R.id.login_id);

        // Construct request parameter
        RequestParams params = new RequestParams();
        params.put("email", email.getText());
        params.put("password", password.getText());

        // Send login post request
        HttpUtils.post("login/", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    // Updates the side bar name and id
                    String name = response.getString("name");
                    int userId = response.getInt("userId");
                    tutorName.setText(name);
                    tutorId.setText(userId + "");

                    // Hide Login and unhide the rest of the nav options
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    Menu nav_Menu = navigationView.getMenu();
                    nav_Menu.findItem(R.id.nav_welcome).setVisible(false);
                    nav_Menu.findItem(R.id.nav_schedule).setVisible(true);
                    nav_Menu.findItem(R.id.nav_notification).setVisible(true);
                    nav_Menu.findItem(R.id.nav_reviews).setVisible(true);
                    nav_Menu.findItem(R.id.nav_wages).setVisible(true);
                    nav_Menu.findItem(R.id.nav_settings).setVisible(true);
                    Navigation.findNavController(v).navigate(R.id.nav_schedule);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                error += "Invalid Email or Password";
                tv.setText(error);
            }
        });
    }

    public void getSchedule(View v) {
        error = "";
        final TextView tv1 = (TextView) findViewById(R.id.uhh);
        final TextView tv2 = (TextView) findViewById(R.id.pp);
        final TextView tutorIdField = (TextView) findViewById(R.id.login_id);
        final int tutorId = Integer.parseInt(tutorIdField.getText().toString());

        HttpUtils.get("requests/tutor/" + tutorId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                String requestDisplay = "";
                String pendingDisplay = "";
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject request = response.getJSONObject(i);
                        JSONObject student = request.getJSONObject("student");
                        JSONObject course = request.getJSONObject("course");
                        String studentName = student.getString("name");
                        String date = request.getString("date");
                        String time = request.getString("time");
                        String courseName = course.getString("courseName");

                        try {
                            JSONObject room = request.getJSONObject("room");
                            String roomNumber = room.getString("roomNumber");
                            requestDisplay += "" + date + " at " + time + " at " + roomNumber + " with " + studentName + " for " + courseName + "\n";
                        } catch (JSONException e) {
                            pendingDisplay += "" + date + " at " + time + " with " + studentName + " for " + courseName + "\n";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                tv1.setText(requestDisplay);
                tv2.setText(pendingDisplay);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                tv1.setText(error);
            }
        });
    }

    /*
     * BROWSE WAGE PAGE METHODS
     */

    /**
     * Gets the list of all institutions and updates the dropdown menu (spinner) options.
     * @param v
     */
    public void getInstitutions(View v) {
        error = "";

        // Get elements
        final TextView tv = (TextView) findViewById(R.id.text_wages);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_institution);

        // Send get request
        HttpUtils.get("institutions/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Iterate through every institution, adding its name to an array
                ArrayList<String> institutions = new ArrayList<>();
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject institution = response.getJSONObject(i);
                        institutions.add(institution.getString("institutionName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Updates the institution dropdown menu
                spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, institutions));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                tv.setText(error);
            }
        });
    }

    /**
     * Gets the list of courses from a chosen institution.
     * @param v
     */
    public void getCourses(View v) {
        error = "";

        // Get elements
        final TextView tv = (TextView) findViewById(R.id.text_wages);
        final Spinner spinner_ins = (Spinner) findViewById(R.id.spinner_institution);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_courses);

        // Send get request
        HttpUtils.get("courses/institution/" + spinner_ins.getSelectedItem().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Iterate through every course, adding its name to an array
                ArrayList<String> courses = new ArrayList<>();
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject course = response.getJSONObject(i);
                        courses.add(course.getString("courseName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Updates the course dropdown menu
                spinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, courses));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                tv.setText(error);
            }
        });
    }

    /**
     * Gets the list of wages from a chosen institution/course
     * @param v
     */
    public void getWages(View v) {
        error = "";

        // Get elements
        final TextView tv = (TextView) findViewById(R.id.text_wages);
        final Spinner spinner_crs = (Spinner) findViewById(R.id.spinner_courses);
        final TextView wages_amounts = (TextView) findViewById(R.id.wages_amounts);

        // Send get request
        HttpUtils.get("wages/course/" + spinner_crs.getSelectedItem().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Iterate through all the wages, adding its wage & tutor name to the string
                String amounts = "";
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject wage = response.getJSONObject(i);
                        String wageCents = wage.getString("wage");
                        String name = wage.getString("tutorName");
                        double wageAmount = Double.parseDouble(wageCents) / 100;
                        amounts += name + ": " + wageAmount + "\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Display the constructed string
                wages_amounts.setText(amounts);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                tv.setText(error);
            }
        });
    }


    /*
     * SETTINGS PAGE METHODS
     */

    /**
     * Gets the list of the logged in tutor's wages
     * @param v
     */
    public void getTutorWages(View v) {
        error = "";

        // Get elements
        final TextView tv = (TextView) findViewById(R.id.text_settings);
        final TextView wages_amounts = (TextView) findViewById(R.id.tutors_wages);
        final TextView tutorIdField = (TextView) findViewById(R.id.login_id);
        final int tutorId = Integer.parseInt(tutorIdField.getText().toString());

        // Send get request
        HttpUtils.get("wages/tutor/" + tutorId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Iterate through all the wages, adding its wage & course name to the string
                String amounts = "";
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject wage = response.getJSONObject(i);
                        String wageCents = wage.getString("wage");
                        String courseName = wage.getString("courseName");
                        double wageAmount = Double.parseDouble(wageCents) / 100;
                        amounts += courseName + ": " + wageAmount + "\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Display the constructed string
                wages_amounts.setText(amounts);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                tv.setText(error);
            }
        });
    }

    /**
     * Gets the list of the logged in tutor's timeslots
     * @param v
     */
    public void getTutorTimeslots(View v) {
        error = "";

        // Get elements
        final TextView tv = (TextView) findViewById(R.id.text_settings);
        final TextView tutors_timeslots = (TextView) findViewById(R.id.tutors_timeslots);
        final TextView tutorIdField = (TextView) findViewById(R.id.login_id);
        final int tutorId = Integer.parseInt(tutorIdField.getText().toString());

        // Send get request
        HttpUtils.get("timeslots/tutor/" + tutorId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Iterate through all the timeslots, adding its date & time to the string
                String timeslots = "";
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject slot = response.getJSONObject(i);
                        String date = slot.getString("date");
                        String time = slot.getString("time");
                        timeslots += date + " at " + time + "\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Display the constructed string
                tutors_timeslots.setText(timeslots);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                tv.setText(error);
            }
        });
    }
}
