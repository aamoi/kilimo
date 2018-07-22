package com.shamba.amoi.shambaapp.fragments.projects;

import android.app.FragmentTransaction;
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
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;

/**
 * Activities that contain this fragment must implement the
 * {@link TaskSchedulingActuals.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskSchedulingActuals#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskSchedulingActuals extends BaseFragment {
    TextView product_name;
    TextView planting_name;
    TextView planting_phase;

    EditText taskName;
    EditText planned_days;
    EditText planned_startDate;
    EditText planned_endDate;
    EditText planned_People;
    EditText planned_cost;
    EditText planned_assets;
    EditText planned_miscellenious;

    EditText actual_days;
    EditText actual_startDate;
    EditText actual_endDate;
    EditText actual_People;
    EditText actual_cost;
    EditText actual_assets;
    EditText actual_miscellenious;

    Button btn_submit_task;
    String str_taskName;
    Double str_actuals_days;
    String str_actuals_startDate;
    String str_actuals_endDate;
    String str_actuals_People;
    String str_actuals_cost;
    String str_planned_assets;
    String str_planned_miscellenious;

    static PlantingProgramItem plantingProgramItem;
    static PlantingPhaseItem plantingPhaseItem;
    static TaskItem taskItem;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TaskSchedulingActuals() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskSchedulingActuals.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskSchedulingActuals newInstance(String param1, String param2) {
        TaskSchedulingActuals fragment = new TaskSchedulingActuals();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the submit_form_details for this fragment
        View view= inflater.inflate(R.layout.fragment_task_scheduling_actuals,
                container, false);

        plantingProgramItem= BaseFragment.plantingProgramItem;
        plantingPhaseItem= BaseFragment.plantingPhaseItem;
        taskItem=BaseFragment.taskItem;

        product_name=(TextView) view.findViewById(R.id.product_name);
        planting_name=(TextView) view.findViewById(R.id.planting_name);
        planting_phase=(TextView) view.findViewById(R.id.planting_phase);

//        product_name.setText(plantingProgramItem.getPlanting_produce());
        planting_name.setText(plantingProgramItem.getPlanting_name());
        planting_phase.setText(plantingPhaseItem.getPhase_name());

        taskName = (EditText) view.findViewById(R.id.edit_task_name);
        taskName.setText(taskItem.getTask_name());
        taskName.setEnabled(false);

        actual_days = (EditText) view.findViewById(R.id.edit_actual_days);
        actual_startDate = (EditText) view.findViewById(R.id.edit_actual_startDate);
        new DatePickerUtility(actual_startDate).setDateOnEditTextField();
        actual_endDate = (EditText) view.findViewById(R.id.edit_actual_endDate);
        new DatePickerUtility(actual_endDate).setDateOnEditTextField();

        actual_People = (EditText) view.findViewById(R.id.edit_actual_people);
        actual_cost = (EditText) view.findViewById(R.id.edit_actual_cost);

        planned_days=(EditText) view.findViewById(R.id.edit_planned_days);
        planned_days.setText(String.valueOf(taskItem.getPlanned_days()));
        planned_days.setEnabled(false);

        planned_startDate=(EditText) view.findViewById(R.id.edit_planned_startDate);
        new DatePickerUtility(planned_startDate).setDateOnEditTextField();
        planned_startDate.setText(taskItem.getPlanned_start_date());
        planned_startDate.setEnabled(false);

        planned_endDate = (EditText) view.findViewById(R.id.edit_planned_endDate);
        new DatePickerUtility(planned_endDate).setDateOnEditTextField();
        planned_endDate.setText(taskItem.getPlanned_end_date());
        planned_endDate.setEnabled(false);

        planned_People=(EditText) view.findViewById(R.id.edit_planned_people);
        planned_People.setText(taskItem.getPlanned_persons());
        planned_People.setEnabled(false);

        planned_cost=(EditText) view.findViewById(R.id.edit_planned_cost);
        planned_cost.setText(String.valueOf(taskItem.getEstimated_cost()));
        planned_cost.setEnabled(false);

        btn_submit_task = (Button) view.findViewById(R.id.btn_submit_task);
        btn_submit_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_taskName = taskName.getText().toString();
                str_actuals_cost = actual_cost.getText().toString();
                str_actuals_days = Double.valueOf(actual_days.getText().toString());
                str_actuals_endDate = actual_endDate.getText().toString();
                str_actuals_People = actual_People.getText().toString();
                str_actuals_startDate = actual_startDate.getText().toString();

                new SaveScheduledTaskActuals(str_actuals_days,str_actuals_startDate,str_actuals_endDate,
                        str_actuals_People,"",str_actuals_cost,"").execute();

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),R.id.fragment_placeholder_home,
                        new TaskListFragment());
            }
        });
        
        return view;
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

    class SaveScheduledTaskActuals extends AsyncTask<Void, Void, Integer> {
        public int planting_program_id;
        public String phase_identity;
        public int  phase_id;
        public double actuals_days;
        public String actuals_start_date;
        public String actuals_end_date;
        public String actuals_people;
        public String actuals_assets;
        public String actuals_cost;
        public String actuals_others;

        Task task;
        TaskDao taskDao;

        public SaveScheduledTaskActuals(double actuals_days, String actuals_start_date, 
                                        String actuals_end_date, String actuals_people,
                                        String actuals_assets, String actuals_cost, String actuals_others) {

            this.actuals_days = actuals_days;
            this.actuals_start_date = actuals_start_date;
            this.actuals_end_date = actuals_end_date;
            this.actuals_people = actuals_people;
            this.actuals_assets = actuals_assets;
            this.actuals_cost = actuals_cost;
            this.actuals_others = actuals_others;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            taskDao = db.taskDao();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
//            task = taskDao.getTask(taskItem.getId()).get(0);//new Task();
//            task.setActual_assets(actuals_assets);
//            task.setActual_cost(actuals_cost);
//            task.setActual_days(actuals_days);
//            task.setActual_start_date(actuals_start_date);
//            task.setActual_end_date(actuals_end_date);
//            task.setActual_people(actuals_people);
//            task.setActual_others(actuals_others);
//            taskDao.updateTask(task);
            
            Log.d("record saved", "task name: " + task.getTask_name());
            return null;
        }

        @Override
        public void onPostExecute(Integer i) {
        }
    }
}
