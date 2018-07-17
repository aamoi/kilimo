package com.shamba.amoi.shambaapp.adapters.labor;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment.
        OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TaskAssignmentListRecyclerViewAdapter
        extends RecyclerView.Adapter<TaskAssignmentListRecyclerViewAdapter.ViewHolder> {

    private final List<TaskAssignmentItem> mValues;
    private final HomeActivity homeActivity;

    public TaskAssignmentListRecyclerViewAdapter(HomeActivity homeActivity,
                                                 List<TaskAssignmentItem> items)
                                                   {
        this.homeActivity=homeActivity;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_taskassignmentlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.task_identity.setText(mValues.get(position).getTask_id());
        holder.task_date.setText(mValues.get(position).getAssignment_start_date());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                }
            }
        });
    }

    @Override
    public int getItemCount() {

        Log.d("Projects|", "number of task assignments is "+mValues.size());

        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView task_identity;
        public final TextView task_date;
        public TaskAssignmentItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            task_identity = (TextView) view.findViewById(R.id.txt_first_column);
            task_date = (TextView) view.findViewById(R.id.txt_second_column);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + task_identity.getText() + "'";
        }
    }
}
