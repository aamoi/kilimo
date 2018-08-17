package com.shamba.amoi.shambaapp.models.product;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 13/02/2018.
 */
public class ProductStockItem {
    public static List<ProductStockItem> staticProductStockItemList;
    public static ProductStockItem selectedProductStockItem;
    private int id;
    private int product_id;
    private int vendor_id;
    private int distributor_id;
    private int manufacturer_id;
    private double purchase_quantity;
    private double stock_balance;
    private double purchase_price;
    private String purchase_details;
    private String purchase_date;
    private int location_id;
    private double location_balance;
    private String mpesa_txn_number;
    private String receipt_upload;
    private String stock_order_status;

    public double getStock_balance() {
        return stock_balance;
    }

    public void setStock_balance(double stock_balance) {
        this.stock_balance = stock_balance;
    }

    public String getStock_order_status() {
        return stock_order_status;
    }

    public void setStock_order_status(String stock_order_status) {
        this.stock_order_status = stock_order_status;
    }

    public int getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(int distributor_id) {
        this.distributor_id = distributor_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public double getPurchase_quantity() {
        return purchase_quantity;
    }

    public void setPurchase_quantity(double purchase_quantity) {
        this.purchase_quantity = purchase_quantity;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getPurchase_details() {
        return purchase_details;
    }

    public void setPurchase_details(String purchase_details) {
        this.purchase_details = purchase_details;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getLocation_balance() {
        return location_balance;
    }

    public void setLocation_balance(double location_balance) {
        this.location_balance = location_balance;
    }

    public String getMpesa_txn_number() {
        return mpesa_txn_number;
    }

    public void setMpesa_txn_number(String mpesa_txn_number) {
        this.mpesa_txn_number = mpesa_txn_number;
    }

    public String getReceipt_upload() {
        return receipt_upload;
    }

    public void setReceipt_upload(String receipt_upload) {
        this.receipt_upload = receipt_upload;
    }
    /**
     * get product stock item by id.
     * @param productStockItems
     * @param id
     * @return
     */
    public static ProductStockItem getProductStockItemByID(List<ProductStockItem> productStockItems,
                                                           int id) {
        ProductStockItem productStockItem = null;

        for (int i = 0; i < productStockItems.size(); ++i) {
            if (productStockItems.get(i).getId() == id) {
                productStockItem = productStockItems.get(i);
                break;
            }
        }
        return productStockItem;
    }

    /**
     * get product stock items by product id.
     * @param productStockItems
     * @param product_id
     * @return
     */
    public static List<ProductStockItem> getProductStockItemByProductId(
            List<ProductStockItem> productStockItems, int product_id) {
        List<ProductStockItem> productStockItemList = new ArrayList<>();

        for (int i = 0; i < productStockItems.size(); ++i) {
            if (productStockItems.get(i).getProduct_id() == product_id) {
                productStockItemList.add(productStockItems.get(i));
            }
        }
        return productStockItemList;
    }

    /**
     * get all product stock items.
     * @param activity
     * @return
     */
    public static List<ProductStockItem> getAllProductStockItems(Activity activity) {

        List<ProductStockItem> productStockItemList = new ArrayList<>();

        try {
            productStockItemList= new GetProductStocks(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return productStockItemList;
    }
}

/**
 * Get all ProductStockItems from  server and android local db.
 */
class GetProductStocks extends AsyncTask<Void, Void, List<ProductStockItem>> {
    public Activity activity;
    Context context;

    public GetProductStocks(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<ProductStockItem> doInBackground(Void... voids) {
        return getAllProductStockFromServer();
    }
    /**
     * Pools all stocks from server application!
     * @return
     */
    private List<ProductStockItem> getAllProductStockFromServer(){

        List<ProductStockItem> productStockItems=new ArrayList<>();

        try {
            List<JSONObject> response= CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL,"productStock/","");

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

                double stock_balance=jsonObject.getDouble("stock_balance");
                productStockItem.setStock_balance(stock_balance);

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

                String stock_order_status=jsonObject.getString("stock_order_status");
                productStockItem.setStock_order_status(stock_order_status);

                productStockItems.add(productStockItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productStockItems;
    }

    @Override
    public void onPostExecute(List<ProductStockItem> productStockItems) {
    }
}
