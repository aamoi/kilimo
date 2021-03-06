package com.shamba.amoi.shambaapp.db.product;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class StockUtilization {
    @PrimaryKey
    @NonNull
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

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
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
}
