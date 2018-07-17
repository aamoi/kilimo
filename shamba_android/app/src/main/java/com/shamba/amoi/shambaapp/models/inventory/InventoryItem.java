package com.shamba.amoi.shambaapp.models.inventory;

/**
 * Created by amoi on 21/12/2017.
 */

public class InventoryItem {
    private int id;
    private String name;
    private String vendor;
    private String package_size;
    private String unit_of_measure;

    public void setId(int ID){
        id=ID;
    }
    public int getId(){return id;}

    public void setName(String inventory_name) {
        this.name = inventory_name;
    }
    public String getName() {
        return name;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getVendor() {
        return vendor;
    }

    public void setPackage_size(String package_size) {
        this.package_size = package_size;
    }
    public String getPackage_size() {return package_size;}

    public void setUnit_of_measure(String unit_of_measure) {
        this.unit_of_measure = unit_of_measure;
    }

    public String getUnit_of_measure() {
        return unit_of_measure;
    }
}
