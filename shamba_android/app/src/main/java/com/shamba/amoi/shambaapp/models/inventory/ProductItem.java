package com.shamba.amoi.shambaapp.models.inventory;

/**
 * Created by amoi on 13/02/2018.
 */

public class ProductItem {
    String product_id;
    String product_name;
    String product_type;
    String unit_of_measure;
    String comments;

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setUnit_of_measure(String unit_of_measure) {this.unit_of_measure = unit_of_measure;}

    public String getComments() {
        return comments;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getUnit_of_measure() {
        return unit_of_measure;
    }
}
