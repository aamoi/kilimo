package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amoi on 14/04/2018.
 */
@Entity
public class TaskPayment {
    @PrimaryKey(autoGenerate = true)
    int task_payment_id;
    String task_assignment_id;
    int resource_id;
    String task_id;
    String program_id;
    double total_amount;
    double balance_before;
    double amount_paid;
    double balance_after;
    String payment_status;
    String payment_due_date;
    String payment_date;
    String comments;

    public String getPayment_due_date() {
        return payment_due_date;
    }

    public void setPayment_due_date(String payment_due_date) {
        this.payment_due_date = payment_due_date;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public int getTask_payment_id() {
        return task_payment_id;
    }

    public void setTask_payment_id(int task_payment_id) {
        this.task_payment_id = task_payment_id;
    }

    public String getTask_assignment_id() {
        return task_assignment_id;
    }

    public void setTask_assignment_id(String task_assignment_id) {
        this.task_assignment_id = task_assignment_id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getBalance_before() {
        return balance_before;
    }

    public void setBalance_before(double balance_before) {
        this.balance_before = balance_before;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getBalance_after() {
        return balance_after;
    }

    public void setBalance_after(double balance_after) {
        this.balance_after = balance_after;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
