package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 15/02/2018.
 */
@Entity
public class TaskAssignment {
    @PrimaryKey
    @NonNull
    int id;
    int resource_id;
    int task_id;
    int pay_rate_id;
    String assignment_start_date;
    String assignment_end_date;
    double quantity_worked;
    double amount_due;
    String complete_status;
    String comments;
//    String payment_status;
//    double amount_paid;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String isComplete_status() {
        return complete_status;
    }

//    public String getPayment_status() {
//        return payment_status;
//    }
//
//    public void setPayment_status(String payment_status) {
//        this.payment_status = payment_status;
//    }

//    public double getAmount_paid() {
//        return amount_paid;
//    }

//    public void setAmount_paid(double amount_paid) {
//        this.amount_paid = amount_paid;
//    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public void setAssignment_end_date(String assignment_end_date) {
        this.assignment_end_date = assignment_end_date;
    }

    public void setAssignment_id(int id) {
        this.id = id;
    }

    public void setAssignment_start_date(String assignment_start_date) {
        this.assignment_start_date = assignment_start_date;
    }

    public void setComplete_status(String complete_status) {
        this.complete_status = complete_status;
    }

    public void setQuantity_worked(double quantity_worked) {
        this.quantity_worked = quantity_worked;
    }

    public String getComments() {
        return comments;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public double getQuantity_worked() {
        return quantity_worked;
    }


    public String getAssignment_end_date() {
        return assignment_end_date;
    }

    public int getAssignment_id() {
        return id;
    }

    public String getAssignment_start_date() {
        return assignment_start_date;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }


    public String getComplete_status() {
        return complete_status;
    }

    public int getTask_id() {
        return task_id;
    }

}
