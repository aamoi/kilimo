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
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskAssignmentListFragment extends BaseFragment {

    HomeActivity homeActivity;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskAssignmentListFragment() {
    }

    // TODO: Customize parameter initialization
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

        String title= HumanResourceItem.current_hr_resource.getResource_name()+" "+
                getString(R.string.title_fragment_resource_assignment_list);

        getActivity().setTitle(title);

        homeActivity=(HomeActivity) this.getActivity();

        View view = inflater.inflate(R.layout.fragment_taskassignmentlist_list, container,
                false);

        Button submit_task_assignment=(Button)view.findViewById(R.id.btn_add_task_assignment);

        submit_task_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(), R.id.fragment_placeholder_home,
                        new ResourceTaskAssignmentFragment());
            }
        });
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_task_assignment);

            recyclerView.setAdapter(new TaskAssignmentListRecyclerViewAdapter((HomeActivity)homeActivity,
                    TaskAssignmentItem.getAllTaskAssignments(this.getActivity())));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(TaskAssignmentItem item);
    }
}
