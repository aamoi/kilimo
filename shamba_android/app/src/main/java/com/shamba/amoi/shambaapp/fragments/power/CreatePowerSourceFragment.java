package com.shamba.amoi.shambaapp.fragments.power;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.power.PowerSource;
import com.shamba.amoi.shambaapp.db.power.PowerSourceDao;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreatePowerSourceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreatePowerSourceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePowerSourceFragment extends BaseFragment {
    EditText edit_power_source_name;
    EditText edit_power_source_unitOfMeasure;
    EditText edit_power_source_use;
    EditText edit_comments_on_power_source;
    Button btn_add_power_source;

    String str_power_source_name;
    String str_power_source_unitOfMeasure;
    String str_power_source_use;
    String str_comments_on_power_source;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreatePowerSourceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatePowerSourceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePowerSourceFragment newInstance(String param1, String param2) {
        CreatePowerSourceFragment fragment = new CreatePowerSourceFragment();
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
        getActivity().setTitle("New Power Source");
        View view=inflater.inflate(R.layout.fragment_create_power_source, container,
                false);
        edit_comments_on_power_source=(EditText)view.findViewById(R.id.edit_comments_on_power_source);
        edit_power_source_name=(EditText)view.findViewById(R.id.edit_power_source_name);
        edit_power_source_unitOfMeasure=(EditText)view.findViewById(R.id.edit_power_source_unitOfMeasure);
        edit_power_source_use=(EditText)view.findViewById(R.id.edit_power_source_use);

        btn_add_power_source=(Button)view.findViewById(R.id.btn_add_power_source);
        btn_add_power_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_comments_on_power_source=edit_comments_on_power_source.getText().toString();
                str_power_source_name=edit_power_source_name.getText().toString();
                str_power_source_unitOfMeasure=edit_power_source_unitOfMeasure.getText().toString();
                str_power_source_use=edit_power_source_use.getText().toString();

               new  SavePowerResource(str_power_source_name,str_power_source_unitOfMeasure,
                       str_power_source_use,str_comments_on_power_source).execute();

//                Fragment ps=new PowerSourcesFragment();
//                FragmentTransaction ft=getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_placeholder_power,ps);
//                ft.commit();
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

    class SavePowerResource extends AsyncTask<Void, Void, Integer> {
        public String power_source_name;
        public String power_source_unitOfMeasure;
        public String power_source_use;
        public String comments_on_power_source;

        PowerSource powerSource;
        PowerSourceDao powerSourceDao;

        public SavePowerResource(String power_source_name, String power_source_unitOfMeasure,
                                 String power_source_use, String comments_on_power_source){

            this.power_source_name = power_source_name;
            this.power_source_unitOfMeasure = power_source_unitOfMeasure;
            this.power_source_use = power_source_use;
            this.comments_on_power_source = comments_on_power_source;
        }
        @Override
        public void onPreExecute(){
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            powerSourceDao=db.powerSourceDao();
            powerSource=new PowerSource();
            powerSource.setComments_on_power_source(comments_on_power_source);
            powerSource.setPower_source_name(power_source_name);
            powerSource.setPower_source_unitOfMeasure(power_source_unitOfMeasure);
            powerSource.setPower_source_use(power_source_use);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            powerSourceDao.insertPowerSource(powerSource);
            Log.d("record saved", "Power Source name "+power_source_name);
            return null;
        }

        @Override
        public void onPostExecute(Integer i){
        }
    }
}
