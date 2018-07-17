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
        String assignment_id;
        String resource_id;
        String plan_id;
        String phase_id;
        String task_id;
        String pay_rate_id;
        String assignment_start_date;
        String assignment_end_date;
        double quantity_worked;
        double amount_due;
        boolean complete_status;
        String comments;


    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setPay_rate_id(String pay_rate_id) {
            this.pay_rate_id = pay_rate_id;
        }

        public void setPlan_id(String plan_id) {
            this.plan_id = plan_id;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public void setPhase_id(String phase_id) {
            this.phase_id = phase_id;
        }

        public void setTask_id(String task_id) {
            this.task_id = task_id;
        }

        public void setAmount_due(double amount_due) {
            this.amount_due = amount_due;
        }

        public void setAssignment_end_date(String assignment_end_date) {
            this.assignment_end_date = assignment_end_date;
        }

        public void setAssignment_id(String assignment_id) {
            this.assignment_id = assignment_id;
        }

        public void setAssignment_start_date(String assignment_start_date) {
            this.assignment_start_date = assignment_start_date;
        }

        public void setComplete_status(boolean complete_status) {
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

        public String getPlan_id() {
            return plan_id;
        }

        public String getAssignment_end_date() {
            return assignment_end_date;
        }

        public String getAssignment_id() {
            return assignment_id;
        }

        public String getAssignment_start_date() {
            return assignment_start_date;
        }

        public String getPay_rate_id() {
            return pay_rate_id;
        }

        public String getPhase_id() {
            return phase_id;
        }

        public boolean getComplete_status() {
            return complete_status;
        }
    public String getTask_id() {
        return task_id;
    }

}
