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

public class ViewEmployersActivity extends AppCompatActivity {

    private static final String TAG = "ViewEmployersActivity";
    private String error = null;

    // variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mPositions = new ArrayList<>();
    private ArrayList<String> mCompanies = new ArrayList<>();
    private ArrayList<String> mEmails = new ArrayList<>();
    private ArrayList<String> mPhones = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employers);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initEmployerNames();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initEmployerNames() {
        Log.d(TAG, "initEmployerNames: preparing employer names.");

        // Restfull call: all employers
        HttpUtils.get("employers/", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                // Clear lists
                mNames.clear();
                mPositions.clear();
                mCompanies.clear();
                mEmails.clear();
                mPhones.clear();
                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call succesfull (" + i + ").");

                        // Add Employer Names
                        mNames.add(response.getJSONObject(i).getString("firstName") + " "
                                + response.getJSONObject(i).getString("lastName"));

                        // Add Employer IDs
                        mPositions.add(response.getJSONObject(i).getString("position"));

                        // Add Employer Majors & Years
                        mCompanies.add(response.getJSONObject(i).getString("company") + ", "
                                + response.getJSONObject(i).getString("location"));

                        // Add Employer email
                        mEmails.add(response.getJSONObject(i).getString("email"));

                        // Add Employer phone number
                        mPhones.add(response.getJSONObject(i).getString("phone"));

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
        RecyclerView recyclerView = findViewById(R.id.employers_recycler_view);
        EmployersAdapter adapter = new EmployersAdapter(this, mNames, mPositions, mCompanies, mEmails, mPhones);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
