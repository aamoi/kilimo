package com.shamba.amoi.shambaapp.adapters.projects;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.projects.TaskListFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.fragments.projects.TaskSchedulingActuals;
import com.shamba.amoi.shambaapp.fragments.projects.TaskSchedulingFragment;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.
        ViewHolder> {

    private final List<TaskItem> tasks;
    private final OnListFragmentInteractionListener mListener;
    private final HomeActivity homeActivity;

    public TaskListRecyclerViewAdapter(HomeActivity homeActivity, List<TaskItem> items,
                                       OnListFragmentInteractionListener listener) {
        tasks = items;
        mListener = listener;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tasklist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = tasks.get(position);

        String complete_status= holder.mItem.getComplete_status();

        if(!(complete_status.equalsIgnoreCase("Complete"))){
            holder.mIdView.setBackgroundColor(Color.RED);
            holder.mContentView.setBackgroundColor(Color.RED);
        }

        holder.mIdView.setText(tasks.get(position).getTask_name());

        holder.mContentView.setText(tasks.get(position).getPlanned_start_date());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public TaskItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.txt_first_column);
            mContentView = (TextView) view.findViewById(R.id.txt_second_column);

            DialogUtility dialogUtility= new DialogUtility(homeActivity,
                    "Schedule Task Action?",
                    "Details","Actuals",""){
                @Override
                public void onSelectNegativeDialogueOption(){
                    TaskItem.selectedTaskItem=mItem;

                    BaseFragment.changeFragment(homeActivity, R.id.fragment_placeholder_home,
                            TaskSchedulingFragment.newInstance("view",""));
                }

                @Override
                public void onSelectPostiveDialogueOption(){
                    TaskItem.selectedTaskItem=mItem;
                    BaseFragment.changeFragment(homeActivity, R.id.fragment_placeholder_home,
                            TaskSchedulingFragment.newInstance("edit",""));
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem2(mView,mIdView,mContentView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
