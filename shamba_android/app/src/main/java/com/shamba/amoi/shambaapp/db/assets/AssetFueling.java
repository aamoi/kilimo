package com.shamba.amoi.shambaapp.db.assets;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.shamba.amoi.shambaapp.activities.HomeActivity;
import com.shamba.amoi.shambaapp.models.power.PowerSourceStockItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 15/04/2018.
 */
@Entity
public class AssetFueling {
    @PrimaryKey
    @NonNull
    private String asset_fueling_id;
    private String assert_d;
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

    public String getAssert_d() {
        return assert_d;
    }

    public void setAssert_d(String assert_d) {
        this.assert_d = assert_d;
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
}

