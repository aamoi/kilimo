package com.shamba.amoi.shambaapp.models.projects;

import com.shamba.amoi.shambaapp.models.product.VendorItem;

import java.util.List;

/**
 * Created by amoi on 09/02/2018.
 */
public class LocationItem {
    public static List<LocationItem> staticLocationItemList;
    public static LocationItem selectLocationItem;
    int id;
    String location_name;
    String location_details;
    String county;
    String town;
    String map;
    String directions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_details() {
        return location_details;
    }

    public void setLocation_details(String location_details) {
        this.location_details = location_details;
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

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    /**
     * get location by id.
     * @param locationItems
     * @param id
     * @return
     */
    public static LocationItem getLocationItemById(List<LocationItem> locationItems, int id){
        LocationItem locationItem=null;

        for(int i=0;i<locationItems.size();++i){
            if(locationItems.get(i).getId()==id){
                locationItem= locationItems.get(i);
                break;
            }
        }
        return locationItem;
    }
    /**
     * get location by id.
     * @param locationItems
     * @param name
     * @return
     */
    public static LocationItem getLocationItemByName(List<LocationItem> locationItems, String name){
        LocationItem locationItem=null;

        for(int i=0;i<locationItems.size();++i){
            if(locationItems.get(i).getLocation_name().equalsIgnoreCase(name)){
                locationItem= locationItems.get(i);
                break;
            }
        }
        return locationItem;
    }
}
