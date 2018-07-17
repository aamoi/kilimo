package com.shamba.amoi.shambaapp.adapters.projects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingPhasesFragment.OnListFragmentInteractionListener;
import com.shamba.amoi.shambaapp.fragments.projects.TaskListFragment;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PlantingPhasesRecyclerViewAdapter extends
        RecyclerView.Adapter<PlantingPhasesRecyclerViewAdapter.ViewHolder> {


    private static final String ARG_program_id = "key_program_id";
    private static final String ARG_program_name = "key_program_name";
    private static final String ARG_crop_name = "key_crop_name";
    private static final String ARG_phase = "key_phase";

    public int program_id;
    public  String program_name;
    public  String crop_name;
    public String phase;

    private final List<PlantingPhaseItem> phases;
    private final OnListFragmentInteractionListener mListener;
    private final HomeActivity homeActivity;

    public PlantingPhasesRecyclerViewAdapter(HomeActivity homeActivity, List<PlantingPhaseItem> items,
                                             OnListFragmentInteractionListener listener) {
        phases = items;
        mListener = listener;
        this.homeActivity=homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plantingphases, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = phases.get(position);
        holder.mIdView.setText(phases.get(position).getPhase_name());
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
        return phases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public PlantingPhaseItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.phase_name);
            mContentView = (TextView) view.findViewById(R.id.phase_stage);

            DialogUtility dialogUtility= new DialogUtility(homeActivity,"Planting phase Action?",
                    "Tasks","Details", ""){
                @Override
                public void onSelectNegativeDialogueOption(){

                    PlantingPhaseItem.selectedplantingPhaseItem=mItem;

                    BaseFragment.plantingPhaseItem=mItem;
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new TaskListFragment());
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(mView,mIdView,mContentView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
