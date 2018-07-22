package com.shamba.amoi.shambaapp.fragments.projects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.projects.PlantingPhasesRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Phase;
import com.shamba.amoi.shambaapp.db.projects.PhaseDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhase;
import com.shamba.amoi.shambaapp.db.projects.PlantingPhaseDao;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.shamba.amoi.shambaapp.models.product.ProductItem.getProductItemByID;
import static com.shamba.amoi.shambaapp.models.product.ProductItem.staticProductItemList;
import static com.shamba.amoi.shambaapp.models.projects.PhaseItem.staticPhaseItems;
import static com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem.selectedPlantingProgram;

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
    public String planting_name;
    public String produce_name;
    public String start_date;
    static PlantingProgramItem plantingProgramItem;
    static List<PhaseItem> phaseItems;

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

        plantingProgramItem=selectedPlantingProgram;

        String title= getString(R.string.title_fragment_planting_phases)+":\n"+
               selectedPlantingProgram.getPlanting_name();

        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_plantingphases_list, container,
                false);

     produce_name=getProductItemByID(staticProductItemList,plantingProgramItem.
                getProduct_id()).getProduct_name();

        //setup profile details
        TextView txt_crop_name=(TextView)view.findViewById(R.id.product_name);
        txt_crop_name.setText(produce_name);

        TextView txt_program_name=(TextView)view.findViewById(R.id.planting_name);
        txt_program_name.setText(plantingProgramItem.getPlanting_name());

        TextView txt_start_date=(TextView)view.findViewById(R.id.planting_ref);
        txt_start_date.setText(plantingProgramItem.getPlanned_preparation_date());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_phases);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        phaseItems=PhaseItem.staticPhaseItems;
        recyclerView.setAdapter(new PlantingPhasesRecyclerViewAdapter((HomeActivity) this.getActivity(),
                phaseItems, mListener));

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
        void onListFragmentInteraction(PhaseItem mItem);
    }

//    class GetPhasesList extends AsyncTask<Void, Void, List<PhaseItem>> {
//
//        PhaseDao phaseDao;
//        List<PhaseItem> phaseItemList;
//
//        @Override
//        protected void onPreExecute() {
//            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
//            phaseDao=db.phaseDao();
//            phaseItemList=new ArrayList();
//        }
//
//        @Override
//        protected List<PhaseItem> doInBackground(Void... voids) {
//
//            try{
//
//            List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
//                    BuildConfig.SERVER_URL,"phase/","");
//
//            Log.d("phases",response.get(0).getString("phases"));
//
//            JSONArray jArray = new JSONArray(response);
//
//            for(int i=0;i<jArray.length();++i){
//
//                PhaseItem phaseItem=new PhaseItem();
//
//                JSONObject jsonObject = jArray.getJSONObject(i);
//
//                int id=jsonObject.getInt("id");
//                phaseItem.setId(id);
//
//                String phase_name=jsonObject.getString("phase_name");
//                phaseItem.setPhase_name(phase_name);
//
//                String phase_details=jsonObject.getString("phase_details");
//                phaseItem.setPhase_details(phase_details);
//
//                boolean is_planting_phase=jsonObject.getBoolean("is_planting_phase");
//                phaseItem.setIs_planting_phase(is_planting_phase);
//
//                boolean is_poultry_phase=jsonObject.getBoolean("is_poultry_phase");
//                phaseItem.setIs_poultry_phase(is_poultry_phase);
//
//                boolean is_fishing_phase=jsonObject.getBoolean("is_fishing_phase");
//                phaseItem.setIs_fishing_phase(is_fishing_phase);
//
//                boolean is_dairy_phase=jsonObject.getBoolean("is_dairy_phase");
//                phaseItem.setIs_dairy_phase(is_dairy_phase);
//
//                Log.d("Phase name @ "+i, phase_name);
//
//                phaseItemList.add(phaseItem);
//            }
//            staticPhaseItems=phaseItemList;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//            return phaseItemList;
//        }
//
//        @Override
//        protected void onPostExecute(List<PhaseItem> phaseItemList) {
////            super.onPostExecute(masterPlantingPlanItems);
//        }
//    }
}
