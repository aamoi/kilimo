package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "task_assignment")
public class TaskAssignment {
    private static final long serialVersionUID = -3009157732242241303L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
            int id;
    int resource_id;
    int  task_id;
    int pay_rate_id;
    Date assignment_start_date;
    Date assignment_end_date;
    double quantity_worked;
    double amount_due;
    String complete_status;
    String comments;
    String payment_status;
    double amount_paid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public Date getAssignment_start_date() {
        return assignment_start_date;
    }

    public void setAssignment_start_date(Date assignment_start_date) {
        this.assignment_start_date = assignment_start_date;
    }

    public Date getAssignment_end_date() {
        return assignment_end_date;
    }

    public void setAssignment_end_date(Date assignment_end_date) {
        this.assignment_end_date = assignment_end_date;
    }

    public double getQuantity_worked() {
        return quantity_worked;
    }

    public void setQuantity_worked(double quantity_worked) {
        this.quantity_worked = quantity_worked;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public String getComplete_status() {
        return complete_status;
    }

    public void setComplete_status(String complete_status) {
        this.complete_status = complete_status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public TaskAssignment() {
    }

    public TaskAssignment(int resource_id,int task_id,int pay_rate_id,Date assignment_start_date,
                          Date assignment_end_date,double quantity_worked,double amount_due,String complete_status,
                          String comments,String payment_status,double amount_paid) {

        this.resource_id = resource_id;
        this.task_id = task_id;
        this.pay_rate_id = pay_rate_id;
        this.assignment_start_date = assignment_start_date;
        this.assignment_end_date = assignment_end_date;
        this.quantity_worked = quantity_worked;
        this.amount_due = amount_due;
        this.complete_status = complete_status;
        this.comments = comments;
        this.payment_status = payment_status;
        this.amount_paid = amount_paid;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", resource_id='" + resource_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", pay_rate_id='" + pay_rate_id + '\'' +
                ", assignment_start_date='" + assignment_start_date + '\'' +
                ", assignment_end_date='" + assignment_end_date +
                ", quantity_worked='" + quantity_worked + '\'' +
                ", amount_due='" + amount_due + '\'' +
                ", complete_status='" + complete_status + '\'' +
                ", comments='" + comments +
                ", payment_status='" + payment_status + '\'' +
                ", amount_paid='" + amount_paid + '\'' +

                '}';
    }
}