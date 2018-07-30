package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "task")
public class Task {
    private static final long serialVersionUID = -3009157732242241303L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int project_id;
    private String task_name;
    private Date planned_start_date;
    private Date planned_end_date;
    private double planned_days;
    private int phase_id;
    private int planned_persons;
    private double estimated_cost;
    private double estimated_revenue;
    private Date actual_start_date;
    private Date actual_end_date;
    private double actual_days;
    private double actual_persons;
    private double actual_cost;
    private double actual_revenue;

    private String required_assets;
    private String required_products;
    private String details;
    private String completion_status;

    public String getCompletion_status() {
        return completion_status;
    }

    public void setCompletion_status(String completion_status) {
        this.completion_status = completion_status;
    }

    public String getRequired_assets() {
        return required_assets;
    }

    public void setRequired_assets(String required_assets) {
        this.required_assets = required_assets;
    }

    public String getRequired_products() {
        return required_products;
    }

    public void setRequired_products(String required_products) {
        this.required_products = required_products;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Date getPlanned_start_date() {
        return planned_start_date;
    }

    public void setPlanned_start_date(Date planned_start_date) {
        this.planned_start_date = planned_start_date;
    }

    public Date getPlanned_end_date() {
        return planned_end_date;
    }

    public void setPlanned_end_date(Date planned_end_date) {
        this.planned_end_date = planned_end_date;
    }

    public double getPlanned_days() {
        return planned_days;
    }

    public void setPlanned_days(double planned_days) {
        this.planned_days = planned_days;
    }

    public int getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(int phase_id) {
        this.phase_id = phase_id;
    }

    public int getPlanned_persons() {
        return planned_persons;
    }

    public void setPlanned_persons(int planned_persons) {
        this.planned_persons = planned_persons;
    }

    public double getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public double getEstimated_revenue() {
        return estimated_revenue;
    }

    public void setEstimated_revenue(double estimated_revenue) {
        this.estimated_revenue = estimated_revenue;
    }

    public Date getActual_start_date() {
        return actual_start_date;
    }

    public void setActual_start_date(Date actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public Date getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(Date actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public double getActual_days() {
        return actual_days;
    }

    public void setActual_days(double actual_days) {
        this.actual_days = actual_days;
    }

    public double getActual_persons() {
        return actual_persons;
    }

    public void setActual_persons(double actual_persons) {
        this.actual_persons = actual_persons;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Task() {
    }

    public Task(int project_id, String task_name, Date planned_start_date, Date planned_end_date, double planned_days,
                int phase_id, int planned_persons, double estimated_cost, double estimated_revenue, Date actual_start_date,
                Date actual_end_date, double actual_days, double actual_persons, double actual_cost, double actual_revenue,
                String required_assets, String required_products, String details,String completion_status) {
        this.project_id = project_id;
        this.task_name = task_name;
        this.planned_start_date = planned_start_date;
        this.planned_end_date = planned_end_date;
        this.planned_days = planned_days;
        this.phase_id = phase_id;
        this.planned_persons = planned_persons;
        this.estimated_cost = estimated_cost;
        this.estimated_revenue = estimated_revenue;
        this.actual_start_date = actual_start_date;
        this.actual_end_date = actual_end_date;
        this.actual_days = actual_days;
        this.actual_persons = actual_persons;
        this.actual_cost = actual_cost;
        this.actual_revenue = actual_revenue;
        this.required_assets = required_assets;
        this.required_products = required_products;
        this.details = details;
        this.completion_status=completion_status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", project_id='" + project_id + '\'' +
                ", task name='" + task_name + '\'' +
                ", planned_start_date='" + planned_start_date + '\'' +
                ", planned_end_date='" + planned_end_date + '\'' +
                ", planned_days='" + planned_days + '\'' +
                ", phase_id='" + phase_id + '\'' +
                ", planned_persons='" + planned_persons + '\'' +
                ", estimated_cost='" + estimated_cost +
                ", estimated_revenue='" + estimated_revenue + '\'' +
                ", actual_start_date='" + actual_start_date + '\'' +
                ", actual_end_date='" + actual_end_date + '\'' +
                ", actual_days='" + actual_days +
                ", actual_persons='" + actual_persons + '\'' +
                ", actual_cost='" + actual_cost + '\'' +
                ", actual_revenue='" + actual_revenue + '\'' +
                ", required_assets='" + required_assets + '\'' +
                ", required_products='" + required_products + '\'' +
                ", details='" + details + '\'' +
                ", completion_status='" + completion_status + '\'' +

                '}';
    }
}