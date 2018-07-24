package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.net.Uri;
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
import com.shamba.amoi.shambaapp.models.inventory.InventoryUtilizationItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingPhaseItem;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;
import com.shamba.amoi.shambaapp.shareResources.SpinnerUtility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InventoryUtilizationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InventoryUtilizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryUtilizationFragment extends BaseFragment {
    String TAG = getClass().getSimpleName().toString() + "|";

    Spinner spn_plan_name;
    Spinner spn_task_phase;
    Spinner spn_plan_task;
    EditText edit_inventory_quantity;
    EditText edit_inventory_utilization_date;
    Spinner spn_utilization_status;
    EditText edit_comments;

    String str_plan_name;
    String str_task_phase;
    String str_plan_task;
    String str_inventory_quantity;
    String str_inventory_utilization_date;
    String str_utilization_status;
    String str_comments;
    Button btn_submit_stock_utilization;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InventoryUtilizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InventoryUtilizationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryUtilizationFragment newInstance(String param1, String param2) {
        InventoryUtilizationFragment fragment = new InventoryUtilizationFragment();
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

        String stock_item=null;

//        String stock_item="Utilize "+BaseFragment.productItem.getProduct_name()+"("+
//                BaseFragment.productStockItem.getStock_quantity()+
//                BaseFragment.productItem.getUnit_of_measure()+" on "+
//                BaseFragment.productStockItem.getStock_date().substring(0,5)+")";

        getActivity().setTitle(stock_item);

        View view = inflater.inflate(R.layout.fragment_inventory_utilization, container, false);
//        getFragmentView(inflater, container, R.submit_form_details.fragment_inventory_utilization);
        getViews(view);

        btn_submit_stock_utilization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String utilization_id = "UTIL-" + SharedUtilities.getTimeStamp();

                Log.d(TAG, "about to submit utilization record id:" + utilization_id);

                if(validateMandatorySpinnerFields(spn_plan_name,spn_task_phase,spn_plan_task)){
                    str_plan_name = spn_plan_name.getSelectedItem().toString();
                    str_task_phase = spn_task_phase.getSelectedItem().toString();
                    str_plan_task = spn_plan_task.getSelectedItem().toString();
                }

                str_inventory_quantity = edit_inventory_quantity.getText().toString();
                str_inventory_utilization_date = edit_inventory_utilization_date.getText().toString();
                str_utilization_status = spn_utilization_status.getSelectedItem().toString();
                str_comments = edit_comments.getText().toString();

//                Log.d(TAG, String.valueOf(InventoryUtilizationItem.getInventoryUtilizationItems(getActivity()).size()));

                if (validateMandatoryEditTextFields(edit_inventory_quantity, edit_comments, edit_inventory_utilization_date)) {
                    if (InventoryUtilizationItem.saveInventoryUtilization(getActivity(), utilization_id,
                            str_plan_name, str_task_phase, str_plan_task, str_inventory_quantity,
                            str_inventory_utilization_date, str_utilization_status, str_comments)) {
                        Log.d(TAG, "Saved successfully ");

                        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                                R.id.fragment_placeholder_home,
                                new ProductStockFragment());
                    } else {
                        Log.d(TAG, "Inventory utilization  not saved.");
                    }
                } else {
                    Log.d(TAG, "Inventory utilization not saved, mandatory field(s) empty!");

                }
            }
        });

        return view;
    }

    private void getViews(View view) {
        spn_plan_name = SharedUtilities.getSpinnerById(view, R.id.spn_plan_name);
        SpinnerUtility.setDynamicSpinner(view.getContext(), spn_plan_name, SharedUtilities.getHashMapStringKeySetList(
                PlantingProgramItem.getPlantingProgramsHashMap(getActivity()).keySet()));

        spn_task_phase = SharedUtilities.getSpinnerById(view, R.id.spn_task_phase);
        SpinnerUtility.setDynamicSpinner(view.getContext(), spn_task_phase, SharedUtilities.getHashMapStringKeySetList(
                PlantingPhaseItem.getPlantingPhaseItemsHashMap().keySet()));

        spn_plan_task = SharedUtilities.getSpinnerById(view, R.id.spn_plan_task);

        SpinnerUtility.setDynamicSpinner(view.getContext(), spn_plan_task,
                SharedUtilities.getHashMapKeyList(TaskItem.getAllTaskHashMap(getActivity())));

        edit_inventory_quantity = SharedUtilities.getEditTextById(view, R.id.edit_inventory_quantity);

        edit_inventory_utilization_date = SharedUtilities.getEditTextById(view, R.id.edit_inventory_utilization_date);
        new DatePickerUtility(edit_inventory_utilization_date);

        spn_utilization_status = SharedUtilities.getSpinnerById(view, R.id.spn_utilization_status);
        edit_comments = SharedUtilities.getEditTextById(view, R.id.edit_comments);

        btn_submit_stock_utilization = SharedUtilities.getButtonById(view, R.id.btn_submit_stock_utilization);
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
}
