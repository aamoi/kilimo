package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 23/01/2018.
 */

@Entity
public class Task {

    @PrimaryKey(autoGenerate = false)
            @NonNull
    String task_id;
    String planting_program_id;
    String phase_id;
    String task_name;
    double planned_days;
    double actual_days;

    String planned_start_date;
    String actual_start_date;

    String planned_end_date;
    String actual_end_date;

    String planned_people;
    String actual_people;

    String planned_assets;
    String actual_assets;

    String planned_cost;
    String actual_cost;

    String planned_others;
    String actual_others;

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public String getPhase_id() {
        return phase_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public void setActual_days(double actual_days) {
        this.actual_days = actual_days;
    }

    public void setActual_end_date(String actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public void setActual_assets(String actual_assets) {
        this.actual_assets = actual_assets;
    }

    public void setPlanned_days(double planned_days) {
        this.planned_days = planned_days;
    }

    public void setActual_cost(String actual_cost) {
        this.actual_cost = actual_cost;
    }

    public void setActual_people(String actual_people) {
        this.actual_people = actual_people;
    }

    public void setActual_start_date(String actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public void setPlanned_assets(String planned_assets) {
        this.planned_assets = planned_assets;
    }

    public void setPlanned_end_date(String planned_end_date) {
        this.planned_end_date = planned_end_date;
    }

    public void setPlanned_start_date(String planned_start_date) {
        this.planned_start_date = planned_start_date;
    }

    public void setPlanting_program_id(String planting_program_id) {
        this.planting_program_id = planting_program_id;
    }

    public void setActual_others(String actual_others) {
        this.actual_others = actual_others;
    }

    public void setPlanned_cost(String planned_cost) {
        this.planned_cost = planned_cost;
    }

    public void setPlanned_others(String planned_others) {
        this.planned_others = planned_others;
    }

    public void setPlanned_people(String planned_people) {
        this.planned_people = planned_people;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getActual_assets() {
        return actual_assets;
    }

    public String getPlanned_assets() {
        return planned_assets;
    }

    public String getTask_id() {return task_id;}

    public double getActual_days() {
        return actual_days;
    }

    public double getPlanned_days() {
        return planned_days;
    }

    public String getPlanting_program_id() {
        return planting_program_id;
    }

    public String getActual_cost() {
        return actual_cost;
    }

    public String getActual_end_date() {
        return actual_end_date;
    }

    public String getActual_others() {
        return actual_others;
    }

    public String getActual_people() {
        return actual_people;
    }

    public String getActual_start_date() {
        return actual_start_date;
    }

    public String getPlanned_cost() {
        return planned_cost;
    }

    public String getPlanned_end_date() {
        return planned_end_date;
    }

    public String getPlanned_others() {
        return planned_others;
    }

    public String getPlanned_people() {
        return planned_people;
    }

    public String getPlanned_start_date() {
        return planned_start_date;
    }

    public String getTask_name() {
        return task_name;
    }
}
