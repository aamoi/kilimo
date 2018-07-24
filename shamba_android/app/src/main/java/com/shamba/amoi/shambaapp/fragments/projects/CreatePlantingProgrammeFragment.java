package com.shamba.amoi.shambaapp.fragments.projects;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.projects.LocationBlockItem;
import com.shamba.amoi.shambaapp.models.projects.LocationItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;
import com.shamba.amoi.shambaapp.shareResources.SpinnerUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreatePlantingProgrammeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreatePlantingProgrammeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePlantingProgrammeFragment extends BaseFragment {
    private static final String ARG_PARAM2 = "param2";
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String key_view_type = "view_type";
    static PlantingProgramItem plantingProgramItem;
    private EditText edit_planting_name;
    private Spinner  spn_planting_produce;
    private EditText edit_seed_quantity;
    private EditText edit_preparation_date;
    private EditText edit_seedbed_date;
    private EditText edit_transplanting_date;
    private EditText edit_harvesting_date;
    private EditText edit_sales_date;
    private Spinner  spn_location_block;
    private EditText edit_planting_cost;
    private EditText edit_planting_revenue;

    private Button save_program;

    int id;
    double estimated_cost;
    String planned_harvest_date;
    String planned_preparation_date;
    double estimated_revenue;
    double seed_quantity;
    String planned_seedbed_date;
    String planned_sales_date;
    String planned_transplant_date;
    int location_block_id;
    int product_id;
    String planting_name;
    String planting_details;

    double actual_cost;
    double actual_revenue;
    double estimated_sales_quantity;
    double actual_sales_quantity;
    String actual_harvest_date;
    String actual_preparation_date;
    String actual_seedbed_date;
    String actual_transplant_date;
    String actual_sales_date;

    // TODO: Rename and change types of parameters
    private String view_type;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreatePlantingProgrammeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlantingProgrammesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePlantingProgrammeFragment newInstance(String view_type, String param2) {
        CreatePlantingProgrammeFragment fragment = new CreatePlantingProgrammeFragment();
        Bundle args = new Bundle();
        args.putString(key_view_type, view_type);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            view_type = getArguments().getString(key_view_type);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.title_fragment_new_planting_project);
        // Inflate the submit_form_details for this fragment
        View view = inflater.inflate(R.layout.fragment_create_planting_programme, container,
                false);
        getViewsByIds(view);

        plantingProgramItem = PlantingProgramItem.selectedPlantingProgram;

        if (view_type.contains("view")) {
            viewPlantingProgramDetails();
        } else if (view_type.contains("new")) {

            List<String> product_names = new ArrayList<>();
            List<ProductItem> productItemList = ProductItem.staticProductItemList;
            for (int i = 0; i < productItemList.size(); ++i) {
                product_names.add(productItemList.get(i).getProduct_name());
            }
            spn_planting_produce = SpinnerUtility.setDynamicSpinner(view.getContext(),
                    spn_planting_produce, product_names);

            List<String> location_blks = new ArrayList<>();
            List<LocationBlockItem> locationBlockItems = LocationBlockItem.staticLocationBlockItemList;
            for (int i = 0; i < locationBlockItems.size(); ++i) {
                location_blks.add(locationBlockItems.get(i).getLocation_block_name());
            }
            spn_location_block = SpinnerUtility.setDynamicSpinner(view.getContext(),
                    spn_location_block, location_blks);

        } else {
        }

        save_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String produce_name = spn_planting_produce.getSelectedItem().toString();
                for (int i = 0; i < ProductItem.staticProductItemList.size(); i++) {
                    if (ProductItem.staticProductItemList.get(i).getProduct_name().
                            equalsIgnoreCase(produce_name)) {
                        product_id = ProductItem.staticProductItemList.get(i).getId();
                        break;
                    }
                }
                seed_quantity = Double.parseDouble(edit_seed_quantity.getText().toString());

                planned_preparation_date = edit_preparation_date.getText().toString();
                planned_seedbed_date = edit_seedbed_date.getText().toString();
                planned_transplant_date = edit_transplanting_date.getText().toString();
                planned_harvest_date = edit_harvesting_date.getText().toString();
                planned_sales_date = edit_sales_date.getText().toString();

                String location_block_str = spn_location_block.getSelectedItem().toString();
                for (int i = 0; i < LocationBlockItem.staticLocationBlockItemList.size(); i++) {
                    if (LocationBlockItem.staticLocationBlockItemList.get(i).getLocation_block_name().
                            equalsIgnoreCase(location_block_str)) {
                        location_block_id = LocationBlockItem.staticLocationBlockItemList.get(i).getId();
                        break;
                    }
                }

                planting_name = location_block_str + ":" + produce_name + ":" + SharedUtilities.getMonthStamp();

                estimated_cost = Double.parseDouble(edit_planting_cost.getText().toString());
                estimated_revenue = Double.parseDouble(edit_planting_revenue.getText().toString());

                SavePlantingProgram savePlantingProgram = new SavePlantingProgram(estimated_cost,
                        planned_harvest_date, planned_preparation_date,
                        estimated_revenue, seed_quantity, planned_seedbed_date, planned_sales_date,
                        planned_transplant_date, location_block_id, product_id, planting_name,
                        planting_details, actual_cost, actual_revenue, estimated_sales_quantity,
                        actual_sales_quantity, actual_harvest_date, actual_preparation_date,
                        actual_seedbed_date, actual_transplant_date, actual_sales_date);

                savePlantingProgram.execute();

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new PlantingProgrammesFragment());
            }
        });

        return view;
    }

    private void getViewsByIds(View view) {

        edit_planting_name = (EditText) view.findViewById(R.id.edit_planting_name);

        spn_planting_produce = (Spinner) view.findViewById(R.id.spn_planting_produce);
        List<String> product_names = new ArrayList<>();
        List<ProductItem> productItemList = ProductItem.staticProductItemList;
        for (int i = 0; i < productItemList.size(); ++i) {
            product_names.add(productItemList.get(i).getProduct_name());
        }
        spn_planting_produce = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_planting_produce, product_names);

        edit_seed_quantity = (EditText) view.findViewById(R.id.edit_seed_quantity);

        edit_preparation_date = (EditText) view.findViewById(R.id.edit_preparation_date);
        new DatePickerUtility(edit_preparation_date);

        edit_seedbed_date = (EditText) view.findViewById(R.id.edit_seedbed_date);
        new DatePickerUtility(edit_seedbed_date);

        edit_transplanting_date = (EditText) view.findViewById(R.id.edit_transplanting_date);
        new DatePickerUtility(edit_transplanting_date);

        edit_harvesting_date = (EditText) view.findViewById(R.id.edit_harvesting_date);
        new DatePickerUtility(edit_harvesting_date);

        edit_sales_date = (EditText) view.findViewById(R.id.edit_sales_date);
        new DatePickerUtility(edit_sales_date);

        spn_location_block = (Spinner) view.findViewById(R.id.spn_planting_location);
        List<String> location_block_names = new ArrayList<>();
        List<LocationBlockItem> locationBlockItems = LocationBlockItem.staticLocationBlockItemList;
        for (int i = 0; i < locationBlockItems.size(); ++i) {
            location_block_names.add(locationBlockItems.get(i).getLocation_block_name());
        }
        spn_location_block = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_location_block, location_block_names);

        edit_planting_cost = (EditText) view.findViewById(R.id.edit_planting_cost);
        edit_planting_revenue = (EditText) view.findViewById(R.id.edit_planting_revenue);

        save_program = (Button) view.findViewById(R.id.btn_accept);
    }

    private void viewPlantingProgramDetails() {
        edit_planting_name.setText(plantingProgramItem.getPlanting_name());
        edit_planting_name.setEnabled(false);

        spn_planting_produce.setSelection(plantingProgramItem.getProduct_id()-1);
        spn_planting_produce.setEnabled(false);

        edit_seed_quantity.setText(String.valueOf(plantingProgramItem.getSeed_quantity()));
        edit_seed_quantity.setEnabled(false);

        edit_preparation_date.setText(plantingProgramItem.getPlanned_preparation_date());
        edit_preparation_date.setEnabled(false);

        edit_seedbed_date.setText(plantingProgramItem.getPlanned_seedbed_date());
        edit_seedbed_date.setEnabled(false);

        edit_transplanting_date.setText(plantingProgramItem.getPlanned_transplant_date());
        edit_transplanting_date.setEnabled(false);

        edit_harvesting_date.setText(plantingProgramItem.getPlanned_harvest_date());
        edit_harvesting_date.setEnabled(false);

        edit_sales_date.setText(plantingProgramItem.getPlanned_sales_date());
        edit_sales_date.setEnabled(false);

        spn_location_block.setSelection(plantingProgramItem.getLocation_block_id()-1);
        spn_location_block.setEnabled(false);

        edit_planting_cost.setText(String.valueOf(plantingProgramItem.getEstimated_cost()));
        edit_planting_cost.setEnabled(false);

        edit_planting_revenue.setText(String.valueOf(plantingProgramItem.getEstimated_revenue()));
        edit_planting_revenue.setEnabled(false);

        save_program.setText("Update Details");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Saves planting record into both server and local db
     * It only saves on local db after the record is saved to server!
     */
    class SavePlantingProgram extends AsyncTask<Void, Void, Integer> {
        public int id;
        public double estimated_cost;
        public String planned_harvest_date;
        public String planned_preparation_date;
        public double estimated_revenue;
        public double seed_quantity;
        public String planned_seedbed_date;
        public String planned_sales_date;
        public String planned_transplant_date;
        public int location_block_id;
        public int product_id;
        public String planting_name;
        public String planting_details;
        public double actual_cost;
        public double actual_revenue;
        public double estimated_sales_quantity;
        public double actual_sales_quantity;
        public String actual_harvest_date;
        public String actual_preparation_date;
        public String actual_seedbed_date;
        public String actual_transplant_date;
        public String actual_sales_date;

        JSONObject request_object = new JSONObject();
        JSONObject response_object = new JSONObject();

        PlantingProgram plantingProgram;
        PlantingProgramDao plantingProgramDao;
        int planting_id;

        int success=0;

        public SavePlantingProgram(double estimated_cost, String planned_harvest_date, String planned_preparation_date,
                                   double estimated_revenue, double seed_quantity, String planned_seedbed_date, String planned_sales_date,
                                   String planned_transplant_date, int location_block_id, int product_id, String planting_name,
                                   String planting_details, double actual_cost, double actual_revenue, double estimated_sales_quantity,
                                   double actual_sales_quantity, String actual_harvest_date, String actual_preparation_date,
                                   String actual_seedbed_date, String actual_transplant_date, String actual_sales_date) {

            this.estimated_cost = estimated_cost;
            this.planned_harvest_date = planned_harvest_date;
            this.planned_preparation_date = planned_preparation_date;
            this.estimated_revenue = estimated_revenue;
            this.seed_quantity = seed_quantity;
            this.planned_seedbed_date = planned_seedbed_date;
            this.planned_sales_date = planned_sales_date;
            this.planned_transplant_date = planned_transplant_date;
            this.location_block_id = location_block_id;
            this.product_id = product_id;
            this.planting_name = planting_name;
            this.planting_details = planting_details;
            this.actual_cost = actual_cost;
            this.actual_revenue = actual_revenue;
            this.estimated_sales_quantity = estimated_sales_quantity;
            this.actual_sales_quantity = actual_sales_quantity;
            this.actual_harvest_date = actual_harvest_date;
            this.actual_preparation_date = actual_preparation_date;
            this.actual_seedbed_date = actual_seedbed_date;
            this.actual_transplant_date = actual_transplant_date;
            this.actual_sales_date = actual_sales_date;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            plantingProgramDao=db.plantingProgramDao();
            plantingProgram=new PlantingProgram();

            try {
                request_object.put("planting_name", planting_name);
                request_object.put("planting_details", planting_details);
                request_object.put("product_id", product_id);
                request_object.put("seed_quantity", seed_quantity);
                request_object.put("planned_preparation_date", planned_preparation_date);
                request_object.put("planned_seedbed_date", planned_seedbed_date);
                request_object.put("planned_transplant_date", planned_transplant_date);
                request_object.put("planned_harvest_date", planned_harvest_date);
                request_object.put("planned_sales_date", planned_sales_date);
                request_object.put("location_block_id", location_block_id);
                request_object.put("estimated_cost", estimated_cost);
                request_object.put("estimated_sales_quantity", estimated_sales_quantity);
                request_object.put("estimated_revenue", estimated_revenue);

                Log.d("Planting request...",request_object.toString());

            } catch (JSONException e) {
                Log.d("Error preparing request",e.getMessage());
                e.printStackTrace();
            }
            try{
                plantingProgram.setPlanting_name(planting_name);
                plantingProgram.setPlanting_details(planting_details);
                plantingProgram.setProduct_id(product_id);
                plantingProgram.setSeed_quantity(seed_quantity);
                plantingProgram.setPlanned_preparation_date(planned_preparation_date);
                plantingProgram.setPlanned_seedbed_date(planned_seedbed_date);
                plantingProgram.setPlanned_transplant_date(planned_transplant_date);
                plantingProgram.setPlanned_harvest_date(planned_harvest_date);
                plantingProgram.setActual_sales_date(planned_sales_date);
                plantingProgram.setLocation_block_id(location_block_id);
                plantingProgram.setEstimated_cost(estimated_cost);
                plantingProgram.setEstimated_sales_quantity(estimated_sales_quantity);
                plantingProgram.setEstimated_revenue(estimated_revenue);

                Log.d("Planting db request...",plantingProgram.toString());
            }
            catch(Exception e){
                Log.d("Error creating request",e.getMessage());
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            /**
             * Saving planting record to the server!
             */
            try {
                response_object = CommonHelper.sendPostRequestWithJsonResponse(BuildConfig.SERVER_URL,
                        "createPlanting/",request_object.toString());

                Log.d("Planting response...", response_object.toString());

                planting_id=response_object.getInt("id");

            } catch (IOException e) {
                Log.d("Error posting request",e.getMessage());
                e.printStackTrace();
            }catch(JSONException jsonException){
                Log.d("Error getting response",jsonException.getMessage());
                jsonException.printStackTrace();
            }

            /**
             * Saving planting record to local db only if the record is send to the server!
             */
            if(planting_id>0){
                try{
                    plantingProgram.setId(planting_id);
                    plantingProgramDao.insertPlantingProgram(plantingProgram);
                    Log.d("Record saved", "planting name: "+planting_name);
                    success=1;
                } catch(Exception e){
                    success=0;
                    Log.d("Error saving record",e.getMessage());
                    e.printStackTrace();
                }
            }
            return success;
        }

        @Override
        public void onPostExecute(Integer i) {
        }
    }
}
