package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by amoi on 30/12/2017.
 */
@Entity
public class PlantingProgram {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String plan_id;
    public String planting_name;
    public String planting_produce;
    public double seed_quantity;
    public String preparation_date;
    public String seedbed_date;
    public String transplanting_date;
    public String harvesting_date;
    public String sales_date;
    public String planting_location;
    public String planting_block;
    public double planting_cost;
    public double planting_revenue;

    public void setSeed_quantity(double seed_quantity) {
        this.seed_quantity = seed_quantity;
    }

    public void setPlanting_revenue(double planting_revenue) {
        this.planting_revenue = planting_revenue;
    }

    public void setPlanting_cost(double planting_cost) {
        this.planting_cost = planting_cost;
    }

    public void setPlan_id(@NonNull String plan_id) {
        this.plan_id = plan_id;
    }

    public void setSales_date(String sales_date) {
        this.sales_date = sales_date;
    }

    public void setHarvesting_date(String harvesting_date) {
        this.harvesting_date = harvesting_date;
    }

    public void setTransplanting_date(String transplanting_date) {
        this.transplanting_date = transplanting_date;
    }

    public void setSeedbed_date(String seedbed_date) {
        this.seedbed_date = seedbed_date;
    }

    public void setPlanting_block(String planting_block) {
        this.planting_block = planting_block;
    }

    public void setPlanting_location(String planting_location) {
        this.planting_location = planting_location;
    }

    public void setPlanting_produce(String planting_produce) {
        this.planting_produce = planting_produce;
    }

    public void setPlanting_name(String planting_name) {
        this.planting_name = planting_name;
    }

    public void setPreparation_date(String preparation_date) {
        this.preparation_date = preparation_date;
    }

    public double getSeed_quantity() {
        return seed_quantity;
    }

    @NonNull
    public String getPlan_id() {
        return plan_id;
    }

    public String getPreparation_date() {
        return preparation_date;
    }

    public double getPlanting_revenue() {
        return planting_revenue;
    }

    public double getPlanting_cost() {
        return planting_cost;
    }

    public String getSales_date() {
        return sales_date;
    }

    public String getHarvesting_date() {
        return harvesting_date;
    }

    public String getPlanting_block() {
        return planting_block;
    }

    public String getPlanting_location() {
        return planting_location;
    }

    public String getTransplanting_date() {
        return transplanting_date;
    }

    public String getPlanting_produce() {
        return planting_produce;
    }

    public String getSeedbed_date() {
        return seedbed_date;
    }

    public String getPlanting_name() {
        return planting_name;
    }
}
