package com.shamba.amoi.shambaapp.fragments.labor;

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
import android.widget.Spinner;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.HumanResource;
import com.shamba.amoi.shambaapp.db.labor.HumanResourceDao;
import com.shamba.amoi.shambaapp.models.labor.HumanResourceItem;
import com.shamba.amoi.shambaapp.models.labor.PaymentRateItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SharedUtilities;
import com.shamba.amoi.shambaapp.shareResources.SpinnerUtility;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateHumanResourceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateHumanResourceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateHumanResourceFragment extends BaseFragment {
    private EditText edit_resource_name;
    private Spinner  spn_resource_type;
    private EditText edit_resource_joining_date;
    private EditText edit_resource_main_skillset;
    private Spinner  spn_resource_contract;
    private Spinner  spn_resource_pay_rate;
    private EditText  edit_comments_on_resource;
    
    private Button btn_add_hr;

    private String resource_name;
    private String resource_type;
    private String resource_joining_date;
    private String resource_main_skillset;
    private String resource_contract;
    private String resource_pay_rate;
    private String comments_on_resource;

    private OnFragmentInteractionListener mListener;

    public CreateHumanResourceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateHumanResourceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateHumanResourceFragment newInstance(String param1, String param2) {
        CreateHumanResourceFragment fragment = new CreateHumanResourceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("New human Resource ");

        View view = inflater.inflate(R.layout.fragment_create_human_resource, container,
                false);
        getViewByIds(view);

        btn_add_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 resource_name=edit_resource_name.getText().toString();
                String resource_id=resource_name+"-"+SharedUtilities.getTimeStamp();
                resource_type=spn_resource_type.getSelectedItem().toString();
                 resource_joining_date=edit_resource_joining_date.getText().toString();
                 resource_main_skillset=edit_resource_main_skillset.getText().toString();
                 resource_contract=spn_resource_contract.getSelectedItem().toString();
                 resource_pay_rate=spn_resource_pay_rate.getSelectedItem().toString();
                 comments_on_resource=edit_comments_on_resource.getText().toString();

                     new SaveHumanResource(resource_id, resource_name, resource_type, resource_joining_date,
                             resource_main_skillset, resource_contract,
                             resource_pay_rate, comments_on_resource).execute();

                     BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                             R.id.fragment_placeholder_home, new HumanResourcesFragment());
                 }
            //}
        });
        
        return view;
    }

    private void getViewByIds(View view){
        edit_resource_name=(EditText)view.findViewById(R.id.edit_resource_name);

        spn_resource_type=(Spinner) view.findViewById(R.id.spn_resource_type);
        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_resource_type,
                BaseFragment.getResourceTypes());

        edit_resource_joining_date=(EditText)view.findViewById(R.id.edit_resource_joining_date);
        new DatePickerUtility(edit_resource_joining_date);

        edit_resource_main_skillset=(EditText)view.findViewById(R.id.edit_resource_main_skillset);

        spn_resource_contract=(Spinner) view.findViewById(R.id.spn_resource_contract);
        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_resource_contract,
                BaseFragment.getResourceContractTypes());

        spn_resource_pay_rate=(Spinner) view.findViewById(R.id.spn_resource_pay_rate);
        SpinnerUtility.setDynamicSpinner(view.getContext(),spn_resource_pay_rate,
                SharedUtilities.getHashMapStringKeySetList(PaymentRateItem.getPaymentRates().keySet()));

        edit_comments_on_resource=(EditText)view.findViewById(R.id.edit_comments_on_resource);
        btn_add_hr=(Button)view.findViewById(R.id.btn_add_hr);
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

    class SaveHumanResource extends AsyncTask<Void, Void, Integer> {
        public String resource_id;
        public  String resource_name;
        public  String resource_type;
        public  String resource_joining_date;
        public  String resource_main_skillset;
        public  String resource_contract;
        public  String resource_pay_rate;
        public  String comments_on_resource;
        
        HumanResource hr;
        HumanResourceDao hr_dao;

        public SaveHumanResource(  String resource_id ,String resource_name, String resource_type ,
                                   String resource_joining_date , String resource_main_skillset ,
                                   String resource_contract , String resource_pay_rate ,
                                   String comments_on_resource ){
            this.resource_id = resource_id;
            this.resource_name = resource_name;
            this.resource_type = resource_type;
            this.resource_joining_date = resource_joining_date;
            this.resource_main_skillset = resource_main_skillset;
            this.resource_contract = resource_contract;
            this.resource_pay_rate = resource_pay_rate;
            this.comments_on_resource = comments_on_resource;

            List<HumanResourceItem> hr_items;
        }
        @Override
        public void onPreExecute(){
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            hr_dao=db.humanResourceDao();
            hr=new HumanResource();
            hr.setComments_on_resource(comments_on_resource);
            hr.setResource_contract(resource_contract);
            hr.setResource_id(resource_id);
            hr.setResource_joining_date(resource_joining_date);
            hr.setResource_main_skillset(resource_main_skillset);
            hr.setResource_name(resource_name);
            hr.setResource_pay_rate(resource_pay_rate);
            hr.setResource_type(resource_type);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            hr_dao.insertHumanResource(hr);
            Log.d("Record saved", "resource name: "+resource_name);
            return null;
        }

        @Override
        public void onPostExecute(Integer i){
        }
    }
}
