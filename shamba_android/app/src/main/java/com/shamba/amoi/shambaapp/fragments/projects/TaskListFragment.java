package com.shamba.amoi.shambaapp.fragments.projects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.projects.TaskListRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskListFragment extends BaseFragment {

    private static final String ARG_program_id = "key_program_id";
    private static final String ARG_program_name = "key_program_name";
    private static final String ARG_crop_name = "key_crop_name";
    private static final String ARG_phase = "key_phase";
    private static PlantingProgramItem plantingProgramItem;
    private static PlantingPhaseItem plantingPhaseItem;


    public int program_id;
    public  String program_name;
    public  String crop_name;
    public String phase;

    Button add_task;
    List<TaskItem> tasks;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TaskListFragment newInstance(int program_id, String program_name,
                                               String phase, String crop_name) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_program_id, program_id);
        bundle.putString(ARG_program_name, program_name);
        bundle.putString(ARG_phase, phase);
        bundle.putString(ARG_crop_name, crop_name);
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

        String title= getString(R.string.title_fragment_task_list)+":\n"+
                PlantingProgramItem.selectedPlantingProgram.getPlanting_name();

        getActivity().setTitle(title);

        View view = inflater.inflate(R.layout.fragment_tasklist_list, container, false);

        plantingProgramItem=BaseFragment.plantingProgramItem;
        plantingPhaseItem=BaseFragment.plantingPhaseItem;

         Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_tasks);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        try {
            tasks=new GetTaskList().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(new TaskListRecyclerViewAdapter((HomeActivity) this.getActivity(),
                tasks, mListener));

        add_task=(Button)view.findViewById(R.id.btn_add_schedule_task);
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,
                        TaskSchedulingFragment.newInstance("new",""));
            }
        });
        return view;
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
        mListener = null;
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(TaskItem item);
    }

    class GetTaskList extends AsyncTask<Void, Void, List<TaskItem>> {

        TaskDao taskDao;
        List<TaskItem> taskList;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            taskDao=db.taskDao();
            taskList=new ArrayList();
        }

        @Override
        protected List<TaskItem> doInBackground(Void... voids) {

            List<Task> db_tasks = taskDao.getProgramPhaseTasks(plantingPhaseItem.getPhase_id(),
                    plantingProgramItem.getPlan_id());

            Log.d("program-id", String.valueOf(plantingProgramItem.getPlan_id()));
            Log.d("program-phase_id", String.valueOf(plantingPhaseItem.getPhase_id()));
            Log.d("program-tasks", String.valueOf(db_tasks.size()));

            if (db_tasks.size() > 0){

                for (int count = 0; count < db_tasks.size(); ++count) {
                    TaskItem taskItem = new TaskItem();
                    taskItem.setActual_assets(db_tasks.get(count).getActual_assets());
                    taskItem.setActual_cost(db_tasks.get(count).getActual_cost());
                    taskItem.setActual_days(db_tasks.get(count).getActual_days());
                    taskItem.setActual_end_date(db_tasks.get(count).getActual_end_date());
                    taskItem.setActual_others(db_tasks.get(count).getActual_others());
                    taskItem.setActual_people(db_tasks.get(count).getActual_people());
                    taskItem.setActual_start_date(db_tasks.get(count).getActual_start_date());
                    taskItem.setId(db_tasks.get(count).getTask_id());
                    taskItem.setPhase_identity(db_tasks.get(count).getPhase_id());
                    taskItem.setPlanned_assets(db_tasks.get(count).getPlanned_assets());
                    taskItem.setPlanned_cost(db_tasks.get(count).getPlanned_cost());
                    taskItem.setPlanned_days(db_tasks.get(count).getPlanned_days());
                    taskItem.setPlanned_end_date(db_tasks.get(count).getPlanned_end_date());
                    taskItem.setPlanned_others(db_tasks.get(count).getPlanned_others());
                    taskItem.setPlanned_people(db_tasks.get(count).getPlanned_people());
                    taskItem.setPlanned_start_date(db_tasks.get(count).getPlanned_start_date());
                    taskItem.setPlanting_program_id(db_tasks.get(count).getPlanting_program_id());
                    taskItem.setTask_name(db_tasks.get(count).getTask_name());
                    taskList.add(taskItem);
                }
            }

            return taskList;
        }

        @Override
        protected void onPostExecute(List<TaskItem> taskItemList) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
