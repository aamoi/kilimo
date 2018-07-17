package com.shamba.amoi.shambaapp.models.projects;

/**
 * Created by amoi on 27/12/2017.
 */

public class MasterPlantingPlanItem {
    private int id;
    private String plan_name;
    private String plan_ref;
    private String farm_location;
    private String farm_block;
    private String block_size;
    private String produce;
    private String produce_quantity;
    private String seedbed_date ;
    private String transplanting_date;
    private String harvesting_date;
    private String sales_date;
    private String estimated_cost;
    private String estimated_revenue;
    private String comments;



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setFarm_location(String farm_location) {
        this.farm_location = farm_location;
    }

    public String getFarm_location() {
        return farm_location;
    }

    public void setFarm_block(String farm_block) {
        this.farm_block = farm_block;
    }

    public String getFarm_block() {
        return farm_block;
    }

    public void setBlock_size(String block_size) {
        this.block_size = block_size;
    }

    public String getBlock_size() {
        return block_size;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce_quantity(String produce_quantity) {
        this.produce_quantity = produce_quantity;
    }

    public String getProduce_quantity() {
        return produce_quantity;
    }

    public void setSeedbed_date(String seedbed_date) {
        this.seedbed_date = seedbed_date;
    }

    public String getSeedbed_date() {
        return seedbed_date;
    }

    public void setTransplanting_date(String transplanting_date) {
        this.transplanting_date = transplanting_date;
    }

    public String getTransplanting_date() {
        return transplanting_date;
    }

    public void setHarvesting_date(String harvesting_date) {
        this.harvesting_date = harvesting_date;
    }

    public String getHarvesting_date() {
        return harvesting_date;
    }

    public void setSales_date(String sales_date) {
        this.sales_date = sales_date;
    }

    public String getSales_date() {
        return sales_date;
    }

    public void setEstimated_cost(String estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public String getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_revenue(String estimated_revenue) {
        this.estimated_revenue = estimated_revenue;
    }

    public String getEstimated_revenue() {
        return estimated_revenue;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }
}
