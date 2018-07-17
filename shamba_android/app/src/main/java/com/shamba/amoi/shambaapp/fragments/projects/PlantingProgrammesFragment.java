package com.shamba.amoi.shambaapp.fragments.projects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.projects.PlantingProgrammesRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class PlantingProgrammesFragment extends BaseFragment {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;
    List<PlantingProgramItem> planting_programmes;
    Button add_plant_program;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlantingProgrammesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlantingProgrammesFragment newInstance(int columnCount) {
        PlantingProgrammesFragment fragment = new PlantingProgrammesFragment();
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
        getActivity().setTitle(R.string.title_fragment_planting_projects);

        View view = inflater.inflate(R.layout.fragment_plantingprogrammes_list, container,
                false);

        GetPlantingProgrammes getPlantingProgrammes=new GetPlantingProgrammes();
        try {
            planting_programmes= getPlantingProgrammes.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_planting_programmes);
        recyclerView.setAdapter(new PlantingProgrammesRecyclerViewAdapter(planting_programmes,
                (HomeActivity) this.getActivity()));

        add_plant_program=(Button)view.findViewById(R.id.btn_add_planting_program);
        add_plant_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("aaa","in programs");
                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,CreatePlantingProgrammeFragment.
                                newInstance("new",""));
            }
        });

        return view;
    }

    public String[][] getDummyPlantingPrograms(){

        String[][] programmes={
                {"1", "Mishiri Jan 18","FB_01_18","mRef 1","French Beans","Jan 2018","Kangemi", "BLK A","0.4"},
                {"2", "Onions Dec 17","Onion_01_18","mRef 1","Onions","Dec 2017","Kangemi", "BLK B","0.4"},
                {"3", "Managu Jan 18","Managu_01_18","mRef 1","Managu","Jan 2018","Kangemi", "BLK C","0.23"},

        };

        return programmes;
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
//        mListener = null;
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
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(PlantingProgramItem item);
//    }

    class GetPlantingProgrammes extends AsyncTask<Void, Void, List<PlantingProgramItem>> {

        PlantingProgramDao plantingProgramDao;
        List<PlantingProgramItem> plantingProgramItems;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            plantingProgramDao=db.plantingProgramDao();
            plantingProgramItems=new ArrayList();
        }

        @Override
        protected List<PlantingProgramItem> doInBackground(Void... voids) {

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"planting/","");

                Log.d("plantings",response.get(0).getString("planting_name"));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){

                    JSONObject jsonObject = jArray.getJSONObject(i);

                    String name=jsonObject.getString("planting_name");

//                    JsonObject jsonObject = jArray.get(i).getAsJsonObject();

                    Log.d("Planting name @ "+i, name);

                    PlantingProgramItem plantingProgramItem = new PlantingProgramItem();
                    plantingProgramItem.setPlan_id(String.valueOf(jsonObject.get("id")));
                    plantingProgramItem.setPlanting_block(String.valueOf(jsonObject.get("block_id")));
                    plantingProgramItem.setPlanting_location(String.valueOf(jsonObject.get("location_id")));
                    plantingProgramItem.setPlanting_name(String.valueOf(jsonObject.get("planting_name")));
                    plantingProgramItem.setPlanting_produce(String.valueOf(jsonObject.get("product_id")));
//                    plantingProgramItem.setPreparation_date(db_planting_programs.get(count).getPreparation_date());
//                    plantingProgramItem.setSeedbed_date(db_planting_programs.get(count).getSeedbed_date());
//                    plantingProgramItem.setTransplanting_date(db_planting_programs.get(count).getTransplanting_date());
//                    plantingProgramItem.setHarvesting_date(db_planting_programs.get(count).getHarvesting_date());
//                    plantingProgramItem.setSales_date(db_planting_programs.get(count).getSales_date());
//                    plantingProgramItem.setSeed_quantity(db_planting_programs.get(count).getSeed_quantity());
//                    plantingProgramItem.setPlanting_cost(db_planting_programs.get(count).getPlanting_cost());
//                    plantingProgramItem.setPlanting_revenue(db_planting_programs.get(count).getPlanting_revenue());

                    plantingProgramItems.add(plantingProgramItem);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            List<PlantingProgram> db_planting_programs = plantingProgramDao.getAllPlantingPrograms();
//
//            if (db_planting_programs.size() > 0){
//
//                for (int count = 0; count < db_planting_programs.size(); ++count) {
//                    PlantingProgramItem plantingProgramItem = new PlantingProgramItem();
//                    plantingProgramItem.setPlan_id(db_planting_programs.get(count).getPlan_id());
//                    plantingProgramItem.setPlanting_block(db_planting_programs.get(count).getPlanting_block());
//                    plantingProgramItem.setPlanting_location(db_planting_programs.get(count).getPlanting_location());
//                    plantingProgramItem.setPlanting_name(db_planting_programs.get(count).getPlanting_name());
//                    plantingProgramItem.setPlanting_produce(db_planting_programs.get(count).getPlanting_produce());
//                    plantingProgramItem.setPreparation_date(db_planting_programs.get(count).getPreparation_date());
//                    plantingProgramItem.setSeedbed_date(db_planting_programs.get(count).getSeedbed_date());
//                    plantingProgramItem.setTransplanting_date(db_planting_programs.get(count).getTransplanting_date());
//                    plantingProgramItem.setHarvesting_date(db_planting_programs.get(count).getHarvesting_date());
//                    plantingProgramItem.setSales_date(db_planting_programs.get(count).getSales_date());
//                    plantingProgramItem.setSeed_quantity(db_planting_programs.get(count).getSeed_quantity());
//                    plantingProgramItem.setPlanting_cost(db_planting_programs.get(count).getPlanting_cost());
//                    plantingProgramItem.setPlanting_revenue(db_planting_programs.get(count).getPlanting_revenue());
//
//                    plantingProgramItems.add(plantingProgramItem);
//                }
//            }

            return plantingProgramItems;
        }

        @Override
        protected void onPostExecute(List<PlantingProgramItem> Plantings) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
