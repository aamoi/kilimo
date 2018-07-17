package com.shamba.amoi.shambaapp.db.inventory;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 01/03/2018.
 */
@Entity
public class InventoryUtilization {
        @PrimaryKey
        @NonNull
        String  utilization_id;
        String  plan_name;
        String  task_phase;
        String  plan_task;
        String  inventory_quantity;
        String  inventory_utilization_date;
        String  utilization_status;
        String  comments;

        public String getUtilization_id() {
            return utilization_id;
        }

        public void setUtilization_id(String utilization_id) {
            this.utilization_id = utilization_id;
        }

        public String getPlan_name() {
            return plan_name;
        }

        public void setPlan_name(String plan_name) {
            this.plan_name = plan_name;
        }

        public String getTask_phase() {
            return task_phase;
        }

        public void setTask_phase(String task_phase) {
            this.task_phase = task_phase;
        }

        public String getPlan_task() {
            return plan_task;
        }

        public void setPlan_task(String plan_task) {
            this.plan_task = plan_task;
        }

        public String getInventory_quantity() {
            return inventory_quantity;
        }

        public void setInventory_quantity(String inventory_quantity) {
            this.inventory_quantity = inventory_quantity;
        }

        public String getInventory_utilization_date() {
            return inventory_utilization_date;
        }

        public void setInventory_utilization_date(String inventory_utilization_date) {
            this.inventory_utilization_date = inventory_utilization_date;
        }

        public String getUtilization_status() {
            return utilization_status;
        }

        public void setUtilization_status(String utilization_status) {
            this.utilization_status = utilization_status;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }

