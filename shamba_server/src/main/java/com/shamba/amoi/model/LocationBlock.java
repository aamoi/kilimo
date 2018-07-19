package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "location_block")
public class LocationBlock {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;
    private int location_id;
    private int block_id;
    private String details;
    private String location_block_name;
    private double acreage ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation_block_name() {
        return location_block_name;
    }

    public void setLocation_block_name(String location_block_name) {
        this.location_block_name = location_block_name;
    }

    public double getAcreage() {
        return acreage;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public LocationBlock() {  }

    public LocationBlock(int location_id,int block_id,String details,String location_block_name,double acreage ) {

        this.location_id = location_id;
        this.block_id = block_id;
        this.details = details;
        this.location_block_name = location_block_name;
        this.acreage = acreage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", location_id='" + location_id + '\'' +
                ", block_id='" + block_id + '\'' +
                ", details='" + details + '\'' +
                ", location_block_name='" + location_block_name + '\'' +
                ", acreage='" + acreage  + '\'' + '}';
    }
}
