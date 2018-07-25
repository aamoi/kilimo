package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "pre_order")
public class PreOrder {
    private static final long serialVersionUID = -3009157732242241303L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)

            int id;
    String pre_order_name;
    private int product_id ;
    private double quantity ;
    String details ;
    int location_id ;
    Date required_date ;
    boolean is_delivered;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPre_order_name() {
        return pre_order_name;
    }

    public void setPre_order_name(String pre_order_name) {
        this.pre_order_name = pre_order_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public Date getRequired_date() {
        return required_date;
    }

    public void setRequired_date(Date required_date) {
        this.required_date = required_date;
    }

    public boolean is_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(boolean is_delivered) {
        this.is_delivered = is_delivered;
    }

    public PreOrder() {  }

    public PreOrder(String pre_order_name,int product_id,double quantity,String details,int location_id,Date
                    required_date,boolean is_delivered) {
        this.pre_order_name = pre_order_name;
        this.product_id = product_id;
        this.quantity = quantity;
        this.details = details;
        this.location_id = location_id;
        this.required_date = required_date;
        this.is_delivered = is_delivered;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", pre_order_name='" + pre_order_name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", quantity='" + quantity + '\'' +
                ", details='" + details  + '\'' +
                ", location_id='" +location_id  + '\'' +
                ", required_date='" + required_date  + '\'' +
                ", is_delivered='" + is_delivered  + '\'' +
                '}';
    }
}