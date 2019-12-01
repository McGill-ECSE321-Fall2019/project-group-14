package ca.mcgill.ecse321.tutoringsystem;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
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

        /* HIDES THE OPTIONS BEFORE LOGGING IN
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_home).setVisible(false);
        nav_Menu.findItem(R.id.nav_gallery).setVisible(false);
        nav_Menu.findItem(R.id.nav_slideshow).setVisible(false);
        nav_Menu.findItem(R.id.nav_tools).setVisible(false);
        nav_Menu.findItem(R.id.nav_share).setVisible(false);*/

        // initialize error message text view
        //refreshErrorMessage();

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
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }*/

    public void getInstitutions(View v) {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.text_wages);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_institution);
        HttpUtils.get("institutions/", new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<String> institutions = new ArrayList<>();
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject institution = response.getJSONObject(i);
                        institutions.add(institution.getString("institutionName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    public void getCourses(View v) {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.text_wages);
        final Spinner spinner_ins = (Spinner) findViewById(R.id.spinner_institution);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_courses);

        HttpUtils.get("courses/institution/" + spinner_ins.getSelectedItem().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<String> courses = new ArrayList<>();
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject course = response.getJSONObject(i);
                        courses.add(course.getString("courseName"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    public void getWages(View v) {
        error = "";
        final TextView tv = (TextView) findViewById(R.id.text_wages);
        final Spinner spinner_crs = (Spinner) findViewById(R.id.spinner_courses);
        final TextView wages_amounts = (TextView) findViewById(R.id.wages_amounts);

        HttpUtils.get("wages/course/" + spinner_crs.getSelectedItem().toString(), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
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
}
