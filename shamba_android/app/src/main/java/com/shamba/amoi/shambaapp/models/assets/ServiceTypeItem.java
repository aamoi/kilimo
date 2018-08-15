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
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 20/12/2017.
 */
public class ServiceTypeItem {
    public static ServiceTypeItem selectedServiceTypeItem;
    public static List<ServiceTypeItem> staticServiceTypeItems;

    int id;
    String name;
    String details;

    /**
     * get all service types by id.
     * @param activity
     * @return
     */
    public static ServiceTypeItem getServiceTypeById(Activity activity, int id) {

        List<ServiceTypeItem> serviceTypeItems = new ArrayList<>();

        ServiceTypeItem serviceTypeItem = new ServiceTypeItem();

        try {
            serviceTypeItems = new GetServiceTypes(activity).
                    execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < serviceTypeItems.size(); ++i) {
            if (serviceTypeItems.get(i).getId() == id) {
                serviceTypeItem = serviceTypeItems.get(i);
                break;
            }
        }
        return serviceTypeItem;
    }

    /**
     * get all service types.
     *
     * @param activity
     * @return
     */
    public static List<ServiceTypeItem> getAllServiceTypes(Activity activity) {
        List<ServiceTypeItem> serviceTypeItems = new ArrayList<>();

        try {
            serviceTypeItems = new GetServiceTypes(activity).
                    execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return serviceTypeItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

/**
 * Get service types from android local db.
 */
class GetServiceTypes extends AsyncTask<Void, Void, List<ServiceTypeItem>> {
    public Activity activity;
    Context context;

    public GetServiceTypes(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<ServiceTypeItem> doInBackground(Void... voids) {
        return getAllServiceTypesFromServer();
    }

    /**
     * Pools all service types from server application!
     *
     * @return
     */
    private List<ServiceTypeItem> getAllServiceTypesFromServer() {

        List<ServiceTypeItem> serviceTypeItems = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "serviceType/", "");

            Log.d("# of service types:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                ServiceTypeItem serviceTypeItem = new ServiceTypeItem();

                JSONObject jsonObject = jArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                serviceTypeItem.setId(id);

                String name = jsonObject.getString("name");
                serviceTypeItem.setName(name);

                String details = jsonObject.getString("details");
                serviceTypeItem.setDetails(details);

                serviceTypeItems.add(serviceTypeItem);
            }

            ServiceTypeItem.staticServiceTypeItems = serviceTypeItems;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return serviceTypeItems;
    }

    @Override
    public void onPostExecute(List<ServiceTypeItem> serviceTypes) {
    }
}

