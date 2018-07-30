package com.shamba.amoi.shambaapp.fragments.projects;

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
import android.widget.TextView;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TaskSchedulingFragment extends BaseFragment {
    private static final String ARG_PARAM2 = "param2";
    public static String key_view_type = "view_type";
    static PlantingProgramItem plantingProgramItem;
    static PhaseItem phaseItem;
    static TaskItem taskItem;

    int product_id;
    TextView product_name;
    String produce_name;

    TextView planting_name;
    TextView planting_phase;

    EditText edit_task_name;

    EditText edit_planned_startDate;
    EditText edit_actual_startDate;

    EditText edit_planned_endDate;
    EditText edit_actual_endDate;

    EditText edit_planned_days;
    EditText edit_actual_days;

    EditText edit_planned_people;
    EditText edit_actual_people;

    EditText edit_planned_cost;
    EditText edit_actual_cost;

    EditText edit_planned_revenue;
    EditText edit_actual_revenue;

    EditText edit_required_assets;
    EditText edit_required_products;
    EditText edit_details;

    Spinner spn_task_complete_status;
    String task_complete_status;

    String task_name;
    String planned_startDate;
    String actual_startDate;
    String planned_endDate;
    String actual_endDate;
    double planned_days;
    double actual_days;
    double planned_people;
    double actual_people;
    double planned_cost;
    double actual_cost;
    double planned_revenue;
    double actual_revenue;
    String required_assets;
    String required_products;
    String details;

    Button btn_submit_task;

    private String view_type;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TaskSchedulingFragment() {
    }

    public static TaskSchedulingFragment newInstance(String view_type, String param2) {
        TaskSchedulingFragment fragment = new TaskSchedulingFragment();
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
        getActivity().setTitle(R.string.title_fragment_add_task);
        View view = inflater.inflate(R.layout.fragment_task_scheduling, container,
                false);

        boolean isNew = view_type.equalsIgnoreCase("edit");

        plantingProgramItem = PlantingProgramItem.selectedPlantingProgram;
        phaseItem = PhaseItem.selectedPhaseItem;
        taskItem = isNew ? (new TaskItem()) : TaskItem.selectedTaskItem;

        product_id = plantingProgramItem.getProduct_id();
        produce_name = ProductItem.getProductItemByID(ProductItem.staticProductItemList, product_id).
                getProduct_name();
        product_name = (TextView) view.findViewById(R.id.product_name);
        product_name.setText(produce_name);

        planting_name = (TextView) view.findViewById(R.id.planting_name);
        planting_name.setText(plantingProgramItem.getPlanting_name());

        planting_phase = (TextView) view.findViewById(R.id.planting_phase);
        planting_phase.setText(phaseItem.getPhase_name());

        findViewByIds(view);

        if (view_type.contains("edit")) {
            addActualDetails();
        } else if (view_type.contains("new")) {
            addPlannedDetails();
        }

        btn_submit_task = (Button) view.findViewById(R.id.btn_submit_task);
        btn_submit_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (view_type.contains("edit")) {
                    getUpdateTaskValues();
                } else if (view_type.contains("new")) {
                    getNewTaskValues();
                }
//                    task_name = edit_task_name.getText().toString();
//                    planned_startDate = edit_planned_startDate.getText().toString();
//                    actual_startDate = edit_actual_startDate.getText().toString();
//                    planned_endDate = edit_planned_endDate.getText().toString();
//                    actual_endDate = edit_actual_endDate.getText().toString();
//                    planned_days = Double.parseDouble(edit_planned_days.getText().toString());
////                    actual_days = Double.parseDouble(edit_actual_days.getText().toString());
//                    planned_people = Integer.parseInt(edit_planned_people.getText().toString());
////                    actual_people = Double.parseDouble(edit_actual_people.getText().toString());
//                    planned_cost = Double.parseDouble(edit_planned_cost.getText().toString());
////                    actual_cost = Double.parseDouble(edit_actual_cost.getText().toString());
//                    planned_revenue = Double.parseDouble(edit_planned_revenue.getText().toString());
////                    actual_revenue = Double.parseDouble(edit_actual_revenue.getText().toString());
//                    required_assets = edit_required_assets.getText().toString();
//                    required_products = edit_required_products.getText().toString();
//                    details = edit_details.getText().toString();

                new SaveScheduleTask(plantingProgramItem.id, phaseItem.getId(), task_name,
                        planned_startDate, actual_startDate, planned_endDate,
                        actual_endDate, planned_days, actual_days, planned_people, actual_people,
                        planned_cost, actual_cost, planned_revenue, actual_revenue, required_assets,
                        required_products, details, task_complete_status).execute();

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new TaskListFragment());
            }
        });

//        }

        return view;
    }

    private void findViewByIds(View view) {
        edit_task_name = (EditText) view.findViewById(R.id.edit_task_name);
        edit_planned_startDate = (EditText) view.findViewById(R.id.edit_planned_startDate);
        edit_actual_startDate = (EditText) view.findViewById(R.id.edit_actual_startDate);
        edit_planned_endDate = (EditText) view.findViewById(R.id.edit_planned_endDate);
        edit_actual_endDate = (EditText) view.findViewById(R.id.edit_actual_endDate);
        edit_planned_days = (EditText) view.findViewById(R.id.edit_planned_days);
        edit_actual_days = (EditText) view.findViewById(R.id.edit_actual_days);
        edit_planned_people = (EditText) view.findViewById(R.id.edit_planned_people);
        edit_actual_people = (EditText) view.findViewById(R.id.edit_actual_people);
        edit_planned_cost = (EditText) view.findViewById(R.id.edit_planned_cost);
        edit_actual_cost = (EditText) view.findViewById(R.id.edit_actual_cost);
        edit_planned_revenue = (EditText) view.findViewById(R.id.edit_planned_revenue);
        edit_actual_revenue = (EditText) view.findViewById(R.id.edit_actual_revenue);
        edit_required_assets = (EditText) view.findViewById(R.id.edit_required_assets);
        edit_required_products = (EditText) view.findViewById(R.id.edit_required_products);
        edit_details = (EditText) view.findViewById(R.id.edit_details);

        spn_task_complete_status = (Spinner) view.findViewById(R.id.spn_task_complete_status);
    }

    private void getNewTaskValues() {
        task_name = edit_task_name.getText().toString();
        planned_startDate = edit_planned_startDate.getText().toString();
        actual_startDate = edit_actual_startDate.getText().toString();
        planned_endDate = edit_planned_endDate.getText().toString();
        actual_endDate = edit_actual_endDate.getText().toString();
        planned_days = Double.parseDouble(edit_planned_days.getText().toString());
        planned_people = Integer.parseInt(edit_planned_people.getText().toString());
        planned_cost = Double.parseDouble(edit_planned_cost.getText().toString());
        planned_revenue = Double.parseDouble(edit_planned_revenue.getText().toString());
        required_assets = edit_required_assets.getText().toString();
        required_products = edit_required_products.getText().toString();
        details = edit_details.getText().toString();
        spn_task_complete_status.setEnabled(false);
    }

    private void getUpdateTaskValues() {
        task_name = edit_task_name.getText().toString();
        planned_startDate = edit_planned_startDate.getText().toString();
        actual_startDate = edit_actual_startDate.getText().toString();
        planned_endDate = edit_planned_endDate.getText().toString();
        actual_endDate = edit_actual_endDate.getText().toString();
        planned_days = Double.parseDouble(edit_planned_days.getText().toString());
        actual_days = Double.parseDouble(edit_actual_days.getText().toString());
        planned_people = Double.parseDouble(edit_planned_people.getText().toString());
        actual_people = Double.parseDouble(edit_actual_people.getText().toString());
        planned_cost = Double.parseDouble(edit_planned_cost.getText().toString());
        actual_cost = Double.parseDouble(edit_actual_cost.getText().toString());
        planned_revenue = Double.parseDouble(edit_planned_revenue.getText().toString());
        actual_revenue = Double.parseDouble(edit_actual_revenue.getText().toString());
        required_assets = edit_required_assets.getText().toString();
        required_products = edit_required_products.getText().toString();
        details = edit_details.getText().toString();
        spn_task_complete_status.setEnabled(true);
        task_complete_status = spn_task_complete_status.getSelectedItem().toString();
    }

    private void addActualDetails() {
        taskItem = TaskItem.selectedTaskItem;
        edit_task_name.setText(TaskItem.selectedTaskItem.getTask_name());
        edit_task_name.setEnabled(false);

        edit_planned_days.setText(String.valueOf(taskItem.getPlanned_days()));
        edit_planned_days.setEnabled(false);
//        String start_date_planned = String.valueOf(taskItem.getPlanned_start_date());

        edit_planned_startDate.setText(String.valueOf(taskItem.getPlanned_start_date()));
        edit_planned_startDate.setEnabled(false);

        edit_planned_endDate.setText(taskItem.getPlanned_end_date());
        edit_planned_endDate.setEnabled(false);

        edit_planned_people.setText(String.valueOf(taskItem.getPlanned_persons()));
        edit_planned_people.setEnabled(false);

        edit_planned_cost.setText(String.valueOf(taskItem.getEstimated_cost()));
        edit_planned_cost.setEnabled(false);

        edit_planned_revenue.setText(String.valueOf(taskItem.getEstimated_revenue()));
        edit_planned_revenue.setEnabled(false);

        edit_actual_days.setText(String.valueOf(taskItem.getActual_days()));
//        edit_actual_days.setEnabled(false);

        edit_actual_startDate.setText(String.valueOf(taskItem.getActual_start_date()));
//        edit_actual_startDate.setEnabled(false);

        edit_actual_endDate.setText(String.valueOf(taskItem.getActual_end_date()));
//        edit_actual_endDate.setEnabled(false);

        new DatePickerUtility(edit_actual_startDate).setDateOnEditTextField();
        new DatePickerUtility(edit_actual_endDate).setDateOnEditTextField();

        edit_actual_people.setText(String.valueOf(taskItem.getActual_persons()));
//        edit_actual_people.setEnabled(false);

        edit_actual_cost.setText(String.valueOf(taskItem.getActual_cost()));
//        edit_actual_cost.setEnabled(false);

        edit_actual_revenue.setText(String.valueOf(taskItem.getActual_revenue()));
//        edit_actual_revenue.setEnabled(false);

        edit_required_products.setText(String.valueOf(taskItem.getRequired_products()));
//        edit_required_products.setEnabled(false);

        edit_required_assets.setText(String.valueOf(taskItem.getRequired_assets()));
//        edit_required_assets.setEnabled(false);

        edit_details.setText(String.valueOf(taskItem.getDetails()));
//        edit_details.setEnabled(false);

    }

    private void addPlannedDetails() {
        new DatePickerUtility(edit_planned_startDate).setDateOnEditTextField();
        new DatePickerUtility(edit_planned_endDate).setDateOnEditTextField();

        edit_actual_days.setEnabled(false);
        edit_actual_startDate.setEnabled(false);
        edit_actual_endDate.setEnabled(false);
        edit_actual_people.setEnabled(false);
        edit_actual_cost.setEnabled(false);
        edit_actual_revenue.setEnabled(false);
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

    class SaveScheduleTask extends AsyncTask<Void, Void, Integer> {
        public int task_id;
        public int project_id;
        public int phase_id;
        public String task_name;
        public String planned_startDate;
        public String actual_startDate;
        public String planned_endDate;
        public String actual_endDate;
        public double planned_days;
        public double actual_days;
        public double planned_people;
        public double actual_people;
        public double planned_cost;
        public double actual_cost;
        public double planned_revenue;
        public double actual_revenue;
        public String required_assets;
        public String required_products;
        public String details;
        public String status;
        JSONObject request_object = new JSONObject();
        JSONObject response_object = new JSONObject();
        Task task;
        TaskDao taskDao;
        int success = 0;

        public SaveScheduleTask(int project_id, int phase_id, String task_name, String planned_startDate,
                                String actual_startDate, String planned_endDate, String actual_endDate,
                                double planned_days, double actual_days, double planned_people,
                                double actual_people, double planned_cost, double actual_cost,
                                double planned_revenue, double actual_revenue, String required_assets,
                                String required_products, String details, String status) {
            this.project_id = project_id;
            this.phase_id = phase_id;
            this.task_name = task_name;
            this.planned_startDate = planned_startDate;
            this.actual_startDate = actual_startDate;
            this.planned_endDate = planned_endDate;
            this.actual_endDate = actual_endDate;
            this.planned_days = planned_days;
            this.actual_days = actual_days;
            this.planned_people = planned_people;
            this.actual_people = actual_people;
            this.planned_cost = planned_cost;
            this.actual_cost = actual_cost;
            this.planned_revenue = planned_revenue;
            this.actual_revenue = actual_revenue;
            this.required_assets = required_assets;
            this.required_products = required_products;
            this.details = details;
            this.status = status;
        }

        public int getPhase_id() {
            return phase_id;
        }

        public void setPhase_id(int phase_id) {
            this.phase_id = phase_id;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            taskDao = db.taskDao();
            task = new Task();

            try {
                request_object.put("project_id", project_id);
                request_object.put("task_name", task_name);
                request_object.put("planned_start_date", planned_startDate);
                request_object.put("planned_end_date", planned_endDate);
                request_object.put("planned_days", planned_days);
                request_object.put("phase_id", phase_id);
                request_object.put("planned_persons", planned_people);
                request_object.put("estimated_cost", planned_cost);
                request_object.put("estimated_revenue", planned_revenue);
                request_object.put("required_assets", required_assets);
                request_object.put("required_products", required_products);
                request_object.put("details", details);

                if (view_type.contains("edit")) {
                    Log.d("++++++","++++++");
                    request_object.put("actual_start_date", actual_startDate);
                    request_object.put("actual_end_date", actual_endDate);
                    request_object.put("actual_days", actual_days);
                    request_object.put("actual_persons", actual_people);
                    request_object.put("actual_cost", actual_cost);
                    request_object.put("actual_revenue", actual_revenue);
                    request_object.put("completion_status", status);

                }

                Log.d("Task request...", request_object.toString());

            } catch (JSONException e) {
                Log.d("Error preparing request", e.getMessage());
                e.printStackTrace();
            }
            try {
                task.setPlanned_days(planned_days);
                Log.d("Task db request...", task.toString());
            } catch (Exception e) {
                Log.d("Error creating request", e.getMessage());
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            /**
             * Saving task record to the server!
             */
            try {
                if (view_type.contains("edit")) {
                    response_object = CommonHelper.sendPutRequestWithJsonResponse(BuildConfig.SERVER_URL,
                            "updateTask/"+String.valueOf(taskItem.getId()), request_object.toString());
                } else if (view_type.contains("new")) {
                    response_object = CommonHelper.sendPostRequestWithJsonResponse(BuildConfig.SERVER_URL,
                            "createTask/", request_object.toString());
                }
//                response_object = CommonHelper.sendPostRequestWithJsonResponse(BuildConfig.SERVER_URL,
//                        "createTask/", request_object.toString());

                Log.d("Task response...", response_object.toString());

                task_id = response_object.getInt("id");

            } catch (IOException e) {
                Log.d("Error posting request", e.getMessage());
                e.printStackTrace();
            } catch (JSONException jsonException) {
                Log.d("Error getting response", jsonException.getMessage());
                jsonException.printStackTrace();
            }

            /**
             * Saving planting record to local db only if the record is send to the server!
             */
            if ((task_id > 0)&&view_type.contains("new")) {
                try {
                    task.setId(task_id);
                    task.setProject_id(project_id);
                    task.setPhase_id(phase_id);
                    task.setTask_name(task_name);
                    task.setPlanned_start_date(planned_startDate);
                    task.setPlanned_end_date(planned_endDate);
                    task.setPlanned_days(planned_days);
                    task.setPlanned_persons(planned_people);
                    task.setEstimated_cost(planned_cost);
                    task.setEstimated_revenue(planned_revenue);

                    request_object.put("estimated_revenue", planned_revenue);

                    taskDao.insertTask(task);
                    Log.d("Record saved", "planting name: " + planting_name);
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


