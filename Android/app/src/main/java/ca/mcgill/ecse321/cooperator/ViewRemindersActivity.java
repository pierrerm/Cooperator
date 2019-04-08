package ca.mcgill.ecse321.cooperator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewRemindersActivity extends AppCompatActivity {

    private static final String TAG = "ViewRemindersActivity";
    private String error = null;

    // variables
    private ArrayList<String> mSubjects = new ArrayList<>();
    private ArrayList<String> mUrgencies = new ArrayList<>();
    private ArrayList<String> mStudents = new ArrayList<>();
    private ArrayList<String> mIDs = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminders);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initReminderSubjects();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initReminderSubjects() {
        Log.d(TAG, "initReminderSubjects: preparing reminder names.");

        // Restfull call: all reminders
        HttpUtils.get("reminders/", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                // Clear lists
                mSubjects.clear();
                mUrgencies.clear();
                mStudents.clear();
                mIDs.clear();
                mDescriptions.clear();
                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call succesfull (" + i + ").");

                        // Add Reminder Subject
                        mSubjects.add(response.getJSONObject(i).getString("subject"));

                        // Add Reminder IDs
                        mUrgencies.add("URGENCY: " + response.getJSONObject(i).getString("urgency"));

                        // Add Reminder Majors & Years
                        mStudents.add(response.getJSONObject(i).getJSONObject("coop").getJSONObject("student").getString("firstName") + " "
                                + response.getJSONObject(i).getJSONObject("coop").getJSONObject("student").getString("lastName"));

                        // Add Reminder email
                        mIDs.add(response.getJSONObject(i).getJSONObject("coop").getJSONObject("student").getString("id"));

                        // Add Reminder phone number
                        mDescriptions.add(response.getJSONObject(i).getString("description"));

                        initRecyclerView();
                    } catch (JSONException e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    Log.d(TAG, "Restful GET call failure");
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //refreshErrorMessage();
            }
        });

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.reminders_recycler_view);
        RemindersAdapter adapter = new RemindersAdapter(this, mSubjects, mUrgencies, mStudents, mIDs, mDescriptions);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

