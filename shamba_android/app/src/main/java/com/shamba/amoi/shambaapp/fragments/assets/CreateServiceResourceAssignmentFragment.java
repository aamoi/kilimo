package com.shamba.amoi.shambaapp.fragments.assets;

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
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;
import com.shamba.amoi.shambaapp.fragments.labor.TaskAssignmentListFragment;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;
import com.shamba.amoi.shambaapp.models.labor.PayRateItem;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.labor.TaskAssignmentItem;
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

public class CreateServiceResourceAssignmentFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spn_pay_rate;
    Spinner spn_resource;

    EditText edit_start_date;
    EditText edit_end_date;
    EditText edit_total_work;
    EditText edit_total_payment;
    Spinner spn_complete_status;
    EditText edit_comments;

    Button btn_submit_service_assignment;
    String pay_rate;
    String start_date;
    String end_date;
    double total_work;
    double total_payment;
    String complete_status;
    String comments;
    List<PayRateItem> payRateItemList;
    List<ResourceItem> resourceItemList;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreateServiceResourceAssignmentFragment() {
    }

    public static CreateServiceResourceAssignmentFragment newInstance(String param1, String param2) {
        CreateServiceResourceAssignmentFragment fragment = new CreateServiceResourceAssignmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_service_resource_assignment, container,
                false);
        getViewsByIds(view);

        btn_submit_service_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resource_name = spn_resource.getSelectedItem().toString();
                ResourceItem resourceItem = ResourceItem.getResourceByName(getActivity(),
                        resource_name);
                ResourceItem.selectedResourceItem = resourceItem;
                int resource_id = resourceItem.getId();

                int service_id = AssetServicingItem.selectedAssetServicingItem.getId();
                pay_rate = spn_pay_rate.getSelectedItem().toString();
                int pay_rate_id = PayRateItem.getPayRateItemByName(PayRateItem.staticPayRateItems,
                        pay_rate).getId();

                start_date = edit_start_date.getText().toString();
                end_date = edit_end_date.getText().toString();
                total_work = Double.parseDouble(edit_total_work.getText().toString());
                total_payment = Double.parseDouble(edit_total_payment.getText().toString());
                complete_status = spn_complete_status.getSelectedItem().toString();
                comments = edit_comments.getText().toString();

                new SaveResourceAssignment(resource_id, service_id, pay_rate_id, start_date, end_date,
                        total_work, total_payment, complete_status, comments).execute();

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new TaskAssignmentListFragment());

            }
        });
        return view;
    }

    private void getViewsByIds(final View view) {
        spn_resource = (Spinner) view.findViewById(R.id.spn_resource);
        spn_pay_rate = (Spinner) view.findViewById(R.id.spn_pay_rate);

        resourceItemList = ResourceItem.staticResourceItemList;
        List<String> resources = new ArrayList<>();
        for (int i = 0; i < resourceItemList.size(); ++i) {
            resources.add(resourceItemList.get(i).getResource_name());
        }
        spn_resource = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_resource, resources);

        payRateItemList = PayRateItem.staticPayRateItems;
        List<String> pay_rates = new ArrayList<>();
        for (int i = 0; i < payRateItemList.size(); ++i) {
            pay_rates.add(payRateItemList.get(i).getName());
        }
        spn_pay_rate = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_pay_rate, pay_rates);

        edit_start_date = SharedUtilities.getEditTextById(view, R.id.edit_start_date);
        new DatePickerUtility(edit_start_date);

        edit_end_date = SharedUtilities.getEditTextById(view, R.id.edit_end_date);
        new DatePickerUtility(edit_end_date);

        edit_total_work = SharedUtilities.getEditTextById(view, R.id.edit_total_work);
        edit_total_payment = SharedUtilities.getEditTextById(view, R.id.edit_total_payment);
        spn_complete_status = SharedUtilities.getSpinnerById(view, R.id.spn_complete_status);
        edit_comments = SharedUtilities.getEditTextById(view, R.id.edit_comments);

        btn_submit_service_assignment = SharedUtilities.getButtonById(view,
                R.id.btn_submit_service_assignment);
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
     *
     */
    class SaveResourceAssignment extends AsyncTask<Void, Void, Integer> {
        public int resource_id;
        public int task_id;
        public int pay_rate_id;
        public String start_date;
        public String end_date;
        public double quantity_worked;
        public double amount_due;
        public String complete_status;
        public String comments;

        JSONObject request_object = new JSONObject();
        JSONObject response_object = new JSONObject();

        TaskAssignmentItem taskAssignmentItem = new TaskAssignmentItem();
        TaskAssignment taskAssignment;
        TaskAssignmentDao taskAssignmentDao;

        int id;
        int success = 0;

        public SaveResourceAssignment(int resource_id, int task_id, int pay_rate_id, String start_date,
                                      String end_date, double quantity_worked, double amount_due,
                                      String complete_status, String comments) {
            this.resource_id = resource_id;
            this.task_id = task_id;
            this.pay_rate_id = pay_rate_id;
            this.start_date = start_date;
            this.end_date = end_date;
            this.quantity_worked = quantity_worked;
            this.amount_due = amount_due;
            this.complete_status = complete_status;
            this.comments = comments;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            taskAssignment = new TaskAssignment();
            taskAssignmentDao = db.taskAssignmentDao();

            try {
                request_object.put("resource_id", resource_id);
                request_object.put("service_id", task_id);
                request_object.put("pay_rate_id", pay_rate_id);
                request_object.put("assignment_start_date", start_date);
                request_object.put("assignment_end_date", end_date);
                request_object.put("quantity_worked", quantity_worked);
                request_object.put("amount_due", amount_due);
                request_object.put("complete_status", complete_status);
                request_object.put("comments", comments);

                Log.d("Assignment request...", request_object.toString());

            } catch (JSONException e) {
                Log.d("Error preparing request", e.getMessage());
                e.printStackTrace();
            }
            try {
                taskAssignment.setResource_id(resource_id);
//                taskAssignment.setTask_id(task_id);
                taskAssignment.setService_id(task_id);
                taskAssignment.setPay_rate_id(pay_rate_id);
                taskAssignment.setAssignment_start_date(start_date);
                taskAssignment.setAssignment_end_date(end_date);
                taskAssignment.setQuantity_worked(quantity_worked);
                taskAssignment.setAmount_due(amount_due);
                taskAssignment.setComplete_status(complete_status);
                taskAssignment.setComments(comments);

                Log.d("Assign. db request...", taskAssignment.toString());
            } catch (Exception e) {
                Log.d("Error creating request", e.getMessage());
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            /**
             * Saving Assignment record to the server!
             */
            try {
                response_object = CommonHelper.sendPostRequestWithJsonResponse(BuildConfig.SERVER_URL,
                        "createTaskAssignment/", request_object.toString());

                Log.d("Assignment response...", response_object.toString());

                id = response_object.getInt("id");

            } catch (IOException e) {
                Log.d("Error posting request", e.getMessage());
                e.printStackTrace();
            } catch (JSONException jsonException) {
                Log.d("Error getting response", jsonException.getMessage());
                jsonException.printStackTrace();
            }

            /**
             * Saving assignment record to local db only if the record is send to the server!
             */
            if (id > 0) {
                try {
                    taskAssignment.setId(id);
                    taskAssignmentDao.insertTaskAssignment(taskAssignment);
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
