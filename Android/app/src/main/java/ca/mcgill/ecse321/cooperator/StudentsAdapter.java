package ca.mcgill.ecse321.cooperator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    //private String[] mDataset;

    private static final String TAG = "StudentsAdapter";

    private ArrayList<String> mStudentNames = new ArrayList<>();
    private Context mContext;

    public StudentsAdapter(Context mContext, ArrayList<String> mStudentNames) {
        //this.mDataset = mDataset;
        this.mStudentNames = mStudentNames;
        this.mContext = mContext;
    }

//    // Provide a suitable constructor (depends on the kind of dataset)
//    public StudentsAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG, "onBindViewHolder: called.");

        holder.textView.setText(mStudentNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mStudentNames.get(position));

                Toast.makeText(mContext, mStudentNames.get(position), Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(mContext, GalleryActivity.class);
//                intent.putExtra("student_name", mStudentNames.get(position));
//                mContext.startActivity(intent);
            }
        });

        //holder.textView.setText(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mStudentNames.size();
        //return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public RelativeLayout parentLayout;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.student_name);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }
}
