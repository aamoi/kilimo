package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "planting")
public class Planting {
    private static final long serialVersionUID = -300915773224224909L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private double estimated_cost;
    private Date planned_harvest_date;
    private Date planned_preparation_date;
    private double estimated_revenue;
    private double seed_quantity;
    private Date planned_seedbed_date;
    private Date planned_sales_date;
    private Date planned_transplant_date;
    private int block_id;
    private int location_id;
    private int product_id;
    private String planting_name;
    private String planting_details;
    private double actual_cost;
    private double actual_revenue;
    private double estimated_sales_quantity;
    private double actual_sales_quantity;
    private Date actual_harvest_date;
    private Date actual_preparation_date;
    private Date actual_seedbed_date;
    private Date actual_transplant_date;
    private Date actual_sales_date;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPlanting_name() {
        return planting_name;
    }

    public void setPlanting_name(String planting_name) {
        this.planting_name = planting_name;
    }

    public Planting() {  }

    public Planting(double estimated_cost,Date planned_harvest_date,Date planned_preparation_date,double estimated_revenue,
             double seed_quantity,Date planned_seedbed_date,Date planned_sales_date,Date planned_transplant_date,int block_id,
             int location_id,int product_id,String planting_name,String planting_details,double actual_cost,double actual_revenue,
             double estimated_sales_quantity,double actual_sales_quantity,Date actual_harvest_date,Date actual_preparation_date,
             Date actual_seedbed_date,Date actual_transplant_date,Date actual_sales_date) {

        this.estimated_cost = estimated_cost;
        this.planned_harvest_date = planned_harvest_date;
        this.planned_preparation_date = planned_preparation_date;
        this.estimated_revenue = estimated_revenue;
        this.seed_quantity = seed_quantity;
        this.planned_seedbed_date = planned_seedbed_date;
        this.planned_sales_date = planned_sales_date;
        this.planned_transplant_date = planned_transplant_date;
        this.block_id = block_id;
        this.location_id = location_id;
        this.product_id = product_id;
        this.planting_name = planting_name;
        this.planting_details = planting_details;
        this.actual_cost = actual_cost;
        this.actual_revenue = actual_revenue;
        this.estimated_sales_quantity = estimated_sales_quantity;
        this.actual_sales_quantity = actual_sales_quantity;
        this.actual_harvest_date = actual_harvest_date;
        this.actual_preparation_date = actual_preparation_date;
        this.actual_seedbed_date = actual_seedbed_date;
        this.actual_transplant_date = actual_transplant_date;
        this.actual_sales_date = actual_sales_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public Date getPlanned_harvest_date() {
        return planned_harvest_date;
    }

    public void setPlanned_harvest_date(Date planned_harvest_date) {
        this.planned_harvest_date = planned_harvest_date;
    }

    public Date getPlanned_preparation_date() {
        return planned_preparation_date;
    }

    public void setPlanned_preparation_date(Date planned_preparation_date) {
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

    public Date getPlanned_seedbed_date() {
        return planned_seedbed_date;
    }

    public void setPlanned_seedbed_date(Date planned_seedbed_date) {
        this.planned_seedbed_date = planned_seedbed_date;
    }

    public Date getPlanned_sales_date() {
        return planned_sales_date;
    }

    public void setPlanned_sales_date(Date planned_sales_date) {
        this.planned_sales_date = planned_sales_date;
    }

    public Date getPlanned_transplant_date() {
        return planned_transplant_date;
    }

    public void setPlanned_transplant_date(Date planned_transplant_date) {
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

    public Date getActual_harvest_date() {
        return actual_harvest_date;
    }

    public void setActual_harvest_date(Date actual_harvest_date) {
        this.actual_harvest_date = actual_harvest_date;
    }

    public Date getActual_preparation_date() {
        return actual_preparation_date;
    }

    public void setActual_preparation_date(Date actual_preparation_date) {
        this.actual_preparation_date = actual_preparation_date;
    }

    public Date getActual_seedbed_date() {
        return actual_seedbed_date;
    }

    public void setActual_seedbed_date(Date actual_seedbed_date) {
        this.actual_seedbed_date = actual_seedbed_date;
    }

    public Date getActual_transplant_date() {
        return actual_transplant_date;
    }

    public void setActual_transplant_date(Date actual_transplant_date) {
        this.actual_transplant_date = actual_transplant_date;
    }

    public Date getActual_sales_date() {
        return actual_sales_date;
    }

    public void setActual_sales_date(Date actual_sales_date) {
        this.actual_sales_date = actual_sales_date;
    }

    /**
     * Request commented out.
     * Respond in string format!
     * @return
     */
    @Override
    public String toString() {

//        {"planting_name":"KwaGeorge Cucuber","planting_details":"Maize to be sold when green!","product_id":1,"seed_quantity":6,"planned_preparation_date":"08-0-2018","planned_seedbed_date":"08-08-2018","planned_transplant_date":"08-08-2018",
//                "planned_harvest_date":"0-08-2018","planned_sales_date":"08-08-2018","location_id":2,"block_id":1,"estimated_cost":14000,
//                "estimated_sales_quantity":1000,"estimated_revenue":44000.0,
//
//                "actual_preparation_date":"08-0-2018","actual_seedbed_date":"08-08-2018","actual_transplant_date":"08-08-2018","actual_harvest_date":"0-08-2018","actual_sales_date":"08-08-2018","actual_cost":15400,
//                "actual_sales_quantity":934,"actual_revenue":40000
//        }
        return "Planting{" +
                "id=" + id +
                ", planting_name'" + planting_name + '\'' +
                ", planting_details='" + planting_details + '\'' +
                ", location_id='" + location_id + '\'' +
                ", block_id='" + block_id  + '\'' +
                ", product_id='" + product_id  + '\'' +
                ", seed_quantity='" + seed_quantity  + '\'' +
                ", estimated_sales_quantity='" + estimated_sales_quantity  + '\'' +
                ", estimated_cost='" + estimated_cost  + '\'' +
                ", estimated_revenue='" + estimated_revenue + '\'' +
                ", planned_preparation_date='" + planned_preparation_date + '\'' +
                ", planned_seedbed_date='" + planned_seedbed_date + '\'' +
                ", planned_transplant_date='" + planned_transplant_date + '\'' +
                ", planned_harvest_date='" + planned_harvest_date  + '\'' +
                ", planned_sales_date='" + planned_sales_date + '\'' +
                ", actual_sales_quantity='" + actual_sales_quantity  + '\'' +
                ", actual_cost='" + actual_cost  + '\'' +
                ", actual_revenue='" + actual_revenue + '\'' +
                ", actual_preparation_date='" + actual_preparation_date + '\'' +
                ", actual_seedbed_date='" + actual_seedbed_date + '\'' +
                ", actual_transplant_date='" + actual_transplant_date + '\'' +
                ", actual_harvest_date='" + actual_harvest_date  + '\'' +
                ", actual_sales_date='" + actual_sales_date + '\'' +
                '}';
    }
}