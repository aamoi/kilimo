package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "product_stock")
public class ProductStock {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private  int product_id;
    private String vendor_name;
    private String vendor_phone;
    private String vendor_location;
    private String manufacturer;
    private double purchase_quantity;
    private double purchase_price ;
    private String purchase_details;
    private Date purchase_date ;
    private double location_balance;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_phone() {
        return vendor_phone;
    }

    public void setVendor_phone(String vendor_phone) {
        this.vendor_phone = vendor_phone;
    }

    public String getVendor_location() {
        return vendor_location;
    }

    public void setVendor_location(String vendor_location) {
        this.vendor_location = vendor_location;
    }

    public String getManafacturer() {
        return manufacturer;
    }

    public void setManafacturer(String manafacturer) {
        this.manufacturer = manafacturer;
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

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getLocation_balance() {
        return location_balance;
    }

    public void setLocation_balance(double location_balance) {
        this.location_balance = location_balance;
    }

    public ProductStock() {  }

    public ProductStock(int product_id,String vendor_name,String vendor_phone,String vendor_location,
                        String manafacturer,double purchase_quantity,double purchase_price,String purchase_details,
                        Date purchase_date ,double location_balance) {
        this.product_id = product_id;
        this.vendor_name = vendor_name;
        this.vendor_phone = vendor_phone;
        this.vendor_location = vendor_location;
        this.manufacturer = manafacturer;
        this.purchase_quantity = purchase_quantity;
        this.purchase_price = purchase_price;
        this.purchase_details = purchase_details;
        this.purchase_date = purchase_date;
        this.location_balance = location_balance;
    }

    @Override
    public String toString() {
        return "Productstock{" +
                "id=" + id +
                ", product_id='" + product_id + '\'' +
                ", vendor_name='" + vendor_name + '\'' +
                ", vendor_phone='" + vendor_phone + '\'' +
                ", vendor_location='" + vendor_location + '\'' +
                ", manafacturer='" + manufacturer + '\'' +
                ", purchase_quantity='" + purchase_quantity + '\'' +
                ", purchase_price='" + purchase_price + '\'' +
                ", purchase_details='" + purchase_details  + '\''+
                ", purchase_date='" + purchase_date + '\'' +
                ", location_balance='" + location_balance + '\'' +
                + '}';

    }
}
