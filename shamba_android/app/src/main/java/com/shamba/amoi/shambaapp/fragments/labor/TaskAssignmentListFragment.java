package com.shamba.amoi.shambaapp.fragments.labor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.labor.TaskAssignmentListRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TaskAssignmentListFragment extends BaseFragment {

    HomeActivity homeActivity;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public TaskAssignmentListFragment() {
    }

    @SuppressWarnings("unused")
    public static TaskAssignmentListFragment newInstance(int columnCount) {
        TaskAssignmentListFragment fragment = new TaskAssignmentListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TaskItem.staticTaskItems = TaskItem.getAllTask(getActivity());

        String title = ResourceItem.selectedResourceItem.getResource_name() + " " +
                getString(R.string.title_fragment_resource_assignment_list);

        getActivity().setTitle(title);

        homeActivity = (HomeActivity) this.getActivity();

        View view = inflater.inflate(R.layout.fragment_taskassignmentlist_list, container,
                false);

        ResourceItem resourceItem=ResourceItem.selectedResourceItem;

        List<TaskAssignmentItem> taskAssignmentItemList = new ArrayList<>();

        taskAssignmentItemList = TaskAssignmentItem.getAllTaskAssignments(this.getActivity());
        TaskAssignmentItem.staticTaskAssignmentItem = taskAssignmentItemList;

        List<TaskAssignmentItem> resourceAssignmentItemList = TaskAssignmentItem.
                getAssignmentsByResource(getActivity(),resourceItem.getId());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_task_assignment);

        recyclerView.setAdapter(new TaskAssignmentListRecyclerViewAdapter((HomeActivity) homeActivity,
                resourceAssignmentItemList));

        Button submit_task_assignment = (Button) view.findViewById(R.id.btn_add_task_assignment);

        submit_task_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new CreateResourceAssignmentFragment());
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(TaskAssignmentItem item);
    }
}
