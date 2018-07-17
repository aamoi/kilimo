package com.shamba.amoi.shambaapp.fragments.projects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.projects.PlantingPhasesRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhase;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhaseDao;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PlantingPhasesFragment extends BaseFragment {
    private static final String ARG_program_id = "key_program_id";
    private static final String ARG_program_name = "key_program_name";
    private static final String ARG_crop_name = "key_crop_name";
    private static final String ARG_start_date = "key_start_date";
    public int program_id;
    public String program_name;
    public String crop_name;
    public String start_date;
    static PlantingProgramItem plantingProgramItem;
    static List<PlantingPhaseItem> plantingPhaseItemList;
    private PlantingPhasesFragment.OnListFragmentInteractionListener mListener;

    public PlantingPhasesFragment() {
        // Required empty public constructor
    }

    public static PlantingPhasesFragment newInstance(int program_id, String program_name,
                                                     String crop_name, String start_date) {
        PlantingPhasesFragment fragment = new PlantingPhasesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        String title= getString(R.string.title_fragment_planting_phases)+": "+
//                System.getProperty("line.separator") +
//        PlantingProgramItem.selectedPlantingProgram.getPlanting_name();

       String title= getString(R.string.title_fragment_planting_phases)+":\n"+
               PlantingProgramItem.selectedPlantingProgram.getPlanting_name();

        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_plantingphases_list, container,
                false);

        plantingProgramItem=BaseFragment.plantingProgramItem;

        //setup profile details
        TextView txt_crop_name=(TextView)view.findViewById(R.id.product_name);
        txt_crop_name.setText(plantingProgramItem.getPlanting_produce());

        TextView txt_program_name=(TextView)view.findViewById(R.id.planting_name);
        txt_program_name.setText(plantingProgramItem.getPlanting_name());

        TextView txt_start_date=(TextView)view.findViewById(R.id.planting_ref);
        txt_start_date.setText(plantingProgramItem.getPreparation_date());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_phases);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        plantingPhaseItemList=PlantingPhaseItem.getPlantingPhaseDBList();

        recyclerView.setAdapter(new PlantingPhasesRecyclerViewAdapter((HomeActivity) this.getActivity(),
                plantingPhaseItemList, mListener));

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
        void onListFragmentInteraction(PlantingPhaseItem mItem);
    }

    class GetPhasesList extends AsyncTask<Void, Void, List<PlantingPhaseItem>> {

        PlantingPhaseDao phaseDao;
        List<PlantingPhaseItem> phaseItemList;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            phaseDao=db.plantingPhaseDao();
            phaseItemList=new ArrayList();
        }

        @Override
        protected List<PlantingPhaseItem> doInBackground(Void... voids) {

            List<PlantingPhase> db_phases = phaseDao.getAllPlantingPhase();

            if (db_phases.size() > 0){

                for (int count = 0; count < db_phases.size(); ++count) {
                    PlantingPhaseItem phaseItem = new PlantingPhaseItem();
                    phaseItem.setPhase_id(db_phases.get(count).getPhase_id());
                    phaseItem.setPhase_name(db_phases.get(count).getPhase_name());
                    phaseItem.setStage(db_phases.get(count).getStage());
                    phaseItem.setPhase_comments(db_phases.get(count).getPhase_comments());

                    phaseItemList.add(phaseItem);
                }
            }

            return phaseItemList;
        }

        @Override
        protected void onPostExecute(List<PlantingPhaseItem> phaseItemList) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
