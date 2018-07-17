package com.shamba.amoi.shambaapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.fragments.assets.AssetFragment;
import com.shamba.amoi.shambaapp.fragments.inventory.ProductsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.HumanResourcesFragment;
import com.shamba.amoi.shambaapp.fragments.power.PowerSourcesFragment;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingProgrammesFragment;
import com.shamba.amoi.shambaapp.fragments.reports.ReportsFragment;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        getActivity().setTitle(R.string.title_fragment_home);
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        return view;

    }

    /**
     * FragmHome
     * @param view
     */

    public void onClickProjects(View view){
     BaseFragment.changeFragment((AppCompatActivity) getActivity(),
             R.id.fragment_placeholder_home,new PlantingProgrammesFragment());
    }

    public void onClickInventory(View view) {
        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new ProductsFragment());
    }

    public void onClickLabor(View view) {
        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new HumanResourcesFragment());
    }

    public void onClickPowerButton(View view) {
        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new PowerSourcesFragment());
    }

    public void onClickAssets(View view) {
        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new AssetFragment());
    }

    public void onClickReports(View view) {
        BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                R.id.fragment_placeholder_home,new ReportsFragment());

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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
//        mListener = null;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
