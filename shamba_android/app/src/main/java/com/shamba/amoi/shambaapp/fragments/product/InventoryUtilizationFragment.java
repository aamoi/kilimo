package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.product.StockUtilization;
import com.shamba.amoi.shambaapp.db.product.StockUtilizationDao;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.ProductStockItem;
import com.shamba.amoi.shambaapp.models.product.StockUtilizationItem;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
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
import java.util.concurrent.ExecutionException;

public class InventoryUtilizationFragment extends BaseFragment {
    private static final String asset_key = "asset_key";
    public Integer asset_id;

    String TAG = getClass().getSimpleName().toString() + "|";
    Spinner spn_project;
    Spinner spn_phase;
    Spinner spn_task;
    EditText edit_utilized_quantity;
    EditText edit_utilized_date;
    EditText edit_details;
    Button btn_submit_utilization;
    List<PlantingProgramItem> plantingProgramItems;
    List<PhaseItem> phaseItems;
    List<TaskItem> taskItems;
    String project_name;
    String phase_name;
    String task_name;

    int stock_id;
    int project_id;
    int phase_id;
    int task_id;
    double utilized_quantity;
    String utilization_date;
    String details;

    private OnFragmentInteractionListener mListener;

    public InventoryUtilizationFragment() {
        // Required empty public constructor
    }

    public static InventoryUtilizationFragment newInstance(int  asset_id) {
        InventoryUtilizationFragment fragment = new InventoryUtilizationFragment();
        Bundle args = new Bundle();
        args.putInt(asset_key, asset_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            asset_id = getArguments().getInt(asset_key);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String stock_item = null;

        Log.d("Asset id",asset_id.toString());

        if((asset_id!=null)&&(asset_id>0)){
            AssetItem assetItem=AssetItem.getAssetItemById(AssetItem.getAllAssets(getActivity()),
                    asset_id);

            Log.d("InventoryUtil",assetItem.toString());

            String assetName=assetItem.getName();

            ProductItem productItem=ProductItem.getProductItemByID(ProductItem.
                    getAllProducts(getActivity()),assetItem.getFuel_product_id());
            String productName=productItem.getProduct_name();

            String titleName=productName+" for "+assetName;

            Log.d("Title+++",titleName);

            getActivity().setTitle(titleName);
        }
//        String stock_item="Utilize "+BaseFragment.productItem.getProduct_name()+"("+
//                BaseFragment.productStockItem.getStock_quantity()+
//                BaseFragment.productItem.getUnit_of_measure()+" on "+
//                BaseFragment.productStockItem.getStock_date().substring(0,5)+")";
        else{
            getActivity().setTitle(stock_item);
            asset_id=null;
        }

        View view = inflater.inflate(R.layout.fragment_inventory_utilization, container, false);
//        getFragmentView(inflater, container, R.submit_form_details.fragment_inventory_utilization);
        getViews(view);

        btn_submit_utilization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (validateMandatorySpinnerFields(spn_plan_name, spn_task_phase, spn_plan_task)) {
//                    str_plan_name = spn_plan_name.getSelectedItem().toString();
//                    str_task_phase = spn_task_phase.getSelectedItem().toString();
//                    str_plan_task = spn_plan_task.getSelectedItem().toString();
//                }

                project_name = spn_project.getSelectedItem().toString();
                for (int i = 0; i < plantingProgramItems.size(); i++) {
                    if (plantingProgramItems.get(i).getPlanting_name().
                            equalsIgnoreCase(project_name)) {
                        project_id = plantingProgramItems.get(i).getId();
                        break;
                    }
                }

                phase_name = spn_phase.getSelectedItem().toString();
                for (int i = 0; i < phaseItems.size(); i++) {
                    if (phaseItems.get(i).getPhase_name().
                            equalsIgnoreCase(phase_name)) {
                        phase_id = phaseItems.get(i).getId();
                        break;
                    }
                }

                task_name = spn_task.getSelectedItem().toString();
                for (int i = 0; i < taskItems.size(); i++) {
                    if (taskItems.get(i).getTask_name().
                            equalsIgnoreCase(task_name)) {
                        task_id = taskItems.get(i).getId();
                        break;
                    }
                }

                boolean server_status = false;
                utilized_quantity = Double.parseDouble(edit_utilized_quantity.getText().toString());
                utilization_date = edit_utilized_date.getText().toString();
                details = edit_details.getText().toString();
                asset_id=asset_id==null?0:asset_id;
                stock_id = ProductStockItem.selectedProductStockItem.getId();

                try {
                    Log.d("asset id", String.valueOf(asset_id));

                    new SaveStockUtilization(stock_id, task_id, asset_id,utilized_quantity,
                            utilization_date, details).execute().get();
                    server_status = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if (server_status) {
                    if((asset_id!=null)&&(asset_id>0)){
                        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                                R.id.fragment_placeholder_home,
                                 ProductStockFragment.newInstance(asset_id));
                    }
                    else{
                        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                                R.id.fragment_placeholder_home,
                                new ProductStockFragment());
                    }

                }

//                Log.d(TAG, String.valueOf(InventoryUtilizationItem.getInventoryUtilizationItems(getActivity()).size()));

//                if (validateMandatoryEditTextFields(edit_inventory_quantity, edit_comments, edit_inventory_utilization_date)) {
//                    if (InventoryUtilizationItem.saveInventoryUtilization(getActivity(), utilization_id,
//                            str_plan_name, str_task_phase, str_plan_task, str_inventory_quantity,
//                            str_inventory_utilization_date, str_utilization_status, str_comments)) {
//                        Log.d(TAG, "Saved successfully ");
//
//                        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
//                                R.id.fragment_placeholder_home,
//                                new ProductStockFragment());
//                    } else {
//                        Log.d(TAG, "Inventory utilization  not saved.");
//                    }
//                } else {
//                    Log.d(TAG, "Inventory utilization not saved, mandatory field(s) empty!");
//
//                }
            }
        });

        return view;
    }

    private void getViews(final View view) {

        taskItems = new ArrayList<>();

        spn_project = (Spinner) view.findViewById(R.id.spn_project);
        spn_phase = (Spinner) view.findViewById(R.id.spn_phase);
        spn_task = (Spinner) view.findViewById(R.id.spn_task);

        plantingProgramItems = PlantingProgramItem.getAllPlantingPrograms(getActivity());
        List<String> projects = new ArrayList<>();
        for (int i = 0; i < plantingProgramItems.size(); ++i) {
            projects.add(plantingProgramItems.get(i).getPlanting_name());
        }
        spn_project = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_project, projects);

        spn_project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {

                int project_id = PlantingProgramItem.getPlantingProgramByName(getActivity(),
                        spn_project.getSelectedItem().toString()).getId();
                taskItems = TaskItem.getProjectTask(getActivity(), project_id);

                Log.d("Invent. utilization|", "project tasks #"+
                        String.valueOf(taskItems.size()));

//                taskItems = TaskItem.getProjectTask(getActivity(), project_id);
                List<String> tasks = new ArrayList<>();
                for (int i = 0; i < taskItems.size(); ++i) {
                    tasks.add(taskItems.get(i).getTask_name());
                }
                spn_task = SpinnerUtility.setDynamicSpinner(view.getContext(),
                        spn_task, tasks);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        phaseItems = PhaseItem.staticPhaseItems;
        List<String> phases = new ArrayList<>();
        for (int i = 0; i < phaseItems.size(); ++i) {
            phases.add(phaseItems.get(i).getPhase_name());
        }
        spn_phase = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_phase, phases);

        spn_phase.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {

                int project_id = PlantingProgramItem.getPlantingProgramByName(getActivity(),
                        spn_project.getSelectedItem().toString()).getId();

                int phase_id = PhaseItem.getPhaseByName(spn_phase.getSelectedItem().toString()).
                        getId();

                spn_task = (Spinner) view.findViewById(R.id.spn_task);

                taskItems = TaskItem.getPlantingPhaseTask(getActivity(), project_id,phase_id);
                List<String> tasks = new ArrayList<>();
                for (int i = 0; i < taskItems.size(); ++i) {
                    tasks.add(taskItems.get(i).getTask_name());

                }
                spn_task = SpinnerUtility.setDynamicSpinner(view.getContext(),
                        spn_task, tasks);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        edit_utilized_quantity = SharedUtilities.getEditTextById(view, R.id.edit_utilized_quantity);

        edit_utilized_date = SharedUtilities.getEditTextById(view, R.id.edit_utilized_date);
        new DatePickerUtility(edit_utilized_date);

        edit_details = SharedUtilities.getEditTextById(view, R.id.edit_details);

        btn_submit_utilization = SharedUtilities.getButtonById(view, R.id.btn_submit_utilization);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Saves product stock record into both server and local db
     * It only saves on local db after the record is saved to server!
     */
    class SaveStockUtilization extends AsyncTask<Void, Void, Integer> {
        public int stock_id;
        public int assetID;
        public int task_id;
        public double utilized_quantity;
        public String utilized_date;
        public String details;

        JSONObject request_object = new JSONObject();
        JSONObject response_object = new JSONObject();

        StockUtilizationItem stockUtilizationItem = new StockUtilizationItem();
        StockUtilization stockUtilization;
        StockUtilizationDao stockUtilizationDao;

        int id;
        int success = 0;

        public SaveStockUtilization(int stock_id,int task_id,int asset_id,double utilized_quantity,
                                    String utilized_date, String details) {
            this.stock_id = stock_id;
            this.assetID = asset_id;
            this.task_id = task_id;
            this.utilized_quantity = utilized_quantity;
            this.utilized_date = utilized_date;
            this.details = details;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            stockUtilization = new StockUtilization();
            stockUtilizationDao = db.stockUtilizationDao();

            try {
                request_object.put("stock_id", stock_id);
                request_object.put("task_id", task_id);
                request_object.put("asset_id", assetID);
                request_object.put("utilized_quantity", utilized_quantity);
                request_object.put("utilized_date", utilized_date);
                request_object.put("details", details);

                Log.d("Utilization request...", request_object.toString());

            } catch (JSONException e) {
                Log.d("Error preparing request", e.getMessage());
                e.printStackTrace();
            }
            try {
                stockUtilization.setAsset_id(asset_id);
                stockUtilization.setStock_id(stock_id);
                stockUtilization.setTask_id(task_id);
                stockUtilization.setUtilized_date(utilized_date);
                stockUtilization.setUtilized_quantity(utilized_quantity);
                stockUtilization.setDetails(details);

                Log.d("Utiliz. db request...", stockUtilizationItem.toString());
            } catch (Exception e) {
                Log.d("Error creating request", e.getMessage());
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            /**
             * Saving planting record to the server!
             */
            try {
                response_object = CommonHelper.sendPostRequestWithJsonResponse(BuildConfig.SERVER_URL,
                        "createStockUtilization/", request_object.toString());

                Log.d("Utilization response...", response_object.toString());

                id = response_object.getInt("id");

            } catch (IOException e) {
                Log.d("Error posting request", e.getMessage());
                e.printStackTrace();
            } catch (JSONException jsonException) {
                Log.d("Error getting response", jsonException.getMessage());
                jsonException.printStackTrace();
            }

            /**
             * Saving utilization record to local db only if the record is send to the server!
             */
            if (id > 0) {
                try {
                    stockUtilizationItem.setId(id);
                    stockUtilizationDao.insertStockUtilization(stockUtilization);
                    Log.d("Record saved", "utilization id: " + id);
                    success = 1;
                } catch (Exception e) {
                    success = 0;
                    Log.d("Error saving record", e.getMessage());
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
