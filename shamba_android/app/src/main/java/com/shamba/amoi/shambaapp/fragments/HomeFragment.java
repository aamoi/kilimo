package com.shamba.amoi.shambaapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.shamba.amoi.shambaapp.fragments.product.ProductsFragment;
import com.shamba.amoi.shambaapp.fragments.labor.ResourcesFragment;
import com.shamba.amoi.shambaapp.fragments.power.PowerSourcesFragment;
import com.shamba.amoi.shambaapp.fragments.projects.PlantingProgrammesFragment;
import com.shamba.amoi.shambaapp.fragments.reports.ReportsFragment;
import com.shamba.amoi.shambaapp.models.assets.AssetItem;
import com.shamba.amoi.shambaapp.models.labor.PayRateItem;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.product.DistributorItem;
import com.shamba.amoi.shambaapp.models.product.ManufacturerItem;
import com.shamba.amoi.shambaapp.models.product.ProductCategoryItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.UnitOfMeasureItem;
import com.shamba.amoi.shambaapp.models.product.VendorItem;
import com.shamba.amoi.shambaapp.models.projects.LocationBlockItem;
import com.shamba.amoi.shambaapp.models.projects.LocationItem;
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
public class HomeFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
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

    /**
     * Get all setup data from server
     */
    private void getAllSetUpData(){

        try {
            AssetItem.getAllAssets(getActivity());
            new GetSetUpData().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getAllSetUpData();
        getActivity().setTitle(R.string.title_fragment_home);
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        return view;

    }

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
                R.id.fragment_placeholder_home,new ResourcesFragment());
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
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

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
            getAllProductCategoriesFromServer();
            getAllPhasesFromServer();
            getAllLocationBlockFromServer();
            getAllUnitOfMeasureFromServer();
            getAllVendorFromServer();
            getAllLocationFromServer();
            getAllManufacturerFromServer();
            getAllDistributorFromServer();
            getAllResourceFromServer();
            getAllPayRateFromServer();
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
         * Pools all product categories from server application!
         * @return
         */
        private List<ProductCategoryItem> getAllProductCategoriesFromServer(){

            List<ProductCategoryItem> productCategories=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"productCategory/","");

                Log.d("# product categories:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    ProductCategoryItem productCategoryItem=new ProductCategoryItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    int id=jsonObject.getInt("id");
                    productCategoryItem.setId(id);

                    String category_name=jsonObject.getString("category_name");
                    productCategoryItem.setCategory_name(category_name);

                    String details=jsonObject.getString("details");
                    productCategoryItem.setDetails(details);

                    productCategories.add(productCategoryItem);
                }

                ProductCategoryItem.staticProductCategoryItemList=productCategories;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return productCategories;
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
                LocationBlockItem.staticLocationBlockItemList=locationBlockItems;

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

        /**
         * Pools all vendor from server application!
         * @return
         */
        private List<VendorItem> getAllVendorFromServer(){

            List<VendorItem> vendorItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"vendor/","");

                Log.d("# of vendors pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    VendorItem vendorItem=new VendorItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    int id=jsonObject.getInt("id");
                    vendorItem.setId(id);
                    String vendor_name=jsonObject.getString("vendor_name");
                    vendorItem.setVendor_name(vendor_name);

                    String vendor_phone=jsonObject.getString("vendor_phone");
                    vendorItem.setVendor_phone(vendor_phone);

                    String county=jsonObject.getString("county");
                    vendorItem.setCounty(county);

                    String town=jsonObject.getString("town");
                    vendorItem.setTown(town);

                    String map=jsonObject.getString("map");
                    vendorItem.setMap(map);

                    String email=jsonObject.getString("email");
                    vendorItem.setEmail(email);

                    String directions=jsonObject.getString("directions");
                    vendorItem.setDirections(directions);

                    String details=jsonObject.getString("details");
                    vendorItem.setDetails(details);

                    vendorItems.add(vendorItem);
                }

                VendorItem.staticVendorItemList=vendorItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return vendorItems;
        }
        /**
         * Pools all locations from server application!
         * @return
         */
        private List<LocationItem> getAllLocationFromServer(){

            List<LocationItem> locationItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"location/","");

                Log.d("#locations pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    LocationItem locationItem=new LocationItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    locationItem.setId(id);

                    String location_name=jsonObject.getString("location_name");
                    locationItem.setLocation_name(location_name);

                    String location_details=jsonObject.getString("location_details");
                    locationItem.setLocation_details(location_details);

                    String county=jsonObject.getString("county");
                    locationItem.setCounty(county);

                    String town=jsonObject.getString("town");
                    locationItem.setTown(town);

                    String map=jsonObject.getString("map");
                    locationItem.setMap(map);

                    String directions=jsonObject.getString("directions");
                    locationItem.setDirections(directions);

                    locationItems.add(locationItem);
                }

                LocationItem.staticLocationItemList=locationItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return locationItems;
        }

        /**
         * Pools all distributor from server application!
         * @return
         */
        private List<DistributorItem> getAllDistributorFromServer(){

            List<DistributorItem> distributorItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"distributor/","");

                Log.d("#distributor pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    DistributorItem distributorItem=new DistributorItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    distributorItem.setId(id);

                    String distributor_name=jsonObject.getString("distributor_name");
                    distributorItem.setDistributor_name(distributor_name);

                    String phone=jsonObject.getString("phone");
                    distributorItem.setPhone(phone);

                    String email=jsonObject.getString("email");
                    distributorItem.setEmail(email);

                    String map=jsonObject.getString("map");
                    distributorItem.setMap(map);

                    String directions=jsonObject.getString("directions");
                    distributorItem.setDirections(directions);

                    String county=jsonObject.getString("county");
                    distributorItem.setCounty(county);

                    String town=jsonObject.getString("town");
                    distributorItem.setTown(town);

                    String details=jsonObject.getString("details");
                    distributorItem.setDetails(details);
                   distributorItems.add(distributorItem);
                }

                DistributorItem.staticDistributorItems=distributorItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return distributorItems;
        }

        /**
         * Pools all manufacturer from server application!
         * @return
         */
        private List<ManufacturerItem> getAllManufacturerFromServer(){

            List<ManufacturerItem> manufacturerItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"manufacturer/","");

                Log.d("#manufacturer pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    ManufacturerItem manufacturerItem=new ManufacturerItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    manufacturerItem.setId(id);

                    String manufacturer_name=jsonObject.getString("manufacturer_name");
                    manufacturerItem.setManufacturer_name(manufacturer_name);

                    String phone=jsonObject.getString("phone");
                    manufacturerItem.setPhone(phone);

                    String email=jsonObject.getString("email");
                    manufacturerItem.setEmail(email);

                    String map=jsonObject.getString("map");
                    manufacturerItem.setMap(map);

                    String directions=jsonObject.getString("directions");
                    manufacturerItem.setDirections(directions);

                    String county=jsonObject.getString("county");
                    manufacturerItem.setCounty(county);

                    String town=jsonObject.getString("town");
                    manufacturerItem.setTown(town);

                    String details=jsonObject.getString("details");
                    manufacturerItem.setDetails(details);

                    manufacturerItems.add(manufacturerItem);
                }

                ManufacturerItem.staticManufacturerItemList=manufacturerItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return manufacturerItems;
        }

        /**
         * Pools all resources from server application!
         * @return
         */
        private List<ResourceItem> getAllResourceFromServer(){

            List<ResourceItem> resourceItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"resource/","");

                Log.d("#resources pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    ResourceItem resourceItem=new ResourceItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    resourceItem.setId(id);

                    int default_pay_rate_id=jsonObject.getInt("default_pay_rate_id");
                    resourceItem.setDefault_pay_rate_id(default_pay_rate_id);

                    int location_id=jsonObject.getInt("location_id");
                    resourceItem.setLocation_id(location_id);

                    String resource_name=jsonObject.getString("resource_name");
                    resourceItem.setResource_name(resource_name);

                    String phone=jsonObject.getString("phone");
                    resourceItem.setPhone(phone);
                    Log.d("Reosurce phone",phone);


                    String skillset=jsonObject.getString("skillset");
                    resourceItem.setSkillset(skillset);

                    String resource_type=jsonObject.getString("resource_type");
                    resourceItem.setResource_type(resource_type);

                    String details=jsonObject.getString("details");
                    resourceItem.setDetails(details);

                    resourceItems.add(resourceItem);
                }

                ResourceItem.staticResourceItemList=resourceItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return resourceItems;
        }

        /**
         * Pools all PayRate from server application!
         * @return
         */
        private List<PayRateItem> getAllPayRateFromServer(){

            List<PayRateItem> payRateItems=new ArrayList<>();

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"payRate/","");

                Log.d("#Pay rates pooled:- ", String.valueOf(response.size()));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){
                    PayRateItem payRateItem=new PayRateItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    payRateItem.setId(id);

                    int uom_id=jsonObject.getInt("uom_id");
                    payRateItem.setUnit_price(uom_id);

                    String name=jsonObject.getString("name");
                    payRateItem.setName(name);

                    String details=jsonObject.getString("details");
                    payRateItem.setDetails(details);

                    double unit_price=jsonObject.getDouble("unit_price");
                    payRateItem.setUnit_price(unit_price);

                    payRateItems.add(payRateItem);
                }

                PayRateItem.staticPayRateItems=payRateItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return payRateItems;
        }
        @Override
        public void onPostExecute(Integer i) {
        }
    }
}
