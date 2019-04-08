package ca.mcgill.ecse321.cooperator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CoopsAdapter extends RecyclerView.Adapter<CoopsAdapter.ViewHolder> {

    private static final String TAG = "CoopsAdapter";

    private ArrayList<String> mCoopIDs = new ArrayList<>();
    private ArrayList<String> mCoopSemesters = new ArrayList<>();
    private ArrayList<String> mCoopStartDates = new ArrayList<>();
    private ArrayList<String> mCoopEndDates = new ArrayList<>();
    private ArrayList<String> mCoopDescriptions = new ArrayList<>();
    private Context mContext;
    public String userId = new String();

    public CoopsAdapter(Context mContext, ArrayList<String> mCoopIDs, ArrayList<String> mCoopSemesters,
                            ArrayList<String> mCoopStartDates, ArrayList<String> mCoopEndDates, ArrayList<String> mCoopDescriptions) {
        this.mCoopIDs = mCoopIDs;
        this.mCoopSemesters = mCoopSemesters;
        this.mCoopStartDates = mCoopStartDates;
        this.mCoopEndDates = mCoopEndDates;
        this.mCoopDescriptions = mCoopDescriptions;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_coop_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG, "onBindViewHolder: called.");

        holder.coopID.setText(mCoopIDs.get(position));
        holder.coopSemester.setText(mCoopSemesters.get(position));
        holder.coopStartDate.setText(mCoopStartDates.get(position));
        holder.coopEndDate.setText(mCoopEndDates.get(position));
        holder.coopDescription.setText(mCoopDescriptions.get(position));

        // Android Toast for id of Coop
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mCoopIDs.get(position));
                //EditText editText = (EditText) findViewById(R.id.editText);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                Toast.makeText(mContext, mCoopIDs.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Return the size of your ArrayLists (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCoopIDs.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView coopID;
        public TextView coopSemester;
        public TextView coopStartDate;
        public TextView coopEndDate;
        public TextView coopDescription;
        public RelativeLayout parentLayout;

        public ViewHolder(View v) {
            super(v);
            coopID = v.findViewById(R.id.coop_id);
            coopSemester = v.findViewById(R.id.coop_semester);
            coopStartDate = v.findViewById(R.id.start_date);
            coopEndDate = v.findViewById(R.id.end_date);
            coopDescription = v.findViewById(R.id.job_description);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }
}
