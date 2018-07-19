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

                    PlantingProgramItem planting=new PlantingProgramItem();

                    JSONObject jsonObject = jArray.getJSONObject(i);

                    String planned_preparation_date=jsonObject.getString("planned_preparation_date").substring(0,10);
                    planting.setPlanned_preparation_date(planned_preparation_date);

                    String planned_seedbed_date=jsonObject.getString("planned_seedbed_date");
                    planting.setPlanned_seedbed_date(planned_seedbed_date);

                    String planned_transplant_date=jsonObject.getString("planned_transplant_date");
                    planting.setPlanned_transplant_date(planned_transplant_date);

                    String planned_harvest_date=jsonObject.getString("planned_harvest_date");
                    planting.setPlanned_harvest_date(planned_harvest_date);

                    String planned_sales_date=jsonObject.getString("planned_sales_date");
                    planting.setPlanned_sales_date(planned_sales_date);

                    double estimated_cost=jsonObject.getDouble("estimated_cost");
                    planting.setEstimated_cost(estimated_cost);

                    double estimated_sales_quantity=jsonObject.getDouble("estimated_sales_quantity");
                    planting.setEstimated_sales_quantity(estimated_sales_quantity);

                    double estimated_revenue=jsonObject.getDouble("estimated_revenue");
                    planting.setEstimated_revenue(estimated_revenue);

                    double seed_quantity=jsonObject.getDouble("seed_quantity");
                    planting.setSeed_quantity(seed_quantity);

                    int location_block_id=jsonObject.getInt("location_block_id");
//                    int location_id=jsonObject.getInt("location_id");
//                    int product_id=jsonObject.getInt("product_id");

                    String planting_name=jsonObject.getString("planting_name");
                    planting.setPlanting_name(planting_name);

                    Log.d("Planting name @ "+i, planting_name);

                    String planting_details=jsonObject.getString("planting_details");
                    planting.setPlanting_details(planting_details);

                    String actual_preparation_date=jsonObject.getString("actual_preparation_date");
                    planting.setActual_preparation_date(actual_preparation_date);

                    String actual_seedbed_date=jsonObject.getString("actual_seedbed_date");
                    planting.setActual_seedbed_date(actual_seedbed_date);

                    String actual_transplant_date=jsonObject.getString("actual_transplant_date");
                    planting.setActual_transplant_date(actual_transplant_date);

                    String actual_harvest_date=jsonObject.getString("actual_harvest_date");
                    planting.setActual_harvest_date(actual_harvest_date);

                    String actual_sales_date=jsonObject.getString("actual_sales_date");
                    planting.setActual_sales_date(actual_sales_date);

                    double actual_cost=jsonObject.getDouble("actual_cost");
                    planting.setActual_cost(actual_cost);

                    double actual_sales_quantity=jsonObject.getDouble("actual_sales_quantity");
                    planting.setActual_sales_quantity(actual_sales_quantity);

                    double actual_revenue=jsonObject.getDouble("actual_revenue");
                    planting.setActual_revenue(actual_revenue);

//                    PlantingProgramItem plantingProgramItem = new PlantingProgramItem();
//                    plantingProgramItem.setPlan_id(String.valueOf(jsonObject.get("id")));
//                    plantingProgramItem.setPlanting_block(String.valueOf(jsonObject.get("block_id")));
//                    plantingProgramItem.setPlanting_location(String.valueOf(jsonObject.get("location_id")));
//                    plantingProgramItem.setPlanting_name(String.valueOf(jsonObject.get("planting_name")));
//                    plantingProgramItem.setPlanting_produce(String.valueOf(jsonObject.get("product_id")));
//
//                    plantingProgramItem.setPreparation_date(db_planting_programs.get(count).getPreparation_date());
//                    plantingProgramItem.setSeedbed_date(db_planting_programs.get(count).getSeedbed_date());
//                    plantingProgramItem.setTransplanting_date(db_planting_programs.get(count).getTransplanting_date());
//                    plantingProgramItem.setHarvesting_date(db_planting_programs.get(count).getHarvesting_date());
//                    plantingProgramItem.setSales_date(db_planting_programs.get(count).getSales_date());
//                    plantingProgramItem.setSeed_quantity(db_planting_programs.get(count).getSeed_quantity());
//                    plantingProgramItem.setPlanting_cost(db_planting_programs.get(count).getPlanting_cost());
//                    plantingProgramItem.setPlanting_revenue(db_planting_programs.get(count).getPlanting_revenue());

                    plantingProgramItems.add(planting);
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
