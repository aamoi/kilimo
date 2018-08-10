package com.shamba.amoi.shambaapp.models.product;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.models.labor.PayRateItem;
import com.shamba.amoi.shambaapp.models.labor.ResourceItem;
import com.shamba.amoi.shambaapp.models.projects.LocationBlockItem;
import com.shamba.amoi.shambaapp.models.projects.LocationItem;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;
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
public class ProductItem {
    public static List<ProductItem> staticProductItemList;
    public static ProductItem selectedProductItem;
    private int id;
    private String product_name;
    private boolean is_asset;
    private int category_id;
    private int uom_id;
    private boolean is_fuel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public boolean isIs_asset() {
        return is_asset;
    }

    public void setIs_asset(boolean is_asset) {
        this.is_asset = is_asset;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUom_id() {
        return uom_id;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }

    public boolean isIs_fuel() {
        return is_fuel;
    }

    public void setIs_fuel(boolean is_fuel) {
        this.is_fuel = is_fuel;
    }

    /**
     * get product name by id.
     * @param productItems
     * @param id
     * @return
     */
    public static ProductItem getProductItemByID(List<ProductItem> productItems, int id) {
        ProductItem productItem = null;

        for (int i = 0; i < productItems.size(); ++i) {
            if (productItems.get(i).getId() == id) {
                productItem = productItems.get(i);
                break;
            }
        }
        return productItem;
    }

    public static List<ProductItem> getAllProducts(Activity activity) {
        List<ProductItem> all_products = new ArrayList<>();

        try {
            all_products = new GetProducts(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return all_products;
    }
}

/**
 * Get products from android local db.
 */
class GetProducts extends AsyncTask<Void, Void, List<ProductItem>> {
    public Activity activity;
    Context context;

    public GetProducts(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<ProductItem> doInBackground(Void... voids) {
        return getAllProductsFromServer();
    }
    /**
     * Pools all products from server application!
     * @return
     */
    private List<ProductItem> getAllProductsFromServer() {

        List<ProductItem> productItemList = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "product/", "");

            Log.d("# of products pooled:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                ProductItem productItem = new ProductItem();
                JSONObject jsonObject = jArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                productItem.setId(id);
                String product_name = jsonObject.getString("product_name");
                productItem.setProduct_name(product_name);
                int category_id = jsonObject.getInt("category_id");
                productItem.setCategory_id(category_id);
                int uom_id = jsonObject.getInt("uom_id");
                productItem.setUom_id(uom_id);
                boolean is_asset = jsonObject.getBoolean("_asset");
                productItem.setIs_asset(is_asset);
                boolean is_fuel = jsonObject.getBoolean("_fuel");
                productItem.setIs_fuel(is_fuel);
                productItemList.add(productItem);
            }

            ProductItem.staticProductItemList = productItemList;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productItemList;
    }

    @Override
    public void onPostExecute(List<ProductItem> productItems) {
    }
}