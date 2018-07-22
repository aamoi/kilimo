package com.shamba.amoi.shambaapp.models.product;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
public class ProductItem {
    public static List<ProductItem> staticProductItemList;
    private int id ;
    private String product_name;
    private boolean is_asset;
    private int category_id ;
    private int uom_id ;
    private boolean is_fuel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public boolean isIs_asset() {
        return is_asset;
    }

    public void setIs_asset(boolean is_asset) {
        this.is_asset = is_asset;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUom_id() {
        return uom_id;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }

    public boolean isIs_fuel() {
        return is_fuel;
    }

    public void setIs_fuel(boolean is_fuel) {
        this.is_fuel = is_fuel;
    }

    /**
     * get product name by id.
     * @param productItems
     * @param id
     * @return
     */
    public static ProductItem getProductItemByID(List<ProductItem> productItems, int id){
        ProductItem productItem=null;

        for(int i=0;i<productItems.size();++i){
            if(productItems.get(i).getId()==id){
                productItem= productItems.get(i);
                break;
            }
        }
      return productItem;
    }
}
