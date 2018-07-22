package com.shamba.amoi.shambaapp.fragments;

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

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.fragments.assets.AssetFragment;
import com.shamba.amoi.shambaapp.fragments.inventory.ProductsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.HumanResourcesFragment;
import com.shamba.amoi.shambaapp.fragments.power.PowerSourcesFragment;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingProgrammesFragment;
import com.shamba.amoi.shambaapp.fragments.reports.ReportsFragment;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.UnitOfMeasureItem;
import com.shamba.amoi.shambaapp.models.projects.LocationBlockItem;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

        try {
            new GetSetUpData().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

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


    private void loadSetUpData(){

    }
    /**
     * Get setup data from server and save in the android local db.
     */
    class GetSetUpData extends AsyncTask<Void, Void, Integer> {

        public GetSetUpData() {
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();

        }

        @Override
        protected Integer doInBackground(Void... voids) {
            getAllProductsFromServer();
            getAllPhasesFromServer();
            getAllLocationBlockFromServer();
            getAllUnitOfMeasureFromServer();

            return 1;
        }

        /**
         * Pools all products from server application!
         * @return
         */
        private List<ProductItem> getAllProductsFromServer(){

            List<ProductItem> productItemList=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"product/","");

                Log.d("# of products pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    ProductItem productItem=new ProductItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    int id=jsonObject.getInt("id");
                    productItem.setId(id);
                    String product_name=jsonObject.getString("product_name");
                    productItem.setProduct_name(product_name);
                    int category_id=jsonObject.getInt("category_id");
                    productItem.setCategory_id(category_id);
                    int uom_id=jsonObject.getInt("uom_id");
                    productItem.setUom_id(uom_id);
                    boolean is_asset= jsonObject.getBoolean("_asset");
                    productItem.setIs_asset(is_asset);
                    boolean is_fuel= jsonObject.getBoolean("_fuel");
                    productItem.setIs_fuel(is_fuel);
                    productItemList.add(productItem);
                    }

                ProductItem.staticProductItemList=productItemList;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return productItemList;
        }
        /**
         * Pools all projects phases from server application!
         * @return
         */
        private List<PhaseItem> getAllPhasesFromServer(){

            List<PhaseItem> phaseItemList=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"phase/","");

                Log.d("# of phases pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    PhaseItem phaseItem=new PhaseItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    int id=jsonObject.getInt("id");
                    phaseItem.setId(id);

                    String phase_name=jsonObject.getString("phase_name");
                    phaseItem.setPhase_name(phase_name);

                    String phase_details=jsonObject.getString("phase_details");
                    phaseItem.setPhase_details(phase_details);

                    boolean is_planting_phase=jsonObject.getBoolean("_planting_phase");
                    phaseItem.setIs_planting_phase(is_planting_phase);

                    boolean is_poultry_phase=jsonObject.getBoolean("_poultry_phase");
                    phaseItem.setIs_poultry_phase(is_poultry_phase);

                    boolean is_fishing_phase=jsonObject.getBoolean("_fishing_phase");
                    phaseItem.setIs_fishing_phase(is_fishing_phase);

                    boolean is_dairy_phase=jsonObject.getBoolean("_dairy_phase");
                    phaseItem.setIs_dairy_phase(is_dairy_phase);

                    phaseItemList.add(phaseItem);
                }

                PhaseItem.staticPhaseItems=phaseItemList;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return phaseItemList;
        }

        /**
         * Pools all location blocks phases from server application!
         * @return
         */
        private List<LocationBlockItem> getAllLocationBlockFromServer(){

            List<LocationBlockItem> locationBlockItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"locationBlock/","");

                Log.d("location blocks pooled-", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){

                    LocationBlockItem locationItem=new LocationBlockItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    locationItem.setId(id);

                    int location_id=jsonObject.getInt("location_id");
                    locationItem.setLocation_id(location_id);

                    int block_id=jsonObject.getInt("block_id");
                    locationItem.setBlock_id(block_id);

                    String details=jsonObject.getString("details");
                    locationItem.setDetails(details);

                    String location_block_name=jsonObject.getString("location_block_name");
                    locationItem.setLocation_block_name(location_block_name);

                    double acreage=jsonObject.getDouble("acreage");
                    locationItem.setAcreage(acreage);

                    locationBlockItems.add(locationItem);
                }
                LocationBlockItem.staticLocationBlockList=locationBlockItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return locationBlockItems;
        }

        /**
         * Pools all unitOfMeasure from server application!
         * @return
         */
        private List<UnitOfMeasureItem> getAllUnitOfMeasureFromServer(){

            List<UnitOfMeasureItem> unitOfMeasureItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"unitOfMeasure/","");

                Log.d("UnitOfMeasures pooled-", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    UnitOfMeasureItem unitOfMeasureItem=new UnitOfMeasureItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    unitOfMeasureItem.setId(id);

                    String uom_name=jsonObject.getString("uom_name");
                    unitOfMeasureItem.setUom_name(uom_name);

                    String details=jsonObject.getString("details");
                    unitOfMeasureItem.setDetails(details);

                    String symbol=jsonObject.getString("symbol");
                    unitOfMeasureItem.setSymbol(symbol);

                    unitOfMeasureItems.add(unitOfMeasureItem);
                }
                UnitOfMeasureItem.staticUnitOfMeasureItemList=unitOfMeasureItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return unitOfMeasureItems;
        }

        @Override
        public void onPostExecute(Integer i) {
        }
    }
}
