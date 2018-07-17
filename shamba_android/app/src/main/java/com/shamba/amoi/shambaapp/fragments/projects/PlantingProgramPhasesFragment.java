package com.shamba.amoi.shambaapp.fragments.projects;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * Activities that contain this fragment must implement the
 * {@link PlantingProgramPhasesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlantingProgramPhasesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantingProgramPhasesFragment extends BaseFragment {

    Button seed_bed;
    Button transplanting;
    Button crop_maintainance;
    Button harvesting;
    Button post_harvesting;

    private static final String ARG_program_id = "key_program_id";
    private static final String ARG_program_name = "key_program_name";
    private static final String ARG_crop_name = "key_crop_name";
    private static final String ARG_start_date = "key_start_date";

    public int program_id;
    public  String program_name;
    public  String crop_name;
    public String start_date;


    private OnFragmentInteractionListener mListener;

    public PlantingProgramPhasesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment PlantingProgramPhasesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlantingProgramPhasesFragment newInstance(int  program_id, String program_name,
                                                            String crop_name, String start_date) {
        PlantingProgramPhasesFragment fragment = new PlantingProgramPhasesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_program_id, program_id);
        args.putString(ARG_program_name, program_name);
        args.putString(ARG_crop_name, crop_name);
        args.putString(ARG_start_date, start_date);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            program_id = getArguments().getInt(ARG_program_id);
            crop_name = getArguments().getString(ARG_crop_name);
            program_name = getArguments().getString(ARG_program_name);
            start_date = getArguments().getString(ARG_start_date);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Planting phases");
        // Inflate the submit_form_details for this fragment
        View view=inflater.inflate(R.layout.fragment_planting_program_phases, container,
                false);
        //setup profile details
        TextView txt_crop_name=(TextView)view.findViewById(R.id.product_name);
        txt_crop_name.setText(crop_name);

        TextView txt_program_name=(TextView)view.findViewById(R.id.planting_name);
        txt_program_name.setText(program_name);

        TextView txt_start_date=(TextView)view.findViewById(R.id.planting_ref);
        txt_start_date.setText(start_date);

        seed_bed=(Button)view.findViewById(R.id.btn_seedbed_preparation);
        seed_bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseFragment.changeFragment((AppCompatActivity)getActivity(),
                        R.id.fragment_placeholder_home, new TaskListFragment());
//                FragmentTransaction ft=getActivity().getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment_placeholder_projects,fragment);
//                ft.addToBackStack(null);
//                ft.commit();
            }
        });
        transplanting=(Button)view.findViewById(R.id.btn_transplanting);
        crop_maintainance=(Button)view.findViewById(R.id.btn_crop_maintainance);
        harvesting=(Button)view.findViewById(R.id.btn_harvesting);

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
