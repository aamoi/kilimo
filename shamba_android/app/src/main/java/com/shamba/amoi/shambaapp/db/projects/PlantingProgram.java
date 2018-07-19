package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by amoi on 30/12/2017.
 */
@Entity
public class PlantingProgram {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public int id;
    private double estimated_cost;
    private String planned_harvest_date;
    private String planned_preparation_date;
    private double estimated_revenue;
    private double seed_quantity;
    private String planned_seedbed_date;
    private String planned_sales_date;
    private String planned_transplant_date;
    private int block_id;
    private int location_id;
    private int product_id;
    private String planting_name;
    private String planting_details;
    private double actual_cost;
    private double actual_revenue;
    private double estimated_sales_quantity;
    private double actual_sales_quantity;
    private String actual_harvest_date;
    private String actual_preparation_date;
    private String actual_seedbed_date;
    private String actual_transplant_date;
    private String actual_sales_date;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public double getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public String getPlanned_harvest_date() {
        return planned_harvest_date;
    }

    public void setPlanned_harvest_date (String  planned_harvest_date) {
        this.planned_harvest_date = planned_harvest_date;
    }

    public String getPlanned_preparation_date() {
        return planned_preparation_date;
    }

    public void setPlanned_preparation_date (String  planned_preparation_date) {
        this.planned_preparation_date = planned_preparation_date;
    }

    public double getEstimated_revenue() {
        return estimated_revenue;
    }

    public void setEstimated_revenue(double estimated_revenue) {
        this.estimated_revenue = estimated_revenue;
    }

    public double getSeed_quantity() {
        return seed_quantity;
    }

    public void setSeed_quantity(double seed_quantity) {
        this.seed_quantity = seed_quantity;
    }

    public String getPlanned_seedbed_date() {
        return planned_seedbed_date;
    }

    public void setPlanned_seedbed_date (String  planned_seedbed_date) {
        this.planned_seedbed_date = planned_seedbed_date;
    }

    public String getPlanned_sales_date() {
        return planned_sales_date;
    }

    public void setPlanned_sales_date (String  planned_sales_date) {
        this.planned_sales_date = planned_sales_date;
    }

    public String getPlanned_transplant_date() {
        return planned_transplant_date;
    }

    public void setPlanned_transplant_date (String  planned_transplant_date) {
        this.planned_transplant_date = planned_transplant_date;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getPlanting_name() {
        return planting_name;
    }

    public void setPlanting_name(String planting_name) {
        this.planting_name = planting_name;
    }

    public String getPlanting_details() {
        return planting_details;
    }

    public void setPlanting_details(String planting_details) {
        this.planting_details = planting_details;
    }

    public double getActual_cost() {
        return actual_cost;
    }

    public void setActual_cost(double actual_cost) {
        this.actual_cost = actual_cost;
    }

    public double getActual_revenue() {
        return actual_revenue;
    }

    public void setActual_revenue(double actual_revenue) {
        this.actual_revenue = actual_revenue;
    }

    public double getEstimated_sales_quantity() {
        return estimated_sales_quantity;
    }

    public void setEstimated_sales_quantity(double estimated_sales_quantity) {
        this.estimated_sales_quantity = estimated_sales_quantity;
    }

    public double getActual_sales_quantity() {
        return actual_sales_quantity;
    }

    public void setActual_sales_quantity(double actual_sales_quantity) {
        this.actual_sales_quantity = actual_sales_quantity;
    }

    public String getActual_harvest_date() {
        return actual_harvest_date;
    }

    public void setActual_harvest_date (String  actual_harvest_date) {
        this.actual_harvest_date = actual_harvest_date;
    }

    public String getActual_preparation_date() {
        return actual_preparation_date;
    }

    public void setActual_preparation_date (String  actual_preparation_date) {
        this.actual_preparation_date = actual_preparation_date;
    }

    public String getActual_seedbed_date() {
        return actual_seedbed_date;
    }

    public void setActual_seedbed_date (String  actual_seedbed_date) {
        this.actual_seedbed_date = actual_seedbed_date;
    }

    public String getActual_transplant_date() {
        return actual_transplant_date;
    }

    public void setActual_transplant_date (String  actual_transplant_date) {
        this.actual_transplant_date = actual_transplant_date;
    }

    public String getActual_sales_date() {
        return actual_sales_date;
    }

    public void setActual_sales_date (String  actual_sales_date) {
        this.actual_sales_date = actual_sales_date;
    }
}
