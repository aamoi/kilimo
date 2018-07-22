package com.shamba.amoi.shambaapp.adapters.projects;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.fragments.projects.CreatePlantingProgrammeFragment;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingPhasesFragment;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DialogUtility;
import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class PlantingProgrammesRecyclerViewAdapter
        extends RecyclerView.Adapter<PlantingProgrammesRecyclerViewAdapter.ViewHolder> {

    private static final String ARG_program_id = "key_program_id";
    private static final String ARG_program_name = "key_program_name";
    private static final String ARG_crop_name = "key_crop_name";
    private static final String ARG_start_date = "key_start_date";
    private static final String ARG_plant_program = "key_plant_program";

    private final List<PlantingProgramItem> plantingProgramItems;
//    private final OnListFragmentInteractionListener mListener;
    private final HomeActivity homeActivity;

    public PlantingProgrammesRecyclerViewAdapter(List<PlantingProgramItem> items,
//                                                 OnListFragmentInteractionListener listener,
                                                 HomeActivity homeActivity) {
        plantingProgramItems = items;
//        mListener = listener;
        this.homeActivity =homeActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plantingprogrammes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.pItem = plantingProgramItems.get(position);
        holder.program_name.setText(plantingProgramItems.get(position).getPlanting_name());
        holder.program_ref.setText(plantingProgramItems.get(position).getPlanned_preparation_date());
    }

    @Override
    public int getItemCount() {
        return plantingProgramItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView program_name;
        public final TextView program_ref;
        public PlantingProgramItem pItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            program_name = (TextView) view.findViewById(R.id.txt_first_column);
            program_ref = (TextView) view.findViewById(R.id.txt_second_column);

            DialogUtility dialogUtility= new DialogUtility(homeActivity,"Planting project action?",
                    "Tasks","Details",""){
                @Override
                public void onSelectNegativeDialogueOption(){
                    PlantingProgramItem.selectedPlantingProgram=pItem;

//                    Log.d("program_id", String.valueOf(pItem.getPlan_id()));
                    Log.d("program_name", String.valueOf(pItem.getPlanting_name()));
                    
                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            new PlantingPhasesFragment());
                }
                @Override
                public void onSelectPostiveDialogueOption(){
                    PlantingProgramItem.selectedPlantingProgram=pItem;
                    BaseFragment.plantingProgramItem=pItem;

                    BaseFragment.changeFragment(homeActivity,R.id.fragment_placeholder_home,
                            CreatePlantingProgrammeFragment.newInstance("view",""));
                }
            };

            dialogUtility.setSimpleDialogOnRecyclerListItem(mView,program_name,program_ref);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + program_name.getText() + "'";
        }
    }
}
