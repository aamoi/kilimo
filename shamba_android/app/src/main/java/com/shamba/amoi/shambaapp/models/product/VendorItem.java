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
public class VendorItem {
    public static List<VendorItem> staticVendorItemList;
    public static VendorItem selectedVendorItem;

    private int id;
    private String vendor_name;
    private String vendor_phone;
    private String county;
    private String town;
    private String map;
    private String email;
    private String directions;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_phone() {
        return vendor_phone;
    }

    public void setVendor_phone(String vendor_phone) {
        this.vendor_phone = vendor_phone;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * get vendor by id.
     * @param vendorItems
     * @param id
     * @return
     */
    public static VendorItem getVendorItemByID(List<VendorItem> vendorItems, int id){
        VendorItem vendorItem=null;

        for(int i=0;i<vendorItems.size();++i){
            if(vendorItems.get(i).getId()==id){
                vendorItem= vendorItems.get(i);
                break;
            }
        }
      return vendorItem;
    }

    /**
     * get vendor by id.
     * @param activity
     * @param id
     * @return
     */
    public static VendorItem getVendorItemByID(Activity activity, int id){
        VendorItem vendorItem=null;

        List<VendorItem> vendorItems=new ArrayList<>();
        vendorItems=getAllVendors(activity);

        for(int i=0;i<vendorItems.size();++i){
            if(vendorItems.get(i).getId()==id){
                vendorItem= vendorItems.get(i);
                break;
            }
        }
        return vendorItem;
    }

    /**
     * get vendor by id.
     * @param vendorItems
     * @param name
     * @return
     */
    public static VendorItem getVendorByName(List<VendorItem> vendorItems, String name){
        VendorItem vendorItem=null;

        for(int i=0;i<vendorItems.size();++i){
            if(vendorItems.get(i).getVendor_name().equalsIgnoreCase(name)){
                vendorItem= vendorItems.get(i);
                break;
            }
        }
        return vendorItem;
    }

    /**
     * get vendor by id.
     * @param activity
     * @param name
     * @return
     */
    public static VendorItem getVendorByName(Activity activity, String name){
        List<VendorItem> vendorItems=new ArrayList<>();
        vendorItems=VendorItem.getAllVendors(activity);
        VendorItem vendorItem=null;

        for(int i=0;i<vendorItems.size();++i){
            if(vendorItems.get(i).getVendor_name().equalsIgnoreCase(name)){
                vendorItem= vendorItems.get(i);
                break;
            }
        }
        return vendorItem;
    }

    /**
     * @param activity
     * @return
     */
    public static List<VendorItem> getAllVendors(Activity activity) {
        List<VendorItem> vendorItems = new ArrayList<>();

        try {
            vendorItems = new com.shamba.amoi.shambaapp.models.product.
                    GetVendors(activity).
                    execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return vendorItems;
    }
}

/**
 * Get all vendors from  server and android local db.
 */
class GetVendors extends AsyncTask<Void, Void, List<VendorItem>> {
    public Activity activity;
    Context context;

    public GetVendors(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<VendorItem> doInBackground(Void... voids) {
        return getAllVendorFromServer();
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

    @Override
    public void onPostExecute(List<VendorItem> vendorItems) {
    }
}
