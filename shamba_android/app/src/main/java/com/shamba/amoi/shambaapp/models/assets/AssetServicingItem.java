package com.shamba.amoi.shambaapp.models.assets;

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
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 15/04/2018.
 */
public class AssetServicingItem {
    public static  AssetServicingItem selectedAssetServicingItem;
    public static List<AssetServicingItem> staticAssetServicingItems;
    int id;
    int asset_id;
    int service_type_id;
    int service_provider_id;
    String planned_service_start_date;
    String planned_service_end_date;
    String actual_service_start_date;
    String actual_service_end_date;
    int pay_rate_id;
    double work_size;
    double service_cost;
    String service_completed;
    String details;

    public String getPlanned_service_start_date() {
        return planned_service_start_date;
    }

    public void setPlanned_service_start_date(String planned_service_start_date) {
        this.planned_service_start_date = planned_service_start_date;
    }

    public String getPlanned_service_end_date() {
        return planned_service_end_date;
    }

    public void setPlanned_service_end_date(String planned_service_end_date) {
        this.planned_service_end_date = planned_service_end_date;
    }

    public String getActual_service_start_date() {
        return actual_service_start_date;
    }

    public void setActual_service_start_date(String actual_service_start_date) {
        this.actual_service_start_date = actual_service_start_date;
    }

    public String getActual_service_end_date() {
        return actual_service_end_date;
    }

    public void setActual_service_end_date(String actual_service_end_date) {
        this.actual_service_end_date = actual_service_end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public int getService_provider_id() {
        return service_provider_id;
    }

    public void setService_provider_id(int service_provider_id) {
        this.service_provider_id = service_provider_id;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public double getWork_size() {
        return work_size;
    }

    public void setWork_size(double work_size) {
        this.work_size = work_size;
    }

    public double getService_cost() {
        return service_cost;
    }

    public void setService_cost(double service_cost) {
        this.service_cost = service_cost;
    }

    public String isService_completed() {
        return service_completed;
    }

    public void setService_completed(String service_completed) {
        this.service_completed = service_completed;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * get asset service by name.
     * @param assetServicingItems
     * @param asset_id
     * @return
     */
    public static AssetServicingItem getAssetServicingItemByAsset(List<AssetServicingItem>
                                                                       assetServicingItems,int asset_id) {
        AssetServicingItem assetServicingItem = null;

        for (int i = 0; i < assetServicingItems.size(); ++i) {
            if (assetServicingItems.get(i).getAsset_id()==asset_id) {
                assetServicingItem = assetServicingItems.get(i);
                break;
            }
        }
        return assetServicingItem;
    }
    /**
     * get asset service by id.
     * @param assetServicingItems
     * @param id
     * @return
     */
    public static AssetServicingItem getAssetServicingItemById(List<AssetServicingItem>
                                                                       assetServicingItems,int id) {
        AssetServicingItem assetServicingItem = null;

        for (int i = 0; i < assetServicingItems.size(); ++i) {
            if (assetServicingItems.get(i).getId() == id) {
                assetServicingItem = assetServicingItems.get(i);
                break;
            }
        }
        return assetServicingItem;
    }

    /**
     * @param activity
     * @return
     */
    public static List<AssetServicingItem> getAllAssetServicings(Activity activity) {
        List<AssetServicingItem> assetServicingItems = new ArrayList<>();

        try {
            assetServicingItems = new com.shamba.amoi.shambaapp.models.assets.GetAssetsServices(activity).
                    execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return assetServicingItems;
    }
}

/**
 * Get assets' services from  server and android local db.
 */
class GetAssetsServices extends AsyncTask<Void, Void, List<AssetServicingItem>> {
    public Activity activity;
    Context context;

    public GetAssetsServices(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<AssetServicingItem> doInBackground(Void... voids) {
        return getAllAssetServicesFromServer();
    }
    /**
     * Pools all asset servicing from server application!
     * @return
     */
    private List<AssetServicingItem> getAllAssetServicesFromServer() {

        List<AssetServicingItem> assetServicingItems = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "assetService/", "");

            Log.d("# of services pooled:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                AssetServicingItem assetServicingItem = new AssetServicingItem();

                JSONObject jsonObject = jArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                assetServicingItem.setId(id);

                int asset_id = jsonObject.getInt("asset_id");
                assetServicingItem.setAsset_id(asset_id);

                int service_type_id = jsonObject.getInt("service_type_id");
                assetServicingItem.setService_type_id(service_type_id);

                int service_provider_id = jsonObject.getInt("service_provider_id");
                assetServicingItem.setService_provider_id(service_provider_id);

                String  planned_service_start_date = jsonObject.getString(
                        "planned_service_start_date");
                assetServicingItem.setPlanned_service_start_date(planned_service_start_date);

                String  planned_service_end_date = jsonObject.getString(
                        "planned_service_end_date");
                assetServicingItem.setPlanned_service_end_date(planned_service_end_date);

                String  actual_service_start_date = jsonObject.getString(
                        "actual_service_start_date");
                assetServicingItem.setActual_service_start_date(actual_service_start_date);

                String  actual_service_end_date = jsonObject.getString(
                        "actual_service_end_date");
                assetServicingItem.setActual_service_end_date(actual_service_end_date);

                int pay_rate_id = jsonObject.getInt("pay_rate_id");
                assetServicingItem.setPay_rate_id(pay_rate_id);

                double work_size = jsonObject.getDouble("work_size");
                assetServicingItem.setWork_size(work_size);

                double service_cost = jsonObject.getDouble("service_cost");
                assetServicingItem.setService_cost(service_cost);

                String  service_completed = jsonObject.getString(
                        "service_completed");
                assetServicingItem.setService_completed(service_completed);

                String  details = jsonObject.getString("details");
                assetServicingItem.setDetails(details);

                assetServicingItems.add(assetServicingItem);
            }

            AssetServicingItem.staticAssetServicingItems = assetServicingItems;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return assetServicingItems;
    }

    @Override
    public void onPostExecute(List<AssetServicingItem> assetServicingItems) {
    }
}
