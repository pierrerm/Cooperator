package ca.mcgill.ecse321.cooperator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

public class ViewStudentsActivity extends AppCompatActivity {

    private static final String TAG = "ViewStudentsActivity";
    private String error = null;

    // variables
    private ArrayList<String> mNames = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);
        Log.d(TAG, "onCreate: started.");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initStudentNames();

//        recyclerView = (RecyclerView) findViewById(R.id.students_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new StudentsAdapter(myDataset);
//        recyclerView.setAdapter(mAdapter);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initStudentNames() {
        Log.d(TAG, "initStudentNames: preparing student names.");

        HttpUtils.get("students/", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                //mNames.clear();
                for( int i = 0; i < response.length(); i++){
                    try {
                        Log.d(TAG, "Restful GET call succesfull (" + i + ").");
                        Log.d(TAG, "First Name: " + response.getJSONObject(i).getString("firstName") );
                        mNames.add(response.getJSONObject(i).getString("firstName")
                                + " " + response.getJSONObject(i).getString("lastName"));
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
        RecyclerView recyclerView = findViewById(R.id.students_recycler_view);
        StudentsAdapter adapter = new StudentsAdapter(this, mNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
