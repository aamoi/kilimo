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

public class ProductVarietyItem {
    public static ProductVarietyItem staticProductItemList;
    public static ProductVarietyItem selectedProductVarietyItem;
    int id;
    String variety_name;
    int product_id;
    String comments;
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariety_name() {
        return variety_name;
    }

    public void setVariety_name(String variety_name) {
        this.variety_name = variety_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * get per product variety items.
     * @param activity
     * @return
     */
    public static List<ProductVarietyItem> getProductVarieties(Activity activity, int product_id) {

        List<ProductVarietyItem> allProductVarietyItems = new ArrayList<>();

        List<ProductVarietyItem> productVarieties = new ArrayList<>();

        try {
            allProductVarietyItems= new GetProductVariety(activity).execute().get();

            for (ProductVarietyItem productVarietyItem:allProductVarietyItems) {
                if(productVarietyItem.getId()==product_id){
                    productVarieties.add(productVarietyItem);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return productVarieties;
    }
}


/**
 * Get all product variety from android local db.
 */
class GetProductVariety extends AsyncTask<Void, Void, List<ProductVarietyItem>> {
    public Activity activity;
    Context context;

    public GetProductVariety(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<ProductVarietyItem> doInBackground(Void... voids) {
        return getAllProductVarietiesFromServer();
    }

    /**
     * Pools all product varieties from server application!
     *
     * @return
     */
    private List<ProductVarietyItem> getAllProductVarietiesFromServer() {

        List<ProductVarietyItem> productVarietyItems = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "product_variety/", "");

            Log.d("# of product variety:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                ProductVarietyItem productVarietyItem = new ProductVarietyItem();
                JSONObject jsonObject = jArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                productVarietyItem.setId(id);
                String variety_name = jsonObject.getString("name");
                productVarietyItem.setVariety_name(variety_name);
                int product_id = jsonObject.getInt("product_id");
                productVarietyItem.setProduct_id(product_id);
                String description = jsonObject.getString("description");
                productVarietyItem.setDescription(description);
                productVarietyItems.add(productVarietyItem);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productVarietyItems;
    }

    @Override
    public void onPostExecute(List<ProductVarietyItem> productVarietyItems) {
    }
}
