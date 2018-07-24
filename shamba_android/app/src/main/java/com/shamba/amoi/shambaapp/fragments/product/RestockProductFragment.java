package com.shamba.amoi.shambaapp.fragments.product;

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
import com.shamba.amoi.shambaapp.db.product.ProductStock;
import com.shamba.amoi.shambaapp.db.product.ProductStockDao;
import com.shamba.amoi.shambaapp.models.product.DistributorItem;
import com.shamba.amoi.shambaapp.models.product.ManufacturerItem;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.VendorItem;
import com.shamba.amoi.shambaapp.models.projects.LocationItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;
import com.shamba.amoi.shambaapp.shareResources.DatePickerUtility;
import com.shamba.amoi.shambaapp.shareResources.SpinnerUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestockProductFragment extends BaseFragment {
    Spinner spn_location;
    Spinner spn_vendor;
    Spinner spn_distributor;
    Spinner spn_manufacturer;
    EditText edit_quantity;
    EditText edit_total_price;
    EditText edit_purchase_date;
    EditText edit_purchase_details;
    EditText edit_mpesa_txn_number;
    EditText edit_receipt_upload;

    Button submit;

    int vendor_id;
    int distributor_id;
    int manufacturer_id;
    double purchase_quantity;
    double purchase_price;
    String purchase_details;
    String purchase_date;
    int location_id;
    String mpesa_txn_number;
    String receipt_upload;

    ProductItem productItem;
    private OnFragmentInteractionListener mListener;

    public RestockProductFragment() {
    }

    public static RestockProductFragment newInstance() {
        RestockProductFragment fragment = new RestockProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productItem = ProductItem.selectedProductItem;
        getActivity().setTitle("Add " + productItem.getProduct_name() + " stock");
        View view = inflater.inflate(R.layout.fragment_restock_product, container, false);

        getViewsById(view);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String location = spn_location.getSelectedItem().toString();
                location_id = LocationItem.getLocationItemByName(LocationItem.
                        staticLocationItemList, location).getId();

                String vendor = spn_vendor.getSelectedItem().toString();
                vendor_id = VendorItem.getProductItemByName(VendorItem.
                        staticVendorItemList, vendor).getId();

                String distributor = spn_distributor.getSelectedItem().toString();
                distributor_id = DistributorItem.getDistributorItemByName(DistributorItem.
                        staticDistributorItems, distributor).getId();

                String manufacturer = spn_manufacturer.getSelectedItem().toString();
                manufacturer_id = ManufacturerItem.getManufacturerItemByName(ManufacturerItem.
                        staticManufacturerItemList, manufacturer).getId();

                int product_id = productItem.getId();
                purchase_price = Double.parseDouble(edit_total_price.getText().toString());
                purchase_quantity = Double.parseDouble(edit_quantity.getText().toString());
                purchase_details = edit_purchase_details.getText().toString();
                purchase_date = edit_purchase_date.getText().toString();
                mpesa_txn_number = edit_mpesa_txn_number.getText().toString();
                receipt_upload = edit_receipt_upload.getText().toString();

                SaveStockToDB save_stock = new SaveStockToDB(product_id, vendor_id, distributor_id,
                        manufacturer_id, purchase_quantity, purchase_price, purchase_details,
                        purchase_date, location_id, mpesa_txn_number, receipt_upload);
                try {
                    save_stock.execute().get();
                } catch (Exception e) {
                }

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new ProductStockFragment());
            }
        });
        return view;
    }

    private void getViewsById(View view) {

        spn_location = (Spinner) view.findViewById(R.id.spn_location);
        List<String> locations = new ArrayList<>();
        List<LocationItem> locationItems = LocationItem.staticLocationItemList;
        for (int i = 0; i < locationItems.size(); ++i) {
            locations.add(locationItems.get(i).getLocation_name());
        }
        spn_location = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_location, locations);

        spn_vendor = (Spinner) view.findViewById(R.id.spn_vendor);
        List<String> vendors = new ArrayList<>();
        List<VendorItem> vendorItems = VendorItem.staticVendorItemList;
        for (int i = 0; i < vendorItems.size(); ++i) {
            vendors.add(vendorItems.get(i).getVendor_name());
        }
        spn_vendor = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_vendor, vendors);

        spn_distributor = (Spinner) view.findViewById(R.id.spn_distributor);
        List<String> distributors = new ArrayList<>();
        List<DistributorItem> distributorItems = DistributorItem.staticDistributorItems;
        for (int i = 0; i < distributorItems.size(); ++i) {
            distributors.add(distributorItems.get(i).getDistributor_name());
        }
        spn_distributor = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_distributor, distributors);

        spn_manufacturer = (Spinner) view.findViewById(R.id.spn_manufacturer);
        List<String> manufacturers = new ArrayList<>();
        List<ManufacturerItem> manufacturerItems = ManufacturerItem.staticManufacturerItemList;
        for (int i = 0; i < (manufacturerItems != null ? manufacturerItems.size() : 0); ++i) {
            manufacturers.add(manufacturerItems.get(i).getManufacturer_name());
        }
        spn_manufacturer = SpinnerUtility.setDynamicSpinner(view.getContext(),
                spn_manufacturer, manufacturers);

        edit_quantity = (EditText) view.findViewById(R.id.edit_quantity);
        edit_total_price = (EditText) view.findViewById(R.id.edit_total_price);
        edit_purchase_details = (EditText) view.findViewById(R.id.edit_purchase_details);
        edit_mpesa_txn_number = (EditText) view.findViewById(R.id.edit_mpesa_txn_number);
        edit_receipt_upload = (EditText) view.findViewById(R.id.edit_receipt_upload);

        edit_purchase_date = (EditText) view.findViewById(R.id.edit_purchase_date);
        new DatePickerUtility(edit_purchase_date);

        submit = (Button) view.findViewById(R.id.btn_submit_stock);
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

    class SaveStockToDB extends AsyncTask<Void, Void, Integer> {
        public int product_id;
        public int vendor_id;
        public int distributor_id;
        public int manufacturer_id;
        public double purchase_quantity;
        public double purchase_price;
        public String purchase_details;
        public String purchase_date;
        public int location_id;
        public String mpesa_txn_number;
        public String receipt_upload;

        int success = 0;
        int stock_id;
        int location_balance;

        ProductStock productStock;
        ProductStockDao productStockDao;
        JSONObject request_object = new JSONObject();
        JSONObject response_object = new JSONObject();


        public SaveStockToDB(int product_id, int vendor_id, int distributor_id,
                             int manufacturer_id, double purchase_quantity, double purchase_price,
                             String purchase_details, String purchase_date, int location_id,
                             String mpesa_txn_number, String receipt_upload) {

            this.product_id = product_id;
            this.vendor_id = vendor_id;
            this.distributor_id = distributor_id;
            this.manufacturer_id = manufacturer_id;
            this.purchase_quantity = purchase_quantity;
            this.purchase_price = purchase_price;
            this.purchase_details = purchase_details;
            this.purchase_date = purchase_date;
            this.location_id = location_id;
            this.mpesa_txn_number = mpesa_txn_number;
            this.receipt_upload = receipt_upload;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(getActivity()).getDB();
            productStockDao = db.productStockDao();
            productStock = new ProductStock();

            try {
                request_object.put("product_id", product_id);
                request_object.put("vendor_id", vendor_id);
                request_object.put("distributor_id", distributor_id);
                request_object.put("manufacturer_id", manufacturer_id);
                request_object.put("purchase_quantity", purchase_quantity);
                request_object.put("purchase_price", purchase_price);
                request_object.put("purchase_details", purchase_details);
                request_object.put("purchase_date", purchase_date);
                request_object.put("location_id", location_id);
                request_object.put("mpesa_txn_number", mpesa_txn_number);
                request_object.put("receipt_upload", receipt_upload);

                Log.d("Stock request...", request_object.toString());

            } catch (JSONException e) {
                Log.d("Error preparing request", e.getMessage());
                e.printStackTrace();
            }
            try {
                productStock.setDistributor_id(distributor_id);
                productStock.setLocation_balance(location_balance);
                productStock.setLocation_id(location_id);
                productStock.setId(stock_id);
                productStock.setManufacturer_id(manufacturer_id);
                productStock.setMpesa_txn_number(mpesa_txn_number);
                productStock.setProduct_id(product_id);
                productStock.setPurchase_date(purchase_date);
                productStock.setPurchase_details(purchase_details);
                productStock.setPurchase_price(purchase_price);
                productStock.setPurchase_quantity(purchase_quantity);
                productStock.setReceipt_upload(receipt_upload);
                productStock.setVendor_id(vendor_id);

                Log.d("Stock db request...", productStock.toString());
            } catch (Exception e) {
                Log.d("Error creating request", e.getMessage());
            }
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            /**
             * Saving stock record to the server!
             */
            try {
                response_object = CommonHelper.sendPostRequestWithJsonResponse(BuildConfig.SERVER_URL,
                        "createProductStock/", request_object.toString());

                Log.d("Stock response...", response_object.toString());

                stock_id = response_object.getInt("id");
                location_balance = response_object.getInt(" location_balance");

            } catch (IOException e) {
                Log.d("Error posting request", e.getMessage());
                e.printStackTrace();
            } catch (JSONException jsonException) {
                Log.d("Error getting response", jsonException.getMessage());
                jsonException.printStackTrace();
            }

            /**
             * Saving stock record to local db only if the record is send to the server!
             */
            if (stock_id > 0) {
                try {
                    productStock.setId(stock_id);
                    productStock.setLocation_balance(location_balance);

                    productStockDao.insertProductStock(productStock);
                    Log.d("Record saved", "product id:" + product_id +
                            ", location stock balance: " + location_balance);
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
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

        }
    }
}
