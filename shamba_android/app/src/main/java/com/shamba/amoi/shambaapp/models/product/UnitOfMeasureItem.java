package com.shamba.amoi.shambaapp.models.product;

import java.util.List;

public class UnitOfMeasureItem {
    public static List<UnitOfMeasureItem> staticUnitOfMeasureItemList;
    private int id;
    private String uom_name;
    private String details;
    private String symbol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUom_name() {
        return uom_name;
    }

    public void setUom_name(String uom_name) {
        this.uom_name = uom_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
