package com.shamba.amoi.shambaapp.fragments.assets;

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

import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.assets.Asset;
import com.shamba.amoi.shambaapp.db.assets.AssetDao;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAssetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateAssetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAssetFragment extends BaseFragment {

    EditText edit_asset_manufacturer;
    EditText edit_asset_name;
    EditText edit_asset_measuringUnit;
    EditText edit_asset_unit_size;

    Button save_asset;
    Button cancel_asset;

     String  str_asset_manufacturer;
     String  str_asset_name;
     String  str_asset_measuringUnit;
     String  str_asset_unit_size;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreateAssetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateAssetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAssetFragment newInstance(String param1, String param2) {
        CreateAssetFragment fragment = new CreateAssetFragment();
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
        getActivity().setTitle("New Asset");
        View view =inflater.inflate(R.layout.fragment_create_asset, container, false);

        edit_asset_manufacturer=(EditText)view.findViewById(R.id.edit_asset_manufacturer);
        edit_asset_name=(EditText)view.findViewById(R.id.edit_asset_name);
        edit_asset_measuringUnit=(EditText)view.findViewById(R.id.edit_asset_measuringUnit);
        edit_asset_unit_size=(EditText)view.findViewById(R.id.edit_asset_unit_size);

        save_asset=(Button)view.findViewById(R.id.btn_accept_asset_creation);
        save_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_asset_manufacturer=edit_asset_manufacturer.getText().toString();
                str_asset_measuringUnit=edit_asset_measuringUnit.getText().toString();
                str_asset_name=edit_asset_name.getText().toString();
                str_asset_unit_size=edit_asset_unit_size.getText().toString();

                SaveAsset saveAsset=new SaveAsset(str_asset_manufacturer,str_asset_name,
                        str_asset_measuringUnit,str_asset_unit_size);
                saveAsset.execute();

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home,new AssetFragment());
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

    class SaveAsset extends AsyncTask<Void, Void, Integer> {
        public final String manufacturer;
        public final String name;
        public  final String unit_of_measure;
        public final String unit_size;

        Asset asset;
        AssetDao assetDao;

        public SaveAsset(String manufacturer, String name,String unit_of_measure, String unit_size){
            this.manufacturer = manufacturer;
            this.name = name;
            this.unit_of_measure = unit_of_measure;
            this.unit_size = unit_size;
        }
        @Override
        public void onPreExecute(){
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            assetDao=db.assetDao();
            asset=new Asset();
            asset.setManufacturer(manufacturer);
            asset.setAsset_name(name);
            asset.setUnit_of_measure(unit_of_measure);
            asset.setCapacity(unit_size);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            assetDao.insertAsset(asset);
            Log.d("record saved", "asset name: "+name);
            return null;
        }

        @Override
        public void onPostExecute(Integer i){
        }
    }
}
