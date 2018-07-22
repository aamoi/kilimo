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

    public Vendor() {  }

    public Vendor(String vendor_name,String vendor_phone,String county,String town,String map,
                  String email,String directions) {
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
                ", email='" + email  + '\'' +
                ", directions='" + directions  + '\'' +
                '}';
    }
}
