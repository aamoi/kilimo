package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.internal.Nullable;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "stock_transaction")
public class StockTransaction {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    int stock_id;
    int utilization_id;
    String transaction_type;
    double quantity;
    double stock_balance;
    double location_stock_balance;

    public StockTransaction(int stock_id,int utilization_id,String transaction_type,double quantity,
                            double stock_balance, double location_stock_balance){

        this.stock_id = stock_id;
        this.utilization_id = utilization_id;
        this.transaction_type = transaction_type;
        this.quantity = quantity;
        this.stock_balance = stock_balance;
        this.location_stock_balance = location_stock_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getUtilization_id() {
        return utilization_id;
    }

    public void setUtilization_id(int utilization_id) {
        this.utilization_id = utilization_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getStock_balance() {
        return stock_balance;
    }

    public void setStock_balance(double stock_balance) {
        this.stock_balance = stock_balance;
    }

    public double getLocation_stock_balance() {
        return location_stock_balance;
    }

    public void setLocation_stock_balance(double location_stock_balance) {
        this.location_stock_balance = location_stock_balance;
    }

    @Override
    public String toString() {
        return "stock_transaction{" +
                "id=" + id +
                ", stock_id='" + stock_id + '\'' +
                ", utilization_id='" + utilization_id + '\'' +
                ", quantity='" + quantity + '\'' +
                ", stock_balance='" + stock_balance + '\'' +
                ", location_stock_balance='" + location_stock_balance + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                +'}';
    }
}
