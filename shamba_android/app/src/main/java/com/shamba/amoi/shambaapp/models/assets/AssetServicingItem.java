package com.shamba.amoi.shambaapp.models.assets;

/**
 * Created by amoi on 15/04/2018.
 */

public class AssetServicingItem {
    public static  AssetServicingItem currentAssetServicingItem;
    private String asset_servicing_id;
    private String assert_id;
    private String oil_stock_id;
    private String previous_servicing_date;
    private String next_servicing_date;
    private String is_serviced;
    private String servicing_date;
    private String servicing_cost;
    private String servicing_description;

    public String getAsset_servicing_id() {
        return asset_servicing_id;
    }

    public void setAsset_servicing_id(String asset_servicing_id) {
        this.asset_servicing_id = asset_servicing_id;
    }

    public String getAssert_id() {
        return assert_id;
    }

    public void setAssert_id(String assert_id) {
        this.assert_id = assert_id;
    }

    public String getOil_stock_id() {
        return oil_stock_id;
    }

    public void setOil_stock_id(String oil_stock_id) {
        this.oil_stock_id = oil_stock_id;
    }

    public String getPrevious_servicing_date() {
        return previous_servicing_date;
    }

    public void setPrevious_servicing_date(String previous_servicing_date) {
        this.previous_servicing_date = previous_servicing_date;
    }

    public String getNext_servicing_date() {
        return next_servicing_date;
    }

    public void setNext_servicing_date(String next_servicing_date) {
        this.next_servicing_date = next_servicing_date;
    }

    public String getIs_serviced() {
        return is_serviced;
    }

    public void setIs_serviced(String is_serviced) {
        this.is_serviced = is_serviced;
    }

    public String getServicing_date() {
        return servicing_date;
    }

    public void setServicing_date(String servicing_date) {
        this.servicing_date = servicing_date;
    }

    public String getServicing_cost() {
        return servicing_cost;
    }

    public void setServicing_cost(String servicing_cost) {
        this.servicing_cost = servicing_cost;
    }

    public String getServicing_description() {
        return servicing_description;
    }

    public void setServicing_description(String servicing_description) {
        this.servicing_description = servicing_description;
    }
}
