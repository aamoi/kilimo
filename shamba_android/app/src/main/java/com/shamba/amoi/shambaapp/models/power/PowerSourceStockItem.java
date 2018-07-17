package com.shamba.amoi.shambaapp.models.power;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStock;
import com.shamba.amoi.shambaapp.db.power.PowerSourceStockDao;
import com.shamba.amoi.shambaapp.models.inventory.InventoryUtilizationItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 27/12/2017.
 */

public class PowerSourceStockItem {
    public static PowerSourceItem currentPowerSourceItem;

    private String stock_id;
    private String power_source_id;
    private String vendor_name;
    private String produce_name;
    private String stock_ref;
    private String stock_date;
    private double stock_quantity;
    private double stock_price;
    private String stock_description;

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getPower_source_id() {
        return power_source_id;
    }

    public void setPower_source_id(String power_source_id) {
        this.power_source_id = power_source_id;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setProduce_name(String produce_name) {
        this.produce_name = produce_name;
    }

    public String getProduce_name() {
        return produce_name;
    }

    public void setStock_ref(String stock_ref) {
        this.stock_ref = stock_ref;
    }

    public String getStock_ref() {
        return stock_ref;
    }

    public void setStock_date(String stock_date) {
        this.stock_date = stock_date;
    }

    public String getStock_date() {
        return stock_date;
    }

    public void setStock_quantity(double stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public double getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_price(double stock_price) {
        this.stock_price = stock_price;
    }

    public double getStock_price() {
        return stock_price;
    }

    public void setStock_description(String stock_description) {
        this.stock_description = stock_description;
    }

    public String getStock_description() {
        return stock_description;
    }

    public static List<PowerSourceStockItem> getAllPowerSourceStocks(HomeActivity homeActivity){

        List<PowerSourceStockItem> powerSourceStockItems=new ArrayList<>();

        try {
            powerSourceStockItems=new GetPowerSourceStocks(homeActivity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return powerSourceStockItems;
    }
}

/**
 * This class locally fetches all power source stock stored in the database.
 */
class GetPowerSourceStocks extends AsyncTask<Void, Void, List<PowerSourceStockItem>> {

    public String TAG=new InventoryUtilizationItem().TAG;


    PowerSourceStockDao powerSourceStockDao;
    PowerSourceStock powerSourceStock;
    Activity activity;
    ArrayList<PowerSourceStockItem> powerSourceStockItems;

    public GetPowerSourceStocks(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        powerSourceStockDao = db.powerSourceStockDao();
        powerSourceStock = new PowerSourceStock();
        powerSourceStockItems = new ArrayList<>();
    }
    @Override
    protected List<PowerSourceStockItem> doInBackground(Void... voids) {
        List<PowerSourceStock> dbList = new ArrayList<>();
        dbList = powerSourceStockDao.getAllPowerSourceStocks();

        for (int i = 0; i < dbList.size(); ++i) {
            PowerSourceStockItem powerSourceStockItem = new PowerSourceStockItem();
            powerSourceStockItem.setStock_id(dbList.get(i).getStock_id());

            powerSourceStockItem.setPower_source_id(dbList.get(i).getPower_source_id());
            powerSourceStockItem.setProduce_name(dbList.get(i).getProduce_name());
            powerSourceStockItem.setStock_date(dbList.get(i).getStock_date());
            powerSourceStockItem.setStock_description(dbList.get(i).getStock_description());
            powerSourceStockItem.setStock_price(dbList.get(i).getStock_price());
            powerSourceStockItem.setStock_quantity(dbList.get(i).getStock_quantity());
            powerSourceStockItem.setStock_ref(dbList.get(i).getStock_ref());
            powerSourceStockItem.setVendor_name(dbList.get(i).getVendor_name());

            powerSourceStockItems.add(powerSourceStockItem);
        }
        Log.d(TAG, "Power source stocks is :" + String.valueOf(powerSourceStockItems.size()));

        return powerSourceStockItems;
    }

    @Override
    protected void onPostExecute(List<PowerSourceStockItem> result) {
    }
}
