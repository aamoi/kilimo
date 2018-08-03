package com.shamba.amoi.shambaapp.models.labor;
import com.shamba.amoi.shambaapp.models.projects.PhaseItem;

import java.util.List;

/**
 * Created by amoi on 15/02/2018.
 */
public class PayRateItem {
    public static List<PayRateItem> staticPayRateItems;
    public static PayRateItem  selectedPayRateItem;
    int id;
    String name;
    double unit_price;
    String details;
    int uom_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUom_id() {
        return uom_id;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }

    /**
     * Get pay rate item by name.
     * @return
     */
    public static PayRateItem getPayRateItemByName(List<PayRateItem> payRateItems,
                                                   String name) {
        List<PayRateItem> payRateItemList = payRateItems;
        PayRateItem payRateItem = new PayRateItem();

        for (int i = 0; i < payRateItemList.size(); ++i) {
            if (payRateItemList.get(i).getName().equalsIgnoreCase(name)) {
                payRateItem = payRateItemList.get(i);
                break;
            }
        }
        return payRateItem;
    }
}

