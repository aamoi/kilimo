package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "location")
public class Location {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String location_name;
    String location_details;
    String county;
    String town;
    String map;
    String directions;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public void setLcation_details(String location_details) {
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

    public Location() {  }

    public Location(String location_name,String location_details,String county,String town,String map,
                    String directions) {

        this.location_name = location_name;
        this.location_details = location_details;
        this.county = county;
        this.town = town;
        this.map = map;
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", location_name='" + location_name + '\'' +
                ", location_details='" + location_details + '\'' +
                ", county='" + county + '\'' +
                ", town='" + town + '\'' +
                ", directions='" + directions + '\'' +
                ", map='" + map  + '\'' + '}';
    }
}
