package ca.mcgill.ecse321.cooperator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewCoopsActivity extends AppCompatActivity {

    private static final String TAG = "ViewCoopsActivity";
    private String error = null;

    // variables
    private ArrayList<String> mIDs = new ArrayList<>();
    private ArrayList<String> mSemesters = new ArrayList<>();
    private ArrayList<String> mStartDates = new ArrayList<>();
    private ArrayList<String> mEndDates = new ArrayList<>();
    private ArrayList<String> mJobDescriptions = new ArrayList<>();
    private String userId = "";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coops);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userId = getIntent().getStringExtra("userId");
        initCoopNames();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initCoopNames() {
        Log.d(TAG, "initCoopNames: preparing coop IDs. userId: " + getIntent().getStringExtra("userId"));

        // Restful call: all coops
        HttpUtils.get("coops/" + getIntent().getStringExtra("userId") + "/", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                // Clear lists
                mIDs.clear();
                mSemesters.clear();
                mStartDates.clear();
                mEndDates.clear();
                mJobDescriptions.clear();
                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call succesfull (" + i + ").");

                        // Add Coop ID
                        mIDs.add("Coop #" + response.getJSONObject(i).getString("coopId"));

                        // Add Coop Semester
                        mSemesters.add(response.getJSONObject(i).getString("semester"));

                        // Add Coop Start Date
                        mStartDates.add("Start Date: " + response.getJSONObject(i).getString("startDate"));

                        // Add Coop End Date
                        mEndDates.add("End Date: " + response.getJSONObject(i).getString("endDate"));

                        // Add Coop Description
                        mJobDescriptions.add(response.getJSONObject(i).getString("jobDescription") + " at "
                                + response.getJSONObject(i).getJSONObject("employer").getString("company")
                                + ", " + response.getJSONObject(i).getString("location"));

                        initRecyclerView();
                    } catch (JSONException e) {
                        Log.d(TAG, e.getMessage());
                        //error += e.getMessage();
                    }
                    //refreshErrorMessage();
                }
                //adapter.notifyDataSetChanged();
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
        RecyclerView recyclerView = findViewById(R.id.coops_recycler_view);
        CoopsAdapter adapter = new CoopsAdapter(this, mIDs, mSemesters, mStartDates, mEndDates, mJobDescriptions);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

