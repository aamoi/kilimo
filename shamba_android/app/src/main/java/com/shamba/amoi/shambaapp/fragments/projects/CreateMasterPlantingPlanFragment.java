//package com.shamba.amoi.shambaapp.fragments.projects;
//
//import android.app.DatePickerDialog;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.content.Context;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//
//import com.shamba.amoi.shambaapp.R;
//import com.shamba.amoi.shambaapp.db.DBAdaptor;
//import com.shamba.amoi.shambaapp.db.ShambaAppDB;
//import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlan;
//import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlanDao;
//import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Locale;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link CreateMasterPlantingPlanFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link CreateMasterPlantingPlanFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class CreateMasterPlantingPlanFragment extends Fragment {
//    EditText plan_name;
//    EditText plan_ref;
//    EditText plan_farm_location;
//    EditText plan_farm_block;
//    EditText plan_farm_block_size;
//    EditText plan_product_name;
//    EditText plan_product_seed_quantity;
//    EditText plan_seedbed_date;
//    EditText plan_transplanting_date;
//    EditText plan_harvesting_date;
//    EditText plan_estimated_cost;
//    EditText plan_estimated_revenue;
//    Button save_plan;
//    Calendar myCalendar;
//    String str_plan_name;
//    String str_plan_ref;
//    String str_plan_farm_location;
//    String str_plan_farm_block;
//    String str_plan_farm_block_size;
//    String str_plan_product_name;
//    String str_plan_product_seed_quantity;
//    String str_plan_seedbed_date;
//    String str_plan_transplanting_date;
//    String str_plan_harvesting_date;
//    String str_plan_estimated_cost;
//    String str_plan_estimated_revenue;
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public CreateMasterPlantingPlanFragment() {
//        // Required empty public constructor
//    }
//
//    public static CreateMasterPlantingPlanFragment newInstance(String param1, String param2) {
//        CreateMasterPlantingPlanFragment fragment = new CreateMasterPlantingPlanFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        getActivity().setTitle("New Master Project Plan");
//
//        View view=inflater.inflate(R.submit_form_details.fragment_create_master_planting_plan,
//                container, false);
//
//        plan_name=(EditText)view.findViewById(R.id.edit_plan_name);
//        plan_ref=(EditText)view.findViewById(R.id.edit_plan_ref);
//        plan_farm_location=(EditText)view.findViewById(R.id.edit_farm_location);
//        plan_farm_block=(EditText)view.findViewById(R.id.edit_farm_block);
//        plan_farm_block_size=(EditText)view.findViewById(R.id.edit_block_size);
//        plan_product_name=(EditText)view.findViewById(R.id.edit_plan_produce);
//        plan_product_seed_quantity=(EditText)view.findViewById(R.id.edit_produce_quantity);
//
//        plan_seedbed_date=(EditText)view.findViewById(R.id.edit_seedbed_date);
//        new DatePickerUtility(plan_seedbed_date);
//
//        plan_transplanting_date=(EditText)view.findViewById(R.id.edit_transplanting_date);
//        new DatePickerUtility(plan_transplanting_date);
//
//        plan_harvesting_date=(EditText)view.findViewById(R.id.edit_harvesting_date);
//        new DatePickerUtility(plan_harvesting_date);
//
//        plan_estimated_cost=(EditText)view.findViewById(R.id.edit_estimated_cost);
//        plan_estimated_revenue=(EditText)view.findViewById(R.id.edit_estimated_revenue);
//
//        save_plan=(Button)view.findViewById(R.id.btn_accept_inventory_creation);
//
//        save_plan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                str_plan_name=plan_name.getText().toString();
//                str_plan_ref=plan_ref.getText().toString();
//                str_plan_farm_location=plan_farm_location.getText().toString();
//                str_plan_farm_block=plan_farm_block.getText().toString();
//                str_plan_farm_block_size=plan_farm_block_size.getText().toString();
//                str_plan_product_name=plan_product_name.getText().toString();
//                str_plan_product_seed_quantity=plan_product_seed_quantity.getText().toString();
//                str_plan_seedbed_date=plan_seedbed_date.getText().toString();
//                str_plan_transplanting_date=plan_transplanting_date.getText().toString();
//                str_plan_harvesting_date=plan_harvesting_date.getText().toString();
//                str_plan_estimated_cost=plan_estimated_cost.getText().toString();
//                str_plan_estimated_revenue=plan_estimated_revenue.getText().toString();
//
//                SaveMasterPlantingPlan saveMasterPlantingPlan=new SaveMasterPlantingPlan(
//                        str_plan_name,str_plan_ref,str_plan_farm_location ,str_plan_farm_block,
//                        str_plan_farm_block_size,str_plan_product_name,str_plan_product_seed_quantity,
//                        str_plan_seedbed_date,str_plan_transplanting_date,str_plan_harvesting_date,
//                        str_plan_estimated_cost,str_plan_estimated_revenue);
//
//                saveMasterPlantingPlan.execute();
//
//                Fragment masterplans=new MasterPlantingPlansFragment();
//                FragmentTransaction ft= getFragmentManager().beginTransaction();
////                ft.replace(R.id.fragment_placeholder_projects,masterplans);
//                ft.commit();
//            }
//        });
//
//        return view;
//    }
//
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//
//    class SaveMasterPlantingPlan extends AsyncTask<Void, Void, Integer>  {
//        public int plan_id;
//        public String plan_name;
//        public String plan_ref;
//        public String plan_farm_location;
//        public String plan_farm_block;
//        public String plan_farm_block_size;
//        public String plan_product_name;
//        public String plan_product_seed_quantity;
//        public String plan_seedbed_date;
//        public String plan_transplanting_date;
//        public String plan_harvesting_date;
//        public String plan_estimated_cost;
//        public String plan_estimated_revenue;
//
//        MasterPlantingPlan masterPlan;
//        MasterPlantingPlanDao masterPlanDAO;
//
//        public SaveMasterPlantingPlan(String plan_name, String plan_ref, String plan_farm_location,
//                                      String plan_farm_block,String plan_farm_block_size,
//                                      String plan_product_name, String plan_product_seed_quantity,
//                                      String plan_seedbed_date, String plan_transplanting_date,
//                                      String plan_harvesting_date, String plan_estimated_cost,
//                                      String plan_estimated_revenue){
//            this.plan_name = plan_name;
//            this.plan_ref = plan_ref;
//            this.plan_farm_location = plan_farm_location;
//            this.plan_farm_block = plan_farm_block;
//            this.plan_farm_block_size = plan_farm_block_size;
//            this.plan_product_name = plan_product_name;
//            this.plan_product_seed_quantity = plan_product_seed_quantity;
//            this.plan_seedbed_date = plan_seedbed_date;
//            this.plan_transplanting_date = plan_transplanting_date;
//            this.plan_harvesting_date = plan_harvesting_date;
//            this.plan_estimated_cost = plan_estimated_cost;
//            this.plan_estimated_revenue = plan_estimated_revenue;
//        }
//        @Override
//        public void onPreExecute(){
//            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
//            masterPlanDAO=db.masterPlantPlanDao();
//            masterPlan=new MasterPlantingPlan();
//        }
//
//        @Override
//        protected Integer doInBackground(Void... voids) {
//            masterPlan.setPlan_estimated_cost(plan_estimated_cost);
//            masterPlan.setPlan_estimated_revenue(plan_estimated_revenue);
//            masterPlan.setPlan_farm_block(plan_farm_block);
//            masterPlan.setPlan_farm_block_size(plan_farm_block_size);
//            masterPlan.setPlan_farm_location(plan_farm_location);
//            masterPlan.setPlan_harvesting_date(plan_harvesting_date);
//            masterPlan.setPlan_name(plan_name);
//            masterPlan.setPlan_product_name(plan_product_name);
//            masterPlan.setPlan_product_seed_quantity(plan_product_seed_quantity);
//            masterPlan.setPlan_seedbed_date(plan_seedbed_date);
//            masterPlan.setPlan_ref(plan_ref);
//
//            masterPlanDAO.insertMasterPlantingPlan(masterPlan);
//
//            Log.d("record saved", "plan name: "+plan_name);
//            return null;
//        }
//
//       @Override
//        public void onPostExecute(Integer i){
//        }
//    }
//}
