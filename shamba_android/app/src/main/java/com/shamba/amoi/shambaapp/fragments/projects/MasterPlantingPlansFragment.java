//package com.shamba.amoi.shambaapp.fragments.projects;
//
//import android.app.FragmentTransaction;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import com.shamba.amoi.shambaapp.R;
//import com.shamba.amoi.shambaapp.adapters.projects.MasterPlantingPlansRecyclerViewAdapter;
//import com.shamba.amoi.shambaapp.db.DBAdaptor;
//import com.shamba.amoi.shambaapp.db.ShambaAppDB;
//import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlan;
//import com.shamba.amoi.shambaapp.db.projects.MasterPlantingPlanDao;
//import com.shamba.amoi.shambaapp.models.projects.MasterPlantingPlanItem;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
///**
// * A fragment representing a list of Items.
// * <p/>
// * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
// * interface.
// */
//public class MasterPlantingPlansFragment extends Fragment {
//
//   List<MasterPlantingPlanItem> master_plan_list;
//   Button add_master_planting_plan;
//
//    // TODO: Customize parameter argument names
//    private static final String ARG_COLUMN_COUNT = "column-count";
//    // TODO: Customize parameters
//    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public MasterPlantingPlansFragment() {
//    }
//
//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static MasterPlantingPlansFragment newInstance(int columnCount) {
//        MasterPlantingPlansFragment fragment = new MasterPlantingPlansFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        getActivity().setTitle("Master Planting Plans");
//        View view = inflater.inflate(R.submit_form_details.fragment_masterplantingplans_list, container,
//                false);
//
//        GetMasterPlantingPlans getPlans=new GetMasterPlantingPlans();
//        try {
//            master_plan_list=getPlans.execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        // Set the adapter
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(
//                R.id.recycler_list_master_planting_plans);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(new MasterPlantingPlansRecyclerViewAdapter(
//                master_plan_list, mListener, (ProjectsActivity) this.getActivity()));
//
//        add_master_planting_plan=(Button)view.findViewById(R.id.btn_add_master_planting_plan);
//        add_master_planting_plan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction ft=getFragmentManager().beginTransaction();
//                Fragment create_master_plan=new CreateMasterPlantingPlanFragment();
////                ft.replace(R.id.fragment_placeholder_projects,create_master_plan );
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//        });
//
//        return view;
//    }
//
//    private void getMasterPlantingPlanList()  {
//
//        master_plan_list = new ArrayList<>();
//
//        String[][] dummy_master_plans=getDummyMasterPlantingPrograms();
//
//        for (int count = 0; count < dummy_master_plans.length; ++count) {
//            MasterPlantingPlanItem mppi = new MasterPlantingPlanItem();
//            mppi.setId(Integer.parseInt(dummy_master_plans[count][0]));
//            mppi.setPlan_name(dummy_master_plans[count][1]);
//            mppi.setPlan_ref(dummy_master_plans[count][2]);
//            mppi.setFarm_location(dummy_master_plans[count][3]);
//            mppi.setFarm_block(dummy_master_plans[count][4]);
//            mppi.setBlock_size(dummy_master_plans[count][5]);
//            mppi.setProduce(dummy_master_plans[count][6]);
//            mppi.setProduce_quantity(dummy_master_plans[count][7]);
//            mppi.setSeedbed_date(dummy_master_plans[count][8]);
//            mppi.setTransplanting_date(dummy_master_plans[count][9]);
//            mppi.setHarvesting_date(dummy_master_plans[count][10]);
////            mppi.setEstimated_cost(Double.valueOf(dummy_master_plans[count][11]));
////            mppi.setEstimated_revenue(Double.valueOf(dummy_master_plans[count][12]));
//            mppi.setTransplanting_date(dummy_master_plans[count][13]);
//
//            master_plan_list.add(mppi);
//        }
//    }
//
//    public String[][] getDummyMasterPlantingPrograms(){
//
//        String[][] programmes={
//                {"1", "First quarter 2018","Q1_2018","Kangemi","Block A","0.40","Onions", "1000",
//                        "12/12/2017","29/12/2017","28/02/2018","40000","100000", "By Erick" },
//
//                {"2", "First quarter 2018","Q1_2018","Kangemi","Block B","0.40","Tomatos", "200",
//                        "02/12/2017","18/12/2017","02/02/2018", "35000","75000", "By Kitheka" },
//
//                {"3", "First quarter 2018","Q1_2018","Kangemi","Block C","0.40","Managu", "100",
//                        "26/12/2017","14/01/2018","14/02/2018", "15000","35000","By Mutuku" },
//        };
//
//        return programmes;
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(MasterPlantingPlanItem item);
//    }
//    public void getMasterPlanList(){
//
//    }
//
//    class GetMasterPlantingPlans extends AsyncTask<Void, Void, List<MasterPlantingPlanItem>>{
//
//        MasterPlantingPlanDao masterPlantingPlanDao;
//        List<MasterPlantingPlanItem> masterPlantingPlans;
//
//        @Override
//        protected void onPreExecute() {
//            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
//            masterPlantingPlanDao=db.masterPlantPlanDao();
//            masterPlantingPlans=new ArrayList();
//        }
//
//        @Override
//        protected List<MasterPlantingPlanItem> doInBackground(Void... voids) {
//
//            List<MasterPlantingPlan> db_masterPlantingPlans = masterPlantingPlanDao.getAllPlans();
//
//            if (db_masterPlantingPlans.size() > 0){
//
//                for (int count = 0; count < db_masterPlantingPlans.size(); ++count) {
//
//                    MasterPlantingPlanItem masterPlantingPlanItem = new MasterPlantingPlanItem();
//
//                    masterPlantingPlanItem.setId(db_masterPlantingPlans.get(count).getPlan_id());
//                    masterPlantingPlanItem.setEstimated_revenue(db_masterPlantingPlans.get(count).getPlan_estimated_revenue());
//                    masterPlantingPlanItem.setEstimated_cost(db_masterPlantingPlans.get(count).getPlan_estimated_cost());
//                    masterPlantingPlanItem.setHarvesting_date(db_masterPlantingPlans.get(count).getPlan_harvesting_date());
//                    masterPlantingPlanItem.setTransplanting_date(db_masterPlantingPlans.get(count).getPlan_transplanting_date());
//                    masterPlantingPlanItem.setSeedbed_date(db_masterPlantingPlans.get(count).getPlan_seedbed_date());
//                    masterPlantingPlanItem.setProduce_quantity(db_masterPlantingPlans.get(count).getPlan_product_seed_quantity());
//                    masterPlantingPlanItem.setProduce(db_masterPlantingPlans.get(count).getPlan_product_name());
//                    masterPlantingPlanItem.setBlock_size(db_masterPlantingPlans.get(count).getPlan_farm_block_size());
//                    masterPlantingPlanItem.setFarm_block(db_masterPlantingPlans.get(count).getPlan_farm_block());
//                    masterPlantingPlanItem.setFarm_location(db_masterPlantingPlans.get(count).getPlan_farm_location());
//                    masterPlantingPlanItem.setPlan_ref(db_masterPlantingPlans.get(count).getPlan_ref());
//                    masterPlantingPlanItem.setPlan_name(db_masterPlantingPlans.get(count).getPlan_name());
//
//                    masterPlantingPlans.add(masterPlantingPlanItem);
//                }
//        }
//
//            return masterPlantingPlans;
//        }
//
//        @Override
//        protected void onPostExecute(List<MasterPlantingPlanItem> masterPlantingPlanItems) {
////            super.onPostExecute(masterPlantingPlanItems);
//        }
//    }
//}
