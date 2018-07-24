package com.shamba.amoi.shambaapp.db.product;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
@Entity
public class ProductStock {
    @PrimaryKey(autoGenerate = false)
    private int id;
    private int product_id;
    private int vendor_id;
    private int distributor_id;
    private int manufacturer_id;
    private double purchase_quantity;
    private double purchase_price;
    private String purchase_details;
    private String purchase_date;
    private int location_id;
    private double location_balance;
    private String mpesa_txn_number;
    private String receipt_upload;

    public int getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(int distributor_id) {
        this.distributor_id = distributor_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public double getPurchase_quantity() {
        return purchase_quantity;
    }

    public void setPurchase_quantity(double purchase_quantity) {
        this.purchase_quantity = purchase_quantity;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getPurchase_details() {
        return purchase_details;
    }

    public void setPurchase_details(String purchase_details) {
        this.purchase_details = purchase_details;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getLocation_balance() {
        return location_balance;
    }

    public void setLocation_balance(double location_balance) {
        this.location_balance = location_balance;
    }

    public String getMpesa_txn_number() {
        return mpesa_txn_number;
    }

    public void setMpesa_txn_number(String mpesa_txn_number) {
        this.mpesa_txn_number = mpesa_txn_number;
    }

    public String getReceipt_upload() {
        return receipt_upload;
    }

    public void setReceipt_upload(String receipt_upload) {
        this.receipt_upload = receipt_upload;
    }

}

