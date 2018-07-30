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
    private int vendor_id;
    private int distributor_id;
    private int manufacturer_id;
    private double purchase_quantity;
    private double purchase_price ;
    private String purchase_details;
    private Date purchase_date ;
    private int location_id;
    private double location_balance;
    private String mpesa_txn_number;
    private String receipt_upload;
    private String stock_order_status;

    public String getStock_order_status() {
        return stock_order_status;
    }

    public void setStock_order_status(String stock_order_status) {
        this.stock_order_status = stock_order_status;
    }

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

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

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

    public ProductStock(int product_id,int vendor_id,int manufacturer_id,int distributor_id,double purchase_quantity,
                        double purchase_price,String purchase_details,Date purchase_date ,int location_id,
                        double location_balance,String mpesa_txn_number,String receipt_upload,String stock_order_status) {
        this.product_id = product_id;
        this.vendor_id = vendor_id;
        this.manufacturer_id = manufacturer_id;
        this.distributor_id = distributor_id;
        this.purchase_quantity = purchase_quantity;
        this.purchase_price = purchase_price;
        this.purchase_details = purchase_details;
        this.purchase_date = purchase_date;
        this.location_id=location_id;
        this.location_balance = location_balance;
        this.mpesa_txn_number = mpesa_txn_number;
        this.receipt_upload = receipt_upload;
        this.stock_order_status=stock_order_status;
    }

    @Override
    public String toString() {
        return "Productstock{" +
                "id=" + id +
                ", product_id='" + product_id + '\'' +
                ", vendor_id='" + vendor_id + '\'' +
                ", distributor_id='" + distributor_id + '\'' +
                ", manufacturer_id='" + manufacturer_id + '\'' +
                ", purchase_quantity='" + purchase_quantity + '\'' +
                ", purchase_price='" + purchase_price + '\'' +
                ", purchase_details='" + purchase_details  + '\''+
                ", purchase_date='" + purchase_date + '\'' +
                ", location_id='" + location_id + '\'' +
                ", location_balance='" + location_balance + '\'' +
                ", location_balance='" + location_balance + '\'' +
                ", mpesa_txn_number='" + mpesa_txn_number + '\'' +
                ", receipt_upload='" + receipt_upload + '\'' +
                ", stock_order_status='" + stock_order_status + '\'' +

                + '}';
    }
}
