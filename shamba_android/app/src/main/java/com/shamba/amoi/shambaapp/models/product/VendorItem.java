package com.shamba.amoi.shambaapp.models.product;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
public class VendorItem {
    public static List<VendorItem> staticVendorItemList;
    public static VendorItem selectedVendorItem;

    private int id;
    private String vendor_name;
    private String vendor_phone;
    private String county;
    private String town;
    private String map;
    private String email;
    private String directions;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_phone() {
        return vendor_phone;
    }

    public void setVendor_phone(String vendor_phone) {
        this.vendor_phone = vendor_phone;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * get vendor by id.
     * @param vendorItems
     * @param id
     * @return
     */
    public static VendorItem getProductItemByID(List<VendorItem> vendorItems, int id){
        VendorItem vendorItem=null;

        for(int i=0;i<vendorItems.size();++i){
            if(vendorItems.get(i).getId()==id){
                vendorItem= vendorItems.get(i);
                break;
            }
        }
      return vendorItem;
    }
    /**
     * get vendor by id.
     * @param vendorItems
     * @param name
     * @return
     */
    public static VendorItem getProductItemByName(List<VendorItem> vendorItems, String name){
        VendorItem vendorItem=null;

        for(int i=0;i<vendorItems.size();++i){
            if(vendorItems.get(i).getVendor_name().equalsIgnoreCase(name)){
                vendorItem= vendorItems.get(i);
                break;
            }
        }
        return vendorItem;
    }
}
