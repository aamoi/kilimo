package com.shamba.amoi.shambaapp.models.product;

public class ProductVarietyItem {
    int id;
    String variety_name;
    int product_id;
    String comments;

    public static ProductVarietyItem selectedProductVarietyItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVariety_name() {
        return variety_name;
    }

    public void setVariety_name(String variety_name) {
        this.variety_name = variety_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
