package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by amoi on 23/01/2018.
 */

@Entity
public class Task {
    @PrimaryKey(autoGenerate = false)
            @NonNull
    private int id;
    private int project_id;
    private String task_name;
    private String planned_start_date;
    private String planned_end_date;
    private double planned_days;
    private int phase_id;
    private int planned_persons;
    private double estimated_cost;
    private double estimated_revenue;
    private String actual_start_date;
    private String actual_end_date;
    private double actual_days;
    private double actual_persons;
    private double actual_cost;
    private double actual_revenue;

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

    public String getPlanned_start_date() {
        return planned_start_date;
    }

    public void setPlanned_start_date(String planned_start_date) {
        this.planned_start_date = planned_start_date;
    }

    public String getPlanned_end_date() {
        return planned_end_date;
    }

    public void setPlanned_end_date(String planned_end_date) {
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

    public String getActual_start_date() {
        return actual_start_date;
    }

    public void setActual_start_date(String actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public String getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(String actual_end_date) {
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
}
