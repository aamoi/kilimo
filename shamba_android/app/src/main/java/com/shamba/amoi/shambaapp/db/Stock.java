package com.shamba.amoi.shambaapp.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amoi on 28/12/2017.
 */
@Entity
public class Stock {
    @PrimaryKey(autoGenerate = true)
    private int stock_id;
    private int product_id;
    private String stock_ref;
    private String stock_date;
    private String stock_quantity;
    private String stock_price;
    private String stock_description;

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
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

    public void setStock_quantity(String stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_price(String stock_price) {
        this.stock_price = stock_price;
    }

    public String getStock_price() {
        return stock_price;
    }

    public void setStock_description(String stock_description) {
        this.stock_description = stock_description;
    }

    public String getStock_description() {
        return stock_description;
    }
}
