package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "vendor")
public class Vendor {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String vendor_name;
    private String vendor_phone;
    private String county;
    private String town;
    private String map;
    private String email;
    private String directions;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Vendor() {
    }

    public Vendor(String vendor_name, String vendor_phone, String county, String town, String map,
                  String email, String directions) {
        this.vendor_name = vendor_name;
        this.vendor_phone = vendor_phone;
        this.county = county;
        this.town = town;
        this.map = map;
        this.email = email;
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", vendor_name='" + vendor_name + '\'' +
                ", vendor_phone='" + vendor_phone + '\'' +
                ", county='" + county + '\'' +
                ", town='" + town + '\'' +
                ", map='" + map + '\'' +
                ", email='" + email + '\'' +
                ", directions='" + directions + '\'' +
                '}';
    }
}
