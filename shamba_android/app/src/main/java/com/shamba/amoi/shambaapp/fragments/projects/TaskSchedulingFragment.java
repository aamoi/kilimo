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
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskSchedulingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskSchedulingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskSchedulingFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM2 = "param2";
    public static String key_view_type = "view_type";
    static PlantingProgramItem plantingProgramItem;
    static PlantingPhaseItem plantingPhaseItem;
    static TaskItem taskItem;

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
    Double str_planned_days;
    String str_planned_startDate;
    String str_planned_endDate;
    String str_planned_People;
    String str_planned_cost;
    String str_planned_assets;
    String str_planned_miscellenious;
    // TODO: Rename and change types of parameters
    private String view_type;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TaskSchedulingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlantingScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the submit_form_details for this fragment
        View view = inflater.inflate(R.layout.fragment_task_scheduling, container, false);

        plantingProgramItem = BaseFragment.plantingProgramItem;
        plantingPhaseItem = BaseFragment.plantingPhaseItem;
        taskItem=BaseFragment.taskItem;

        product_name = (TextView) view.findViewById(R.id.product_name);
        product_name.setText(plantingProgramItem.getPlanting_produce());

        planting_name = (TextView) view.findViewById(R.id.planting_name);
        planting_name.setText(plantingProgramItem.getPlanting_name());

        planting_phase = (TextView) view.findViewById(R.id.planting_phase);
        planting_phase.setText(plantingPhaseItem.getPhase_name());

        findViewByIds(view);

        if(view_type.contains("view"))
        {
            viewTaskDetails();
        }
        else if (view_type.contains("new")){
            editPlanOnlyView();
            btn_submit_task = (Button) view.findViewById(R.id.btn_submit_task);
            btn_submit_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    str_taskName = taskName.getText().toString();
                    str_planned_cost = planned_cost.getText().toString();
                    str_planned_days = Double.valueOf(planned_days.getText().toString());
                    str_planned_endDate = planned_endDate.getText().toString();
                    str_planned_People = planned_People.getText().toString();
                    str_planned_startDate = planned_startDate.getText().toString();

                    new SaveScheduleTask(plantingProgramItem.getPlan_id(), plantingPhaseItem.getPhase_id(),
                            str_taskName, str_planned_days, str_planned_startDate, str_planned_endDate,
                            str_planned_People, str_planned_assets, str_planned_cost, str_planned_miscellenious
                    ).execute();


                    BaseFragment.changeFragment((AppCompatActivity) getActivity(),R.id.fragment_placeholder_home,new TaskListFragment());

//                    TaskListFragment fragment = new TaskListFragment();
//                    FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
//                    ft.replace(R.id.fragment_placeholder_projects, fragment);
//                    ft.addToBackStack(null);
//                    ft.commit();
                }
            });

        }

        return view;
    }

    private void findViewByIds(View view){
        taskName = (EditText) view.findViewById(R.id.edit_task_name);
        planned_days = (EditText) view.findViewById(R.id.edit_planned_days);
        planned_startDate = (EditText) view.findViewById(R.id.edit_planned_startDate);
        planned_endDate = (EditText) view.findViewById(R.id.edit_planned_endDate);
        planned_People = (EditText) view.findViewById(R.id.edit_planned_people);
        planned_cost = (EditText) view.findViewById(R.id.edit_planned_cost);
        actual_days = (EditText) view.findViewById(R.id.edit_actual_days);
        actual_startDate = (EditText) view.findViewById(R.id.edit_actual_startDate);
        actual_endDate = (EditText) view.findViewById(R.id.edit_actual_endDate);
        actual_People = (EditText) view.findViewById(R.id.edit_actual_people);
        actual_cost = (EditText) view.findViewById(R.id.edit_actual_cost);
    }
    private void viewTaskDetails() {
        taskName.setText(taskItem.getTask_name());
        taskName.setEnabled(false);

        planned_days.setText(String.valueOf(taskItem.getPlanned_days()));
        planned_days.setEnabled(false);

        planned_startDate.setText(String.valueOf(taskItem.getActual_start_date()));
        planned_startDate.setEnabled(false);

        planned_endDate.setText(taskItem.getPlanned_end_date());
        planned_endDate.setEnabled(false);

        planned_People.setText(taskItem.getPlanned_people());
        planned_People.setEnabled(false);

        planned_cost.setText(taskItem.getPlanned_cost());
        planned_cost.setEnabled(false);

        actual_days.setText(String.valueOf(taskItem.getActual_days()));
        actual_days.setEnabled(false);

        actual_startDate.setText(String.valueOf(taskItem.getActual_start_date()));
        actual_startDate.setEnabled(false);

        actual_endDate.setText(String.valueOf(taskItem.getActual_end_date()));
        actual_endDate.setEnabled(false);

        actual_People.setText(String.valueOf(taskItem.getActual_people()));
        actual_People.setEnabled(false);

        actual_cost.setText(String.valueOf(taskItem.getActual_cost()));
        actual_cost.setEnabled(false);
    }

    private void editPlanOnlyView() {
        new DatePickerUtility(planned_startDate).setDateOnEditTextField();
        new DatePickerUtility(planned_endDate).setDateOnEditTextField();

        actual_days.setEnabled(false);
        actual_startDate.setEnabled(false);
        actual_endDate.setEnabled(false);
        actual_People.setEnabled(false);
        actual_cost.setEnabled(false);
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

    class SaveScheduleTask extends AsyncTask<Void, Void, Integer> {
        public String planting_program_id;
        public String phase_identity;
        public String task_name;
        public String planned_start_date;
        public String planned_end_date;
        public String planned_people;
        public String planned_assets;
        public String planned_cost;
        public String planned_others;
        double planned_days;
        Task task;
        TaskDao taskDao;

        public SaveScheduleTask(String planting_program_id, String phase_identity,
                                String task_name, double planned_days, String planned_start_date,
                                String planned_end_date, String planned_people, String planned_assets,
                                String planned_cost, String planned_others) {
            this.planting_program_id = planting_program_id;
            this.phase_identity = phase_identity;
            this.task_name = task_name;
            this.planned_days = planned_days;
            this.planned_start_date = planned_start_date;
            this.planned_end_date = planned_end_date;
            this.planned_people = planned_people;
            this.planned_assets = planned_assets;
            this.planned_cost = planned_cost;
            this.planned_others = planned_others;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            taskDao = db.taskDao();
            task = new Task();

            String task_id=planting_program_id+"-"+phase_identity+"-"+task_name;
            task.setTask_id(task_id);
            task.setTask_name(task_name);
            task.setPhase_id(phase_identity);
            task.setPlanting_program_id(planting_program_id);
            task.setPlanned_assets(planned_assets);
            task.setPlanned_cost(planned_cost);
            task.setPlanned_days(planned_days);
            task.setPlanned_start_date(planned_start_date);
            task.setPlanned_end_date(planned_end_date);
            task.setPlanned_people(planned_people);
            task.setPlanned_others(planned_others);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            taskDao.insertTask(task);
            Log.d("record saved", "task name: " + task_name);
            return null;
        }

        @Override
        public void onPostExecute(Integer i) {
        }
    }
}


