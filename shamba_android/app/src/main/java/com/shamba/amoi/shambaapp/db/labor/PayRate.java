package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 31/12/2017.
 */
@Entity
public class PayRate {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    int id;
    String name;
    double unit_price;
    String details;
    int uom_id;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUom_id() {
        return uom_id;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }
}
