package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "payment")
public class Payment {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
            int id;
    int resource_id;
    int task_id;
    Date task_start_date;
    Date due_date;
    int pay_rate_id;
    double work_size;
    double amount_due;
    Date payment_date;
    String payment_status;
    double amount_paid;
    double resource_balance_due;
    String details;

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

    public Date getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(Date task_start_date) {
        this.task_start_date = task_start_date;
    }

    public Payment() {
    }

    public Payment(int resource_id, int task_id, Date task_start_date, Date due_date,int pay_rate_id,
                   double  work_size,double amount_due,Date payment_date,String payment_status, double amount_paid,
                   double resource_balance_due,String details) {

        this.resource_id = resource_id;
        this.task_id = task_id;
        this.task_start_date = task_start_date;
        this.due_date = due_date;
        this.pay_rate_id = pay_rate_id;
        this.work_size = work_size;
        this.amount_due = amount_due;
        this.payment_date = payment_date;
        this.payment_status = payment_status;
        this.amount_paid = amount_paid;
        this.resource_balance_due = resource_balance_due;
        this.details = details;
    }

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

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", resource_id='" + resource_id + '\'' +
                ", task_id='" + task_id + '\'' +
                ", task_start_date='" + task_start_date + '\'' +
                ", pay_rate_id='" + pay_rate_id + '\'' +
                ", work_size='" + work_size + '\'' +
                ", amount_due='" + amount_due + '\'' +
                ", payment_date='" + payment_date + '\'' +
                ", payment_status='" + payment_status + '\'' +
                ", amount_paid='" + amount_paid + '\'' +
                ", resource_balance_due='" + resource_balance_due + '\'' +
                ", details='" + details + '\'' + '}';
    }
}
