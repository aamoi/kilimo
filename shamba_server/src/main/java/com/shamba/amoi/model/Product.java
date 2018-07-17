package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "product")
public class Product {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;
    private String product_name;
    boolean is_asset;
    int category_id ;
    int uom_id ;
    boolean is_fuel;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getName() {
//        return product_name;
//    }
//
//    public void setName(String name) {
//        this.product_name = name;
//    }

    public boolean is_asset() {
        return is_asset;
    }

    public void setIs_asset(boolean is_asset) {
        this.is_asset = is_asset;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUom_id() {
        return uom_id;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public boolean is_fuel() {
        return is_fuel;
    }

    public void setIs_fuel(boolean is_fuel) {
        this.is_fuel = is_fuel;
    }

    public Product() {  }

    public Product(String name,boolean is_asset,int category_id,int uom_id) {
        this.product_name = name;
        this.is_asset = is_asset;
        this.category_id = category_id;
        this.uom_id = uom_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", is_asset='" + is_asset + '\'' +
                ", is_fuel='" + is_fuel + '\'' +
                ", category id='" + category_id + '\'' +
                ", uom id='" + uom_id  + '\'' + '}';
    }
}
