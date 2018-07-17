package com.shamba.amoi.shambaapp.models.assets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amoi on 20/12/2017.
 */
public class AssetItem {
    public static AssetItem currentAssetItem;

    private String asset_id;
    private String asset_name;
    private String asset_type;
    private String fuel_type;
    private String manufacturer;
    private String vendor;
    private String unit_of_measure;
    private String capacity;
    private Date purchase_date;
    private double purchase_value;
    private double current_value;
    private Date warrant_expiry_date;
    /**
     * Age of the asset since buying!
     */
    private double asset_age;
    private boolean active_status;
    private String description;


    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUnit_of_measure() {
        return unit_of_measure;
    }

    public void setUnit_of_measure(String unit_of_measure) {
        this.unit_of_measure = unit_of_measure;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getPurchase_value() {
        return purchase_value;
    }

    public void setPurchase_value(double purchase_value) {
        this.purchase_value = purchase_value;
    }

    public double getCurrent_value() {
        return current_value;
    }

    public void setCurrent_value(double current_value) {
        this.current_value = current_value;
    }

    public Date getWarrant_expiry_date() {
        return warrant_expiry_date;
    }

    public void setWarrant_expiry_date(Date warrant_expiry_date) {
        this.warrant_expiry_date = warrant_expiry_date;
    }

    public double getAsset_age() {
        return asset_age;
    }

    public void setAsset_age(double asset_age) {
        this.asset_age = asset_age;
    }

    public boolean isActive_status() {
        return active_status;
    }

    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Hardcode assets in the system
     * @return
     */
    public static List<AssetItem> getAssets(){

        //Name, Vendor, capacity,is_active,fuel_type.
        String[][] assets_details={
                {"Ford Tractor","Ford Kenya","4000CC","true","Diesel"},
                {"Pickup Single","Isuzu Kenya","1200CC","true","Diesel"},
                {"Mitsubishi Canter","Ford Kenya","4000CC","true","Diesel"},
                {"Water pump","Darvis & ShirtLiff","7HP-3\"","true","Petrol"},
                {"Spray pump","Tiger","1HP","true", "Manual"},};

        List<AssetItem> assets=new ArrayList<AssetItem>();

        for(int i=0;i<assets_details.length;++i){
            AssetItem assetItem=new AssetItem();
            assetItem.setAsset_name(assets_details[i][0]);
            assetItem.setManufacturer(assets_details[i][1]);
            assetItem.setCapacity(assets_details[i][2]);

            String asset_id=assets_details[i][0]+"-"+assets_details[i][1]+"-"+assets_details[i][2];
            asset_id=asset_id.replaceAll(" ","");
            assetItem.setAsset_id(asset_id);
            assetItem.setActive_status(Boolean.parseBoolean(assets_details[i][3]));
            assetItem.setFuel_type(assets_details[i][4]);

            assets.add(assetItem);
        }

        return assets;
    }
}

