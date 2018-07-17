package com.shamba.amoi.shambaapp.models.projects;

/**
 * Created by amoi on 09/02/2018.
 */

public class LocationItem {
    int location_id;
    String location_name;
    String ward;
    String sub_county;
    String county;
    String country;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public void setSub_county(String sub_county) {
        this.sub_county = sub_county;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public int getLocation_id() {
        return location_id;
    }

    public String getCountry() {
        return country;
    }

    public String getCounty() {
        return county;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getSub_county() {
        return sub_county;
    }

    public String getWard() {
        return ward;
    }
}
