package com.shamba.amoi.shambaapp.models.power;

import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amoi on 27/12/2017.
 */

public class PowerSourceItem {

    public static PowerSourceItem currentPowerSourceItem;
    private String ps_id;
    String power_source_name;
    String power_source_unitOfMeasure;
    String power_source_use;
    String comments_on_power_source;

    public void setComments_on_power_source(String comments_on_power_source) {
        this.comments_on_power_source = comments_on_power_source;
    }

    public void setPower_source_name(String power_source_name) {
        this.power_source_name = power_source_name;
    }

    public void setPower_source_unitOfMeasure(String power_source_unitOfMeasure) {
        this.power_source_unitOfMeasure = power_source_unitOfMeasure;
    }

    public void setPower_source_use(String power_source_use) {
        this.power_source_use = power_source_use;
    }

    public void setPs_id(String ps_id) {
        this.ps_id = ps_id;
    }

    public String getPs_id() {
        return ps_id;
    }

    public String getComments_on_power_source() {
        return comments_on_power_source;
    }

    public String getPower_source_name() {
        return power_source_name;
    }

    public String getPower_source_unitOfMeasure() {
        return power_source_unitOfMeasure;
    }

    public String getPower_source_use() {
        return power_source_use;
    }

    public static List<PowerSourceItem> getPowerSourceList(){
        String product_type= BaseFragment.getProductType().get("Fuel");
        String[] fuels={"Diesel", "Petrol","Diesel Oil","Petrol Oil", "Gas","Charcoal","FireWood"};
        ArrayList< PowerSourceItem> powerSourceItems=new ArrayList<>();

        for (int i = 0; i < fuels.length; i++) {
            PowerSourceItem powerSourceItem = new PowerSourceItem();
            powerSourceItem.setPs_id(String.valueOf(i));
            powerSourceItem.setPower_source_name(fuels[i]);
            powerSourceItem.setPower_source_unitOfMeasure("Litres");
            powerSourceItem.setPower_source_use("Water pump, light, Electricity,Spray pump, Maintainance");
            powerSourceItems.add(powerSourceItem);
        }

        return powerSourceItems;
    }


}
