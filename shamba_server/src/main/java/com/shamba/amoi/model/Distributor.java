package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "distributor")
public class Distributor {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Distributor() {
    }

    public Distributor(String distributor_name, String phone, String email, String map, String directions, String county,
                       String town, String details) {
        this.distributor_name = distributor_name;
        this.phone = phone;
        this.email = email;
        this.map = map;
        this.directions = directions;
        this.county = county;
        this.town = town;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", distributor_name='" + distributor_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", map='" + map + '\'' +
                ", directions='" + directions + '\'' +
                ", county='" + county + '\'' +
                ", town='" + town + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
