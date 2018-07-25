package com.shamba.amoi.shambaapp.models.assets;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.assets.AssetFueling;
import com.shamba.amoi.shambaapp.db.assets.AssetFuelingDao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 15/04/2018.
 */

public class AssetFuelingItem {
    public static  AssetFuelingItem currentAssetFuelingItem;
    private String asset_fueling_id;
    private String assert_id;
    private String stock_id;
    private String fueling_date;
    private String fueling_quantity;
    private String fueling_description;

    public String getAsset_fueling_id() {
        return asset_fueling_id;
    }

    public void setAsset_fueling_id(String asset_fueling_id) {
        this.asset_fueling_id = asset_fueling_id;
    }

    public String getAssert_id() {
        return assert_id;
    }

    public void setAssert_id(String assert_d) {
        this.assert_id = assert_d;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getFueling_date() {
        return fueling_date;
    }

    public void setFueling_date(String fueling_date) {
        this.fueling_date = fueling_date;
    }

    public String getFueling_quantity() {
        return fueling_quantity;
    }

    public void setFueling_quantity(String fueling_quantity) {
        this.fueling_quantity = fueling_quantity;
    }

    public String getFueling_description() {
        return fueling_description;
    }

    public void setFueling_description(String fueling_description) {
        this.fueling_description = fueling_description;
    }
    public static List<AssetFuelingItem> getAllAssetFuelings(HomeActivity homeActivity){

        List<AssetFuelingItem> assetFuelingItems=new ArrayList<>();

        try {
            assetFuelingItems=new GetAssetFuelings(homeActivity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return assetFuelingItems;
    }
}

/**
 * This class locally fetches all asset fuelings stored in the database.
 */
class GetAssetFuelings extends AsyncTask<Void, Void, List<AssetFuelingItem>> {

//    public String TAG=new InventoryUtilizationItem().TAG;
    AssetFuelingDao assetFuelingDao;
    AssetFueling assetFueling;
    Activity activity;
    ArrayList<AssetFuelingItem> assetFuelingItems;

    public GetAssetFuelings(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        assetFuelingDao  = db.assetFuelingDao();
        assetFueling = new AssetFueling();
        assetFuelingItems = new ArrayList<>();
    }
    @Override
    protected List<AssetFuelingItem> doInBackground(Void... voids) {
        List<AssetFueling> dbList = new ArrayList<>();
        dbList = assetFuelingDao.getAllAssetFuelings();

        for (int i = 0; i < dbList.size(); ++i) {
            AssetFuelingItem assetFuelingItem = new AssetFuelingItem();

            assetFuelingItem.setAssert_id(dbList.get(i).getAssert_d());
            assetFuelingItem.setAsset_fueling_id(dbList.get(i).getAsset_fueling_id());
            assetFuelingItem.setAssert_id(dbList.get(i).getAsset_fueling_id());
            assetFuelingItem.setFueling_date(dbList.get(i).getFueling_date());
            assetFuelingItem.setAsset_fueling_id(dbList.get(i).getAsset_fueling_id());

            assetFuelingItems.add(assetFuelingItem);
        }
//        Log.d(TAG, "Asset fueling is :" + String.valueOf(assetFuelingItems.size()));
        return assetFuelingItems;
    }

    @Override
    protected void onPostExecute(List<AssetFuelingItem> result) {
    }
}
