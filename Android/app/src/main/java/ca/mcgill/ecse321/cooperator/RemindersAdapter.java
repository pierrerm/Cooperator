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

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder> {

    private static final String TAG = "RemindersAdapter";

    private ArrayList<String> mReminderSubjects = new ArrayList<>();
    private ArrayList<String> mReminderUrgencies = new ArrayList<>();
    private ArrayList<String> mReminderStudents = new ArrayList<>();
    private ArrayList<String> mReminderIDs = new ArrayList<>();
    private ArrayList<String> mReminderDescriptions = new ArrayList<>();
    private Context mContext;

    public RemindersAdapter(Context mContext, ArrayList<String> mReminderSubjects, ArrayList<String> mReminderUrgencies,
                            ArrayList<String> mReminderStudents, ArrayList<String> mReminderIDs, ArrayList<String> mReminderDescriptions) {
        this.mReminderSubjects = mReminderSubjects;
        this.mReminderUrgencies = mReminderUrgencies;
        this.mReminderStudents = mReminderStudents;
        this.mReminderIDs = mReminderIDs;
        this.mReminderDescriptions = mReminderDescriptions;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reminder_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG, "onBindViewHolder: called.");

        holder.reminderSubject.setText(mReminderSubjects.get(position));
        holder.reminderUrgency.setText(mReminderUrgencies.get(position));
        holder.reminderStudent.setText(mReminderStudents.get(position));
        holder.reminderID.setText(mReminderIDs.get(position));
        holder.reminderDescription.setText(mReminderDescriptions.get(position));

        // Android Toast for subject of the Reminder
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mReminderSubjects.get(position));

                Toast.makeText(mContext, mReminderSubjects.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mReminderSubjects.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView reminderSubject;
        public TextView reminderUrgency;
        public TextView reminderStudent;
        public TextView reminderID;
        public TextView reminderDescription;
        public RelativeLayout parentLayout;

        public ViewHolder(View v) {
            super(v);
            reminderSubject = v.findViewById(R.id.reminder_subject);
            reminderUrgency = v.findViewById(R.id.reminder_urgency);
            reminderStudent = v.findViewById(R.id.employer_name);
            reminderID = v.findViewById(R.id.student_id);
            reminderDescription = v.findViewById(R.id.reminder_description);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }
}
