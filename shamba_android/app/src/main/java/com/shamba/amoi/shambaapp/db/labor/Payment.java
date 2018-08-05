package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
/**
 * Created by amoi on 14/04/2018.
 */
@Entity
public class Payment {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    int id;
    int resource_id;
    int task_id;
    String task_start_date;
    int pay_rate_id;
    double work_size;
    String due_date;
    double amount_due;
    String payment_date;
    String payment_status;
    double amount_paid;
    double resource_balance_due;
    String details;
    String task_complete_status;

    public String getTask_complete_status() {
        return task_complete_status;
    }

    public void setTask_complete_status(String task_complete_status) {
        this.task_complete_status = task_complete_status;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public double getWork_size() {
        return work_size;
    }

    public void setWork_size(double work_size) {
        this.work_size = work_size;
    }

    public String getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(String task_start_date) {
        this.task_start_date = task_start_date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getResource_balance_due() {
        return resource_balance_due;
    }

    public void setResource_balance_due(double resource_balance_due) {
        this.resource_balance_due = resource_balance_due;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
