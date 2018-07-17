package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amoi on 30/12/2017.
 */

@Entity
public class MasterPlantingPlan {
    @PrimaryKey(autoGenerate = true)
    private int plan_id;
    private String plan_name;
    private String plan_ref;
    private String plan_farm_location;
    private String plan_farm_block;
    private String plan_farm_block_size;
    private String plan_product_name;
    private String plan_product_seed_quantity;
    private String plan_seedbed_date;
    private String plan_transplanting_date;
    private String plan_harvesting_date;
    private String plan_estimated_cost;
    private String plan_estimated_revenue;

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_ref(String plan_ref) {
        this.plan_ref = plan_ref;
    }

    public String getPlan_ref() {
        return plan_ref;
    }

    public void setPlan_farm_location(String plan_farm_location) {
        this.plan_farm_location = plan_farm_location;
    }

    public String getPlan_farm_location() {
        return plan_farm_location;
    }

    public void setPlan_farm_block(String plan_farm_block) {
        this.plan_farm_block = plan_farm_block;
    }

    public String getPlan_farm_block() {
        return plan_farm_block;
    }

    public void setPlan_farm_block_size(String plan_farm_block_size) {
        this.plan_farm_block_size = plan_farm_block_size;
    }

    public String getPlan_farm_block_size() {
        return plan_farm_block_size;
    }

    public void setPlan_product_name(String plan_product_name) {
        this.plan_product_name = plan_product_name;
    }

    public String getPlan_product_name() {
        return plan_product_name;
    }

    public void setPlan_seedbed_date(String plan_seedbed_date) {
        this.plan_seedbed_date = plan_seedbed_date;
    }

    public String getPlan_seedbed_date() {
        return plan_seedbed_date;
    }

    public void setPlan_transplanting_date(String plan_transplanting_date) {
        this.plan_transplanting_date = plan_transplanting_date;
    }

    public String getPlan_transplanting_date() {
        return plan_transplanting_date;
    }

    public void setPlan_estimated_cost(String plan_estimated_cost) {
        this.plan_estimated_cost = plan_estimated_cost;
    }

    public String getPlan_estimated_cost() {
        return plan_estimated_cost;
    }

    public void setPlan_estimated_revenue(String plan_estimated_revenue) {
        this.plan_estimated_revenue = plan_estimated_revenue;
    }

    public String getPlan_estimated_revenue() {
        return plan_estimated_revenue;
    }

    public void setPlan_harvesting_date(String plan_harvesting_date) {
        this.plan_harvesting_date = plan_harvesting_date;
    }

    public String getPlan_harvesting_date() {
        return plan_harvesting_date;
    }

    public void setPlan_product_seed_quantity(String plan_product_seed_quantity) {
        this.plan_product_seed_quantity = plan_product_seed_quantity;
    }

    public String getPlan_product_seed_quantity() {
        return plan_product_seed_quantity;
    }
}
