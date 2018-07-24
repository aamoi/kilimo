package com.shamba.amoi.shambaapp.models.product;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
public class ProductCategoryItem {
    public static List<ProductCategoryItem> staticProductCategoryItemList;
    private int id;
    private String category_name ;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * get product category name by id.
     * @param productCategoryItems
     * @param id
     * @return
     */
    public static ProductCategoryItem getProductCategoryItemByID(List<ProductCategoryItem>
                                                                         productCategoryItems, int id){
        ProductCategoryItem productCategoryItem=null;

        for(int i=0;i<productCategoryItems.size();++i){
            if(productCategoryItems.get(i).getId()==id){
                productCategoryItem= productCategoryItems.get(i);
                break;
            }
        }
      return productCategoryItem;
    }
}
