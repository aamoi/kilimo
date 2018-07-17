package com.shamba.amoi.shambaapp.db.inventory;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 14/02/2018.
 */
@Entity
public class ProductStock {
    @PrimaryKey
    @NonNull
    private String stock_id;
    private String product_id;
    private String vendor_name;
    private String produce_name;
    private String stock_ref;
    private String stock_date;
    private double stock_quantity;
    private double stock_price;
    private String stock_description;

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduce_name(String produce_name) {
        this.produce_name = produce_name;
    }

    public String getProduce_name() {
        return produce_name;
    }

    public void setStock_ref(String stock_ref) {
        this.stock_ref = stock_ref;
    }

    public String getStock_ref() {
        return stock_ref;
    }

    public void setStock_date(String stock_date) {
        this.stock_date = stock_date;
    }

    public String getStock_date() {
        return stock_date;
    }

    public void setStock_quantity(double stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public double getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_price(double stock_price) {
        this.stock_price = stock_price;
    }

    public double getStock_price() {
        return stock_price;
    }

    public void setStock_description(String stock_description) {
        this.stock_description = stock_description;
    }

    public String getStock_description() {
        return stock_description;
    }
}
