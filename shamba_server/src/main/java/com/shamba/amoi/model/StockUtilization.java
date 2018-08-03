package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "stock_utilization")
public class StockUtilization {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  int id;
    private  int stock_id;
    private int project_id;
    private  int phase_id;
    private  int task_id;
    private  double utilized_quantity;
    private  Date utilized_date;
    private String details;

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

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(int phase_id) {
        this.phase_id = phase_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public double getUtilized_quantity() {
        return utilized_quantity;
    }

    public void setUtilized_quantity(double utilized_quantity) {
        this.utilized_quantity = utilized_quantity;
    }

    public Date getUtilized_date() {
        return utilized_date;
    }

    public void setUtilized_date(Date utilized_date) {
        this.utilized_date = utilized_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public StockUtilization() {  }

    public StockUtilization( int stock_id,int project_id,int phase_id,int task_id,double utilized_quantity,
             Date utilized_date,String details) {

        this.stock_id = stock_id;
        this.project_id = project_id;
        this.phase_id = phase_id;
        this.task_id = task_id;
        this.utilized_quantity = utilized_quantity;
        this.utilized_date = utilized_date;
        this.details = details;
    }
    @Override
    public String toString() {
        return "Productstock{" +
                "id=" + id +
                ", stock_id='" + stock_id + '\'' +
                ", project_id='" + project_id + '\'' +
                ", phase_id='" + phase_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", utilized_quantity='" + utilized_quantity + '\'' +
                ", utilized_date='" + utilized_date + '\'' +
                ", details='" + details + '\'' +
                + '}';

    }
}
