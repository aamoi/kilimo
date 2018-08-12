package com.shamba.amoi.shambaapp.db.assets;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by amoi on 31/12/2017.
 */
@Entity
public class Asset {
    @PrimaryKey
    @NonNull
    int id;
    String name;
    int capacity_uom_id;
    double capacity_quantity;
    String is_serviceable;
    String is_active;
    String is_decommissioned;
    int service_type_id;
    int service_frequency_uom_id;
    double service_frequency_quantity;
    int fuel_product_id;
    String purchase_date;
    double purchase_price;
    int vendor_id;
    int distributor_id;
    int manufacturer_id;
    int mileage_uom_id;
    double total_mileage_quantity;
    String last_service_date;
    double mileage_to_next_service;
    String next_service_date;
    String is_serviced_upto_date;
    String asset_details;

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

    public int getCapacity_uom_id() {
        return capacity_uom_id;
    }

    public void setCapacity_uom_id(int capacity_uom_id) {
        this.capacity_uom_id = capacity_uom_id;
    }

    public double getCapacity_quantity() {
        return capacity_quantity;
    }

    public void setCapacity_quantity(double capacity_quantity) {
        this.capacity_quantity = capacity_quantity;
    }

    public String getIs_serviceable() {
        return is_serviceable;
    }

    public void setIs_serviceable(String is_serviceable) {
        this.is_serviceable = is_serviceable;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getIs_decommissioned() {
        return is_decommissioned;
    }

    public void setIs_decommissioned(String is_decommissioned) {
        this.is_decommissioned = is_decommissioned;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public int getService_frequency_uom_id() {
        return service_frequency_uom_id;
    }

    public void setService_frequency_uom_id(int service_frequency_uom_id) {
        this.service_frequency_uom_id = service_frequency_uom_id;
    }

    public double getService_frequency_quantity() {
        return service_frequency_quantity;
    }

    public void setService_frequency_quantity(double service_frequency_quantity) {
        this.service_frequency_quantity = service_frequency_quantity;
    }

    public int getFuel_product_id() {
        return fuel_product_id;
    }

    public void setFuel_product_id(int fuel_product_id) {
        this.fuel_product_id = fuel_product_id;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getDistributor_id() {
        return distributor_id;
    }

    public void setDistributor_id(int distributor_id) {
        this.distributor_id = distributor_id;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public int getMileage_uom_id() {
        return mileage_uom_id;
    }

    public void setMileage_uom_id(int mileage_uom_id) {
        this.mileage_uom_id = mileage_uom_id;
    }

    public double getTotal_mileage_quantity() {
        return total_mileage_quantity;
    }

    public void setTotal_mileage_quantity(double total_mileage_quantity) {
        this.total_mileage_quantity = total_mileage_quantity;
    }

    public String getLast_service_date() {
        return last_service_date;
    }

    public void setLast_service_date(String last_service_date) {
        this.last_service_date = last_service_date;
    }

    public double getMileage_to_next_service() {
        return mileage_to_next_service;
    }

    public void setMileage_to_next_service(double mileage_to_next_service) {
        this.mileage_to_next_service = mileage_to_next_service;
    }

    public String getNext_service_date() {
        return next_service_date;
    }

    public void setNext_service_date(String next_service_date) {
        this.next_service_date = next_service_date;
    }

    public String getIs_serviced_upto_date() {
        return is_serviced_upto_date;
    }

    public void setIs_serviced_upto_date(String is_serviced_upto_date) {
        this.is_serviced_upto_date = is_serviced_upto_date;
    }

    public String getAsset_details() {
        return asset_details;
    }

    public void setAsset_details(String asset_details) {
        this.asset_details = asset_details;
    }
}
