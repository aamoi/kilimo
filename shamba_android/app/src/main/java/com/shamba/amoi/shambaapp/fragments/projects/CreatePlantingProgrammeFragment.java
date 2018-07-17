package com.shamba.amoi.shambaapp.fragments.projects;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlan;
import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlanDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.models.projects.CropItem;
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
    static List<CropItem> crops;
    static PlantingProgramItem plantingProgramItem;
    private EditText edit_planting_name;
    private Spinner spn_planting_produce;
    private EditText edit_seed_quantity;
    private EditText edit_preparation_date;
    private EditText edit_seedbed_date;
    private EditText edit_transplanting_date;
    private EditText edit_harvesting_date;
    private EditText edit_sales_date;
    private Spinner spn_planting_location;
    private Spinner spn_planting_block;
    private EditText edit_planting_cost;
    private EditText edit_planting_revenue;
    private Button save_program;
    private String str_planting_name;
    private String str_planting_produce;
    private double seed_quantity;
    private String str_preparation_date;
    private String str_seedbed_date;
    private String str_transplanting_date;
    private String str_harvesting_date;
    private String str_sales_date;
    private String str_planting_location;
    private String str_planting_block;
    private double planting_cost;
    private double planting_revenue;
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

        plantingProgramItem = BaseFragment.plantingProgramItem;

        if (view_type.contains("view")) {
            viewPlantingProgramDetails();
        } else if (view_type.contains("new")) {

            List<String> list = new ArrayList<>();
            crops = BaseFragment.saveCrops();
            for (int i = 0; i < BaseFragment.cropItems.size(); ++i) {
                list.add(crops.get(i).getCrop_name());
            }
            spn_planting_produce = SpinnerUtility.setDynamicSpinner(view.getContext(),
                    spn_planting_produce, list);

            List<String> locations = new ArrayList<>();
            List<LocationItem> locationItems = BaseFragment.saveLocations();
            for (int i = 0; i < locationItems.size(); ++i) {
                locations.add(locationItems.get(i).getLocation_name());
            }
            spn_planting_location = SpinnerUtility.setDynamicSpinner(view.getContext(),
                    spn_planting_location, locations);

        } else {

        }

        save_program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 str_planting_name=edit_planting_name.getText().toString();
//                 str_planting_produce=spn_planting_produce.getSelectedItem().toString();
//                 seed_quantity= Double.parseDouble(edit_seed_quantity.getText().toString());
//                 str_preparation_date=edit_preparation_date.getText().toString();
//                 str_seedbed_date=edit_seedbed_date.getText().toString();
//                 str_transplanting_date=edit_transplanting_date.getText().toString();
//                 str_harvesting_date=edit_harvesting_date.getText().toString();
//                 str_sales_date=edit_sales_date.getText().toString();
//                 str_planting_location=spn_planting_location.getSelectedItem().toString();
//                 str_planting_block=spn_planting_block.getSelectedItem().toString();
//                 planting_cost= Double.parseDouble(edit_planting_cost.getText().toString());
//                 planting_revenue= Double.parseDouble(edit_planting_revenue.getText().toString());

                SavePlantingProgram savePlantingProgram = new SavePlantingProgram(str_planting_name,
                        str_planting_produce, seed_quantity, str_preparation_date, str_seedbed_date,
                        str_transplanting_date, str_harvesting_date, str_sales_date,
                        str_planting_location, str_planting_block, planting_cost, planting_revenue);
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
        List<String> list = new ArrayList<>();
        crops = BaseFragment.saveCrops();
        for (int i = 0; i < BaseFragment.cropItems.size(); ++i) {
            list.add(crops.get(i).getCrop_name());
        }
        spn_planting_produce = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_planting_produce, list);

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

        spn_planting_location = (Spinner) view.findViewById(R.id.spn_planting_location);
        List<String> locations = new ArrayList<>();
        List<LocationItem> locationItems = BaseFragment.saveLocations();
        for (int i = 0; i < locationItems.size(); ++i) {
            locations.add(locationItems.get(i).getLocation_name());
        }
        spn_planting_location = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_planting_location, locations);

        spn_planting_block = (Spinner) view.findViewById(R.id.spn_planting_block);

        edit_planting_cost = (EditText) view.findViewById(R.id.edit_planting_cost);
        edit_planting_revenue = (EditText) view.findViewById(R.id.edit_planting_revenue);

        save_program = (Button) view.findViewById(R.id.btn_accept);
    }

    private void viewPlantingProgramDetails() {

        edit_planting_name.setText(plantingProgramItem.getPlanting_name());
        edit_planting_name.setEnabled(false);

        spn_planting_produce.setSelection(2);
        spn_planting_produce.setEnabled(false);

        spn_planting_location.setSelection(1);
        spn_planting_location.setEnabled(false);

        spn_planting_block.setSelection(3);
        spn_planting_block.setEnabled(false);

        edit_planting_cost.setText((String.valueOf(plantingProgramItem.getPlanting_cost())));
        edit_planting_cost.setEnabled(false);

        edit_planting_revenue.setText((String.valueOf(plantingProgramItem.getPlanting_revenue())));
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

    class SavePlantingProgram extends AsyncTask<Void, Void, Integer> {

        public String planting_id;
        public String str_planting_name;
        public String str_planting_produce;
        public double seed_quantity;
        public String str_preparation_date;
        public String str_seedbed_date;
        public String str_transplanting_date;
        public String str_harvesting_date;
        public String str_sales_date;
        public String str_planting_location;
        public String str_planting_block;
        public double planting_cost;
        public double planting_revenue;

        PlantingProgram plantingProgram;
        PlantingProgramDao plantingProgramDao;

        public SavePlantingProgram(String str_planting_name, String str_planting_produce,
                                   double seed_quantity, String str_preparation_date, String str_seedbed_date,
                                   String str_transplanting_date, String str_harvesting_date, String str_sales_date,
                                   String str_planting_location, String str_planting_block, double planting_cost,
                                   double planting_revenue) {
            this.str_planting_name = str_planting_name;
            this.str_planting_produce = str_planting_produce;
            this.seed_quantity = seed_quantity;
            this.str_preparation_date = str_preparation_date;
            this.str_seedbed_date = str_seedbed_date;
            this.str_transplanting_date = str_transplanting_date;
            this.str_harvesting_date = str_harvesting_date;
            this.str_sales_date = str_sales_date;
            this.str_planting_location = str_planting_location;
            this.str_planting_block = str_planting_block;
            this.planting_cost = planting_cost;
            this.planting_revenue = planting_revenue;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
//            plantingProgramDao=db.plantingProgramDao();
//            plantingProgram=new PlantingProgram();
//
//            String program_id=str_planting_location+"/"+str_planting_block+"/"+str_planting_produce+
//                    "/"+str_preparation_date.replaceAll("/","").substring(0,4);
//
//            Log.d("planting-id",program_id);
//
//            plantingProgram.setPlanting_cost(planting_cost);
//            plantingProgram.setPlanting_revenue(planting_revenue);
//            plantingProgram.setPlanting_block(str_planting_block);
//            plantingProgram.setPlanting_location(str_planting_location);
//            plantingProgram.setPlanting_name(str_planting_name);
//            plantingProgram.setPlanting_produce(str_planting_produce);

////            plantingProgram.setPlan_id(str_planting_name+"-"+SharedUtilities.getMonthStamp());
//            plantingProgram.setPlan_id(program_id);

//            plantingProgram.setPlanting_cost(planting_cost);
//            plantingProgram.setPlanting_revenue(planting_revenue);
//            plantingProgram.setSeed_quantity(seed_quantity);
//
//            plantingProgram.setPreparation_date(str_preparation_date);
//            plantingProgram.setSeedbed_date(str_seedbed_date);
//            plantingProgram.setTransplanting_date(str_transplanting_date);
//            plantingProgram.setHarvesting_date(str_harvesting_date);
//            plantingProgram.setSales_date(str_sales_date);

        }

        @Override
        protected Integer doInBackground(Void... voids) {


            JSONObject obj = new JSONObject();

            try {
//
//                {"planting_name":"KwaGeorge Sianda", "planting_details":"Maize to be sold when green!",
//                        "product_id":1,"seed_quantity":6,
//                        "planned_preparation_date":"08-0-2018", "planned_seedbed_date":"08-08-2018"
//                        ,"planned_transplant_date":"08-08-2018", "planned_harvest_date":"0-08-2018",
//                        "planned_sales_date":"08-08-2018", "location_id":2,
//                        "block_id":1, "estimated_cost":14000,
//                        "estimated_sales_quantity":1000, "estimated_revenue":44000.0
//                }

                obj.put("planting_name", "KwaGeorge Mrende");
                obj.put("planting_details", "To sell to Susan Mama Mboga");
                obj.put("product_id", 6);
                obj.put("seed_quantity", 600);
                obj.put("planned_preparation_date", "08-08-2018");
                obj.put("planned_seedbed_date", "08-08-2018");
                obj.put("planned_transplant_date", "08-08-2018");
                obj.put("planned_harvest_date", "0-08-2018");
                obj.put("planned_sales_date", "08-08-2018");
                obj.put("location_id", 2);
                obj.put("block_id", 1);
                obj.put("estimated_cost", 14000);
                obj.put("estimated_sales_quantity", 16000);
                obj.put("estimated_revenue", 12000);

            } catch (JSONException e) {
                e.printStackTrace();
            }

//            String payload = obj.toString();
//            JSONObject response= null;

            JSONObject response = null;

            Log.d("post request++ ", obj.toString());

            try {
                response = CommonHelper.sendPostRequestWithJsonResponse(
                        BuildConfig.SERVER_URL, "createPlanting/",
//                        " {\"planting_name\":\"KwaGeorge Cabbagesss\"," +
//                                "\"planting_details\":\"Maize to be sold when green!\",\"product_id\":1,\"seed_quantity\":6," +
//                                "\"planned_preparation_date\":\"08-0-2018\",\"planned_seedbed_date\":\"08-08-2018\",\"planned_transplant_date\":\"08-08-2018\",\n" +
//                                " \"planned_harvest_date\":\"0-08-2018\",\"planned_sales_date\":\"08-08-2018\",\"location_id\":2,\"block_id\":1,\"estimated_cost\":14000,\n" +
//                                " \"estimated_sales_quantity\":1000,\"estimated_revenue\":44000.0\n" + " }");

                        obj.toString());

                Log.d("plantings", response.getString("planting_name"));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


//            plantingProgramDao.insertPlantingProgram(plantingProgram);
//            Log.d("record saved", "planting name: "+str_planting_name);
            return null;
        }

        @Override
        public void onPostExecute(Integer i) {
        }
    }
}
