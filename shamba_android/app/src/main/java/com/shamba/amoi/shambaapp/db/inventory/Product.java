package com.shamba.amoi.shambaapp.db.inventory;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amoi on 13/02/2018.
 */
@Entity
public class Product {
    @PrimaryKey(autoGenerate = false)
    String product_id;
    String product_vendor;
    String product_name;
    String product_type;
    String product_measuringUnit;
    String comments;

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_measuringUnit(String product_measuringUnit) {
        this.product_measuringUnit = product_measuringUnit;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setProduct_vendor(String product_vendor) {
        this.product_vendor = product_vendor;
    }

    public String getComments() {
        return comments;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_measuringUnit() {
        return product_measuringUnit;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getProduct_vendor() {
        return product_vendor;
    }
}
