package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 10/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "asset")
public class Asset {
    private static final long serialVersionUID = -300915773224224909L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    //    @GeneratedValue(strategy=GenerationType.IDENTITY)
    String name;
    String vendor;
    int uom_id;
    double capacity;
    double purchase_price;
    String asset_details;
    int service_frequency_in_month;
    String service_description;
    String fuel_type;
    Date purchase_date;
    double total_hours_worked;
    double hours_to_next_service;
    boolean is_serviceable;
    Date last_service_date;
    Date next_service_date;
    boolean is_serviced_upto_date;

    public Asset(){}
    
    public Asset(String name,String vendor,int uom_id,double capacity,double purchase_price,String asset_details,
            int service_frequency_in_month,String service_description,String fuel_type,Date purchase_date,
            double total_hours_worked,double hours_to_next_service,boolean is_serviceable,Date last_service_date,
            Date next_service_date,boolean is_serviced_upto_date){

        this.name = name;
        this.vendor = vendor;
        this.uom_id = uom_id;
        this.capacity = capacity;
        this.purchase_price = purchase_price;
        this.asset_details = asset_details;
        this.service_frequency_in_month = service_frequency_in_month;
        this.service_description = service_description;
        this.fuel_type = fuel_type;
        this.purchase_date = purchase_date;
        this.total_hours_worked = total_hours_worked;
        this.hours_to_next_service = hours_to_next_service;
        this.is_serviceable = is_serviceable;
        this.last_service_date = last_service_date;
        this.next_service_date = next_service_date;
        this.is_serviced_upto_date = is_serviced_upto_date;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getUom_id() {
        return uom_id;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getAsset_details() {
        return asset_details;
    }

    public void setAsset_details(String asset_details) {
        this.asset_details = asset_details;
    }

    public int getService_frequency_in_month() {
        return service_frequency_in_month;
    }

    public void setService_frequency_in_month(int service_frequency_in_month) {
        this.service_frequency_in_month = service_frequency_in_month;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getTotal_hours_worked() {
        return total_hours_worked;
    }

    public void setTotal_hours_worked(double total_hours_worked) {
        this.total_hours_worked = total_hours_worked;
    }

    public double getHours_to_next_service() {
        return hours_to_next_service;
    }

    public void setHours_to_next_service(double hours_to_next_service) {
        this.hours_to_next_service = hours_to_next_service;
    }

    public boolean is_serviceable() {
        return is_serviceable;
    }

    public void setIs_serviceable(boolean is_serviceable) {
        this.is_serviceable = is_serviceable;
    }

    public Date getLast_service_date() {
        return last_service_date;
    }

    public void setLast_service_date(Date last_service_date) {
        this.last_service_date = last_service_date;
    }

    public Date getNext_service_date() {
        return next_service_date;
    }

    public void setNext_service_date(Date next_service_date) {
        this.next_service_date = next_service_date;
    }

    public boolean is_serviced_upto_date() {
        return is_serviced_upto_date;
    }

    public void setIs_serviced_upto_date(boolean is_serviced_upto_date) {
        this.is_serviced_upto_date = is_serviced_upto_date;
    }

    @Override
    public String toString() {
        return "Asset{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", uom id='" + uom_id + '\'' +
                ", capacity='" + capacity  + '\''+
                ", purchase price='" + purchase_price + '\'' +
                ", asset details='" + asset_details + '\'' +
                ", service_frequency_in_month='" + service_frequency_in_month + '\'' +
                ", service description='" + service_description  + '\''+
                ", fuel type='" + fuel_type  + '\''+
                ", purchase date='" + purchase_date + '\'' +
                ", total hours worked='" + total_hours_worked + '\'' +
                ", total hours worked='" + hours_to_next_service + '\'' +
                ", is_serviceable='" + is_serviceable + '\'' +
                ", last service date='" + last_service_date  + '\''+
                ", next service date='" + next_service_date  + '\''+
                ", is_serviced_upto_date='" + is_serviced_upto_date  + '\''+
                '}';
    }
}
