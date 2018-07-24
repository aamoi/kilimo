package com.shamba.amoi.shambaapp.models.product;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
public class ManufacturerItem {
    public static List<ManufacturerItem> staticManufacturerItemList;
    public static ManufacturerItem  selectedManufacturerItem;
    private int id;
    private String manufacturer_name;
    private String phone;
    private String email;
    private String map;
    private String directions;
    private String county;
    private String town;
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * get manufacturer by id.
     * @param manufacturerItems
     * @param id
     * @return
     */
    public static ManufacturerItem getManufacturerItemByID(
            List<ManufacturerItem> manufacturerItems, int id){
        ManufacturerItem manufacturerItem=null;

        for(int i=0;i<manufacturerItems.size();++i){
            if(manufacturerItems.get(i).getId()==id){
                manufacturerItem= manufacturerItems.get(i);
                break;
            }
        }
      return manufacturerItem;
    }

    /**
     * get manufacturer by name.
     * @param manufacturerItems
     * @param name
     * @return
     */
    public static ManufacturerItem getManufacturerItemByName(
            List<ManufacturerItem> manufacturerItems, String name){
        ManufacturerItem manufacturerItem=null;

        for(int i=0;i<manufacturerItems.size();++i){
            if(manufacturerItems.get(i).getManufacturer_name().equalsIgnoreCase(name)){
                manufacturerItem= manufacturerItems.get(i);
                break;
            }
        }
        return manufacturerItem;
    }
}
