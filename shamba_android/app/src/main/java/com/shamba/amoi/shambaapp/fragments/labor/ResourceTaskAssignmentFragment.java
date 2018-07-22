package com.shamba.amoi.shambaapp.fragments.labor;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;
import com.shamba.amoi.shambaapp.db.labor.TaskPayment;
import com.shamba.amoi.shambaapp.db.labor.TaskPaymentDao;
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
import com.shamba.amoi.shambaapp.models.labor.PaymentRateItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;
import com.shamba.amoi.shambaapp.shareResources.SpinnerUtility;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResourceTaskAssignmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResourceTaskAssignmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourceTaskAssignmentFragment extends BaseFragment {
    Spinner spn_plan_name;
    Spinner spn_task_phase;
    Spinner spn_plan_task;
    Spinner spn_pay_rate;
    EditText edit_start_date;
    EditText edit_end_date;
    EditText edit_total_work;
    EditText edit_total_payment;
    Spinner  spn_complete_status;
    EditText edit_comments;
    Button btn_submit_task_assignment;

    String plan_name;
    String task_phase;
    String plan_task;
    String pay_rate;
    String start_date;
    String end_date;
    double total_work;
    double total_payment;
    boolean complete_status;
    String comments;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ResourceTaskAssignmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResourceTaskAssignmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResourceTaskAssignmentFragment newInstance(String param1, String param2) {
        ResourceTaskAssignmentFragment fragment = new ResourceTaskAssignmentFragment();
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

        getActivity().setTitle(R.string.title_fragment_assign_resource_task);
        View view=inflater.inflate(R.layout.fragment_resource_task_assignment, container, false);
        getViewsByIds(view);

        btn_submit_task_assignment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                 String resource_id= HumanResourceItem.current_hr_resource.getResource_id();

                String plan_id=spn_plan_name.getSelectedItem().toString();

//                 String plan_id= PlantingProgramItem.getPlantingProgram(getActivity(),plan_name).getPlan_id();

                 task_phase=spn_task_phase.getSelectedItem().toString();
                 String phase_id= PlantingPhaseItem.getPlantingPhase(task_phase).getPhase_id();

                String task_id=spn_plan_task.getSelectedItem().toString();
//                 String task_id= TaskItem.getTaskItem(getActivity(),plan_task).getId();

                String assignment_id=resource_id+"--"+task_id;

                pay_rate=spn_pay_rate.getSelectedItem().toString();
                 String pay_rate_id= PaymentRateItem.getPaymentRateItemByName(pay_rate).getPay_rate_id();

                 start_date=edit_start_date.getText().toString();
                 end_date=edit_end_date.getText().toString();
                 total_work= Double.parseDouble(edit_total_work.getText().toString());
                 total_payment= Double.parseDouble(edit_total_payment.getText().toString());
                 complete_status= Boolean.parseBoolean(spn_complete_status.getSelectedItem().toString());
                 comments= edit_comments.getText().toString();

                new SaveTaskAssignment(assignment_id,resource_id,plan_id,phase_id,task_id,pay_rate_id,
                        start_date,end_date,total_work,total_payment,complete_status,comments).execute();

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,new TaskAssignmentListFragment());

            }
        });
        return view;
    }

    private void getViewsByIds(View view) {

        spn_plan_name= SharedUtilities.getSpinnerById(view, R.id.spn_plan_name);
        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_plan_name,
                SharedUtilities.getHashMapStringKeySetList(BaseFragment.getAllPlantingPrograms(
                        (AppCompatActivity) getActivity()).keySet()));

        spn_task_phase= SharedUtilities.getSpinnerById(view, R.id.spn_task_phase);
        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_task_phase,
                SharedUtilities.getHashMapStringKeySetList(PlantingPhaseItem.
                        getPlantingPhaseItemsHashMap().keySet()));

        spn_plan_task= SharedUtilities.getSpinnerById(view, R.id.spn_plan_task);
//        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_plan_task,
//                SharedUtilities.getHashMapStringKeySetList(BaseFragment.
//                        getAllProjectsTasks((AppCompatActivity)this.getActivity()).keySet()));

        spn_pay_rate= SharedUtilities.getSpinnerById(view, R.id.spn_pay_rate);
        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_pay_rate,
                SharedUtilities.getHashMapStringKeySetList(PaymentRateItem.getPaymentRates().keySet()));

        edit_start_date= SharedUtilities.getEditTextById(view, R.id.edit_start_date);
        new DatePickerUtility(edit_start_date);

        edit_end_date= SharedUtilities.getEditTextById(view, R.id.edit_end_date);
        new DatePickerUtility(edit_end_date);

        edit_total_work= SharedUtilities.getEditTextById(view, R.id.edit_total_work);
        edit_total_payment= SharedUtilities.getEditTextById(view, R.id.edit_total_payment);
        spn_complete_status= SharedUtilities.getSpinnerById(view, R.id.spn_complete_status);
        edit_comments= SharedUtilities.getEditTextById(view, R.id.edit_comments);
        btn_submit_task_assignment=SharedUtilities.getButtonById(view,R.id.btn_submit_task_assignment);
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

    class SaveTaskAssignment extends AsyncTask<Void, Void, Integer> {
        public String assignment_id;
        public String resource_id;
        public String plan_id;
        public String phase_id;
        public String task_id;
        public String pay_rate_id;
        public String assignment_start_date;
        public String assignment_end_date;
        public double quantity_worked;
        public double amount_due;
        public boolean complete_status;
        public String comments;

        TaskAssignment taskAssignment;
        TaskAssignmentDao taskAssignmentDao;

        TaskPayment taskPayment;
        TaskPaymentDao taskPaymentDao;

        public SaveTaskAssignment(String assignment_id, String resource_id,String plan_id,
                                  String phase_id, String task_id, String pay_rate_id,
                                  String assignment_start_date, String assignment_end_date,
                                  double quantity_worked, double amount_due,
                                  boolean complete_status, String comments){
            this.assignment_id = assignment_id;
            this.resource_id=resource_id;
            this.plan_id = plan_id;
            this.phase_id = phase_id;
            this.task_id = task_id;
            this.pay_rate_id = pay_rate_id;
            this.assignment_start_date = assignment_start_date;
            this.assignment_end_date = assignment_end_date;
            this.quantity_worked = quantity_worked;
            this.amount_due = amount_due;
            this.complete_status = complete_status;
            this.comments = comments;

            List<TaskAssignmentItem> taskAssignmentItemList;
        }
        @Override
        public void onPreExecute(){
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            taskAssignmentDao=db.taskAssignmentDao();
            taskAssignment=new TaskAssignment();
            taskAssignment.setAmount_due(amount_due);
            taskAssignment.setAssignment_end_date(assignment_end_date);
            taskAssignment.setAssignment_id(assignment_id);
            taskAssignment.setAssignment_start_date(assignment_start_date);
            taskAssignment.setComments(comments);
            taskAssignment.setComplete_status(complete_status);
            taskAssignment.setPay_rate_id(pay_rate_id);
            taskAssignment.setPhase_id(phase_id);
            taskAssignment.setPlan_id(plan_id);
            taskAssignment.setQuantity_worked(quantity_worked);
            taskAssignment.setTask_id(task_id);
            taskAssignment.setResource_id(resource_id);
            saveTaskPayment(db);
        }

        private void saveTaskPayment(ShambaAppDB db){
            taskPaymentDao=db.taskPaymentDao();
            taskPayment=new TaskPayment();
            taskPayment.setTotal_amount(amount_due);
            taskPayment.setBalance_before(amount_due);
            taskPayment.setAmount_paid(0.0);
            taskPayment.setBalance_after(amount_due);
            taskPayment.setTask_assignment_id(assignment_id);
            taskPayment.setResource_id(resource_id);
            taskPayment.setProgram_id(plan_id);
            taskPayment.setTask_id(task_id);
            taskPayment.setPayment_due_date(end_date);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            taskAssignmentDao.insertTaskAssignment(taskAssignment);
            Log.d("Assignment record saved", "resource id: "+taskAssignment.getResource_id());

            taskPaymentDao.insertTaskPayment(taskPayment);
            Log.d("Payment record saved", "resource id: "+taskAssignment.getResource_id());

            return null;
        }

        @Override
        public void onPostExecute(Integer i){
        }
    }
}
