package com.shamba.amoi.shambaapp.adapters.labor;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.labor.PaymentsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;
import com.shamba.amoi.shambaapp.models.assets.ServiceTypeItem;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

public class TaskAssignmentListRecyclerViewAdapter
        extends RecyclerView.Adapter<TaskAssignmentListRecyclerViewAdapter.ViewHolder> {

    private final List<TaskAssignmentItem> mValues;
    private final HomeActivity homeActivity;

    public TaskAssignmentListRecyclerViewAdapter(HomeActivity homeActivity,
                                                 List<TaskAssignmentItem> items) {
        this.homeActivity = homeActivity;
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


        if (mValues.get(position).getTask_id() > 0) {
            Log.d("Task assigs adapter|", "Hits task assignment");

            TaskItem taskItem = TaskItem.getTaskItemById(TaskItem.staticTaskItems, mValues.get(position).
                    getTask_id());

            String project_name = PlantingProgramItem.getPlantingProgramById(homeActivity,
                    taskItem.getProject_id()).getPlanting_name();
            holder.project_identity.setText(project_name);

            String task_name = taskItem.getTask_name();
            holder.task_identity.setText(task_name);
        } else {
            Log.d("Service assigs adapter|", "Hits service assignment");
            TaskItem taskItem = TaskItem.getTaskItemById(TaskItem.staticTaskItems,
                    mValues.get(position).getTask_id());

            AssetServicingItem assetServicingItem = AssetServicingItem.getAssetServicingItemById(
                    AssetServicingItem.getAllAssetServicings(homeActivity), mValues.get(position).
                            getService_id());

            String asset_name = AssetItem.getAssetItemById(AssetItem.getAllAssets(homeActivity),
                    assetServicingItem.getAsset_id()).getName();
            holder.project_identity.setText(asset_name);

            String service_name = ServiceTypeItem.getServiceTypeById(homeActivity, mValues.
                    get(position).getService_id()).getName();
            holder.task_identity.setText(service_name);
        }
        holder.task_date.setText(mValues.get(position).getAssignment_start_date().substring(0, 10));

        String complete_status = mValues.get(position).getComplete_status();

        if (!complete_status.equalsIgnoreCase("Yes")) {
            holder.project_identity.setBackgroundColor(Color.RED);
            holder.task_identity.setBackgroundColor(Color.RED);
            holder.task_date.setBackgroundColor(Color.RED);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("Projects|", "number of task assignments is " + mValues.size());
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView project_identity;
        public final TextView task_identity;
        public final TextView task_date;
        public TaskAssignmentItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            project_identity = (TextView) view.findViewById(R.id.txt_first_column);
            task_identity = (TextView) view.findViewById(R.id.txt_second_column);
            task_date = (TextView) view.findViewById(R.id.txt_third_column);

            DialogUtility dialogUtility = new DialogUtility(homeActivity,
                    "Select action on task assignment!: ",
                    "Close task", "Pay", "Details") {

                @Override
                public void onSelectNeutralDialogueOption() {
                    TaskAssignmentItem.selectedTaskAssignmentItem = mItem;
                    Log.d("Assignment| selected!", mItem.toString());
                }

                @Override
                public void onSelectNegativeDialogueOption() {
                    TaskAssignmentItem.selectedTaskAssignmentItem = mItem;
                    Log.d("Assignment| selected!", mItem.toString());
                }

                @Override
                public void onSelectPostiveDialogueOption() {
                    TaskAssignmentItem.selectedTaskAssignmentItem = mItem;
                    Log.d("Assignment| selected!", mItem.toString());
                }
            };
            dialogUtility.setSimpleDialogOnRecyclerListItem(mView,project_identity,task_identity,
                    task_date);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + task_identity.getText() + "'";
        }
    }
}
