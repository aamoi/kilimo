package com.shamba.amoi.shambaapp.models.product;

import java.util.List;

/**
 * Created by amoi on 13/02/2018.
 */
public class DistributorItem {
    public static List<DistributorItem> staticDistributorItems;
    public static DistributorItem selectedDistributorItem;
    private int id;
    private String distributor_name;
    private String phone;
    private String email;
    private String map;
    private String directions;
    private String county;
    private String town;
    private String details;

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
     * get distributor by id.
     * @param distributorItems
     * @param id
     * @return
     */
    public static DistributorItem getDistributorItemByID(List<DistributorItem> distributorItems,int id){
        DistributorItem distributorItem=null;

        for(int i=0;i<distributorItems.size();++i){
            if(distributorItems.get(i).getId()==id){
                distributorItem= distributorItems.get(i);
                break;
            }
        }
      return distributorItem;
    }

    /**
     * get distributor by name.
     * @param distributorItems
     * @param name
     * @return
     */
    public static DistributorItem getDistributorItemByName(List<DistributorItem> distributorItems,String name){
        DistributorItem distributorItem=null;

        for(int i=0;i<distributorItems.size();++i){
            if(distributorItems.get(i).getDistributor_name().equalsIgnoreCase(name)){
                distributorItem= distributorItems.get(i);
                break;
            }
        }
        return distributorItem;
    }
}
