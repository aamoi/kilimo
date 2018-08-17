package com.shamba.amoi.shambaapp.models.product;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.product.StockUtilization;
import com.shamba.amoi.shambaapp.models.assets.AssetServicingItem;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StockUtilizationItem {
    public static List<StockUtilizationItem> staticStockUtilizationItems;
    public static StockUtilizationItem selectedStockUtilizationItems;
    int id ;
    int stock_id;
    int task_id;
    int asset_id;
    double utilized_quantity;
    String  utilized_date;
    String details;

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public double getUtilized_quantity() {
        return utilized_quantity;
    }

    public void setUtilized_quantity(double utilized_quantity) {
        this.utilized_quantity = utilized_quantity;
    }

    public String getUtilized_date() {
        return utilized_date;
    }

    public void setUtilized_date(String utilized_date) {
        this.utilized_date = utilized_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * get stock Utilization by Task id.
     * @param stockUtilizationItems
     * @param task_id
     * @return
     */
    public static List<StockUtilizationItem> getStockUtilizationItemByTaskId(
            List<StockUtilizationItem> stockUtilizationItems,int task_id) {

        List<StockUtilizationItem> stock_utilization=new ArrayList<>();

        for (int i = 0; i < stockUtilizationItems.size(); ++i) {
            if (stockUtilizationItems.get(i).getTask_id() == task_id) {
                stock_utilization.add(stockUtilizationItems.get(i));
            }
        }
        return stock_utilization;
    }
    /**
     * get stock Utilization by asset id.
     * @param stockUtilizationItems
     * @param asset_id
     * @return
     */
    public static List<StockUtilizationItem> getStockUtilizationItemByAssetId(
            List<StockUtilizationItem> stockUtilizationItems,int asset_id) {

        List<StockUtilizationItem> stock_utilization=new ArrayList<>();

        for (int i = 0; i < stockUtilizationItems.size(); ++i) {
            if (stockUtilizationItems.get(i).getAsset_id() == asset_id) {
                stock_utilization.add(stockUtilizationItems.get(i));
            }
        }
        return stock_utilization;
    }
    /**
     * get stock Utilization by stock id.
     * @param stockUtilizationItems
     * @param stock_id
     * @return
     */
    public static List<StockUtilizationItem> getStockUtilizationItemByStockId(
            List<StockUtilizationItem> stockUtilizationItems,int stock_id) {

        List<StockUtilizationItem> stock_utilization=new ArrayList<>();

        for (int i = 0; i < stockUtilizationItems.size(); ++i) {
            if (stockUtilizationItems.get(i).getStock_id() == stock_id) {
                stock_utilization.add(stockUtilizationItems.get(i));
            }
        }
        return stock_utilization;
    }
    /**
     * get stock Utilization by id.
     * @param stockUtilizationItems
     * @param id
     * @return
     */
    public static StockUtilizationItem getStockUtilizationItemById(List<StockUtilizationItem>
                                                                       stockUtilizationItems,int id) {
        StockUtilizationItem stockUtilizationItem = new StockUtilizationItem();

        for (int i = 0; i < stockUtilizationItems.size(); ++i) {
            if (stockUtilizationItems.get(i).getId() == id) {
                stockUtilizationItem = stockUtilizationItems.get(i);
                break;
            }
        }
        return stockUtilizationItem;
    }

    /**
     * @param activity
     * @return
     */
    public static List<StockUtilizationItem> getAllStockUtilizations(Activity activity) {
        List<StockUtilizationItem> stockUtilizationItems = new ArrayList<>();

        try {
            stockUtilizationItems = new com.shamba.amoi.shambaapp.models.product.
                    GetStockUtilization(activity).
                    execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return stockUtilizationItems;
    }
}

/**
 * Get stock utilization from  server and android local db.
 */
class GetStockUtilization extends AsyncTask<Void, Void, List<StockUtilizationItem>> {
    public Activity activity;
    Context context;

    public GetStockUtilization(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<StockUtilizationItem> doInBackground(Void... voids) {
        return getAllAssetServicesFromServer();
    }
    /**
     * Pools all stock utilization from server application!
     * @return
     */
    private List<StockUtilizationItem> getAllAssetServicesFromServer() {

        List<StockUtilizationItem> stockUtilizationItems = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "stockUtilization/", "");

            Log.d("# utilization pooled:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                StockUtilizationItem stockUtilizationItem = new StockUtilizationItem();

                JSONObject jsonObject = jArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                stockUtilizationItem.setId(id);

                int asset_id = jsonObject.getInt("asset_id");
                stockUtilizationItem.setAsset_id(asset_id);

                int stock_id = jsonObject.getInt("stock_id");
                stockUtilizationItem.setStock_id(stock_id);

                int task_id = jsonObject.getInt("task_id");
                stockUtilizationItem.setTask_id(task_id);

                String  details = jsonObject.getString("details");
                stockUtilizationItem.setDetails(details);

                String  utilized_date = jsonObject.getString("utilized_date");
                stockUtilizationItem.setUtilized_date(utilized_date);

                double utilized_quantity = jsonObject.getDouble("utilized_quantity");
                stockUtilizationItem.setUtilized_quantity(utilized_quantity);

                stockUtilizationItems.add(stockUtilizationItem);
            }

            StockUtilizationItem.staticStockUtilizationItems = stockUtilizationItems;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stockUtilizationItems;
    }

    @Override
    public void onPostExecute(List<StockUtilizationItem> assetServicingItems) {
    }
}
