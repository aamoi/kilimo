package com.shamba.amoi.shambaapp.fragments.product;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.R;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.adapters.inventory.ProductStockRecyclerViewAdapter;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.product.ProductStockDao;
import com.shamba.amoi.shambaapp.models.product.ProductItem;
import com.shamba.amoi.shambaapp.models.product.ProductStockItem;
import com.shamba.amoi.shambaapp.models.product.UnitOfMeasureItem;
import com.shamba.amoi.shambaapp.shareResources.BaseFragment;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductStockFragment extends BaseFragment {

    ProductItem productItem;
    Button add_stock;
    private OnListFragmentInteractionListener mListener;

    public static ProductStockFragment newInstance(int product_id, String produce_name,
                                                   String manufacturer, String package_size) {
        ProductStockFragment fragment = new ProductStockFragment();
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
        productItem = ProductItem.selectedProductItem;

        View view = inflater.inflate(R.layout.fragment_productstock_list, container,
                false);

        productItem = ProductItem.selectedProductItem;

        List<ProductStockItem> product_stock_list = new ArrayList<>();

//        product_stock_list = ProductStockItem.staticProductStockItemList;

        try {
            product_stock_list = new GetProductStocks().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        double total_quantity = 0.0;

        String stock_size="";

        if(product_stock_list!=null){
            for (int i = 0; i < product_stock_list.size(); ++i) {
                total_quantity = total_quantity + product_stock_list.get(i).getPurchase_quantity();
            }
            stock_size = String.valueOf(product_stock_list.size());
        }
        Log.d("stock quantity", stock_size);

        String unit_of_measure = UnitOfMeasureItem.getUnitOfMeasureItemByID(
                UnitOfMeasureItem.staticUnitOfMeasureItemList, productItem.getUom_id()).getSymbol();

        getActivity().setTitle(stock_size + " " + productItem.getProduct_name() + " " +
                getString(R.string.title_fragment_product_stocks) +
                "(" + String.valueOf(total_quantity) +" "+ unit_of_measure + ")");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_product_stock);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(new ProductStockRecyclerViewAdapter(product_stock_list,
                (HomeActivity) this.getActivity()));

        add_stock = (Button) view.findViewById(R.id.btn_add_product_stock);
        add_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BaseFragment.changeFragment((AppCompatActivity) getActivity(),
                        R.id.fragment_placeholder_home, new RestockProductFragment());

            }
        });
        return view;
    }

    public String[][] getDummyProductStocks() {

        String[][] stocks = {
                {"1", "REC010101", "Cabbage", "12/12/2017", "3", "50", "To plant on BlockA"},
                {"2", "REC010102", "Cabbage", "14/12/2017", "2", "100", "To plant on BlockB"},
                {"3", "REC010103", "Cabbage", "30/12/2017", "1", "250", "To plant on BlockC"},
        };

        return stocks;
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

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ProductStockItem item);
    }

    /**
     * Get all productstockitem from server.
     */
    class GetProductStocks extends AsyncTask<Void, Void, List<ProductStockItem>> {

        ProductStockDao productStockDao;
        List<ProductStockItem> productStockItems;

        @Override
        protected void onPreExecute() {
            ShambaAppDB db= new DBAdaptor(getActivity()).getDB();
            productStockDao=db.productStockDao();
            productStockItems=new ArrayList();
        }

        @Override
        protected List<ProductStockItem> doInBackground(Void... voids) {

            try {
                List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL,"productStock/","");

//                Log.d("Product stocks", String.valueOf(response.get(0).getInt("id")));

                JSONArray jArray = new JSONArray(response);

                for(int i=0;i<jArray.length();++i){

                    ProductStockItem productStockItem=new ProductStockItem();

                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id=jsonObject.getInt("id");
                    productStockItem.setId(id);

                    int product_id=jsonObject.getInt("product_id");
                    productStockItem.setProduct_id(product_id);

                    int vendor_id=jsonObject.getInt("vendor_id");
                    productStockItem.setVendor_id(vendor_id);

                    int distributor_id=jsonObject.getInt("distributor_id");
                    productStockItem.setDistributor_id(distributor_id);

                    int manufacturer_id=jsonObject.getInt("manufacturer_id");
                    productStockItem.setManufacturer_id(manufacturer_id);

                    double purchase_quantity=jsonObject.getDouble("purchase_quantity");
                    productStockItem.setPurchase_quantity(purchase_quantity);

                    double purchase_price=jsonObject.getDouble("purchase_price");
                    productStockItem.setPurchase_price(purchase_price);

                    String purchase_details=jsonObject.getString("purchase_details");
                    productStockItem.setPurchase_details(purchase_details);

                    String purchase_date=jsonObject.getString("purchase_date");
                    productStockItem.setPurchase_date(purchase_date);

                    int location_id=jsonObject.getInt("location_id");
                    productStockItem.setLocation_id(location_id);

                    double location_balance=jsonObject.getDouble("location_balance");
                    productStockItem.setLocation_balance(location_balance);

                    String mpesa_txn_number=jsonObject.getString("mpesa_txn_number");
                    productStockItem.setMpesa_txn_number(mpesa_txn_number);

                    String receipt_upload=jsonObject.getString("receipt_upload");
                    productStockItem.setReceipt_upload(receipt_upload);

                    productStockItems.add(productStockItem);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            List<PlantingProgram> db_planting_programs = plantingProgramDao.getAllPlantingPrograms();
//
//            if (db_planting_programs.size() > 0){
//
//                for (int count = 0; count < db_planting_programs.size(); ++count) {
//                    PlantingProgramItem plantingProgramItem = new PlantingProgramItem();
//                    plantingProgramItem.setPlan_id(db_planting_programs.get(count).getPlan_id());
//                    plantingProgramItem.setPlanting_block(db_planting_programs.get(count).getPlanting_block());
//                    plantingProgramItem.setPlanting_location(db_planting_programs.get(count).getPlanting_location());
//                    plantingProgramItem.setPlanting_name(db_planting_programs.get(count).getPlanting_name());
//                    plantingProgramItem.setPlanting_produce(db_planting_programs.get(count).getPlanting_produce());
//                    plantingProgramItem.setPreparation_date(db_planting_programs.get(count).getPreparation_date());
//                    plantingProgramItem.setSeedbed_date(db_planting_programs.get(count).getSeedbed_date());
//                    plantingProgramItem.setTransplanting_date(db_planting_programs.get(count).getTransplanting_date());
//                    plantingProgramItem.setHarvesting_date(db_planting_programs.get(count).getHarvesting_date());
//                    plantingProgramItem.setSales_date(db_planting_programs.get(count).getSales_date());
//                    plantingProgramItem.setSeed_quantity(db_planting_programs.get(count).getSeed_quantity());
//                    plantingProgramItem.setPlanting_cost(db_planting_programs.get(count).getPlanting_cost());
//                    plantingProgramItem.setPlanting_revenue(db_planting_programs.get(count).getPlanting_revenue());
//
//                    plantingProgramItems.add(plantingProgramItem);
//                }
//            }

            return productStockItems;
        }

        @Override
        protected void onPostExecute(List<ProductStockItem> Plantings) {
//            super.onPostExecute(masterPlantingPlanItems);
        }
    }
}
