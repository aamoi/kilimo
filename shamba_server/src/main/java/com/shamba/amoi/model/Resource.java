package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 09/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "resource")
public class Resource {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    String resource_name;
    int default_pay_rate_id;
    int location_id;
    String phone;
    String skillset;
    String resource_type;
    String details;

    public int getDefault_pay_rate_id() {
        return default_pay_rate_id;
    }

    public void setDefault_pay_rate_id(int default_pay_rate_id) {
        this.default_pay_rate_id = default_pay_rate_id;
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

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Resource() {
    }

    public Resource(String resource_name,int default_pay_rate_id, int location_id, String phone, String skillset,
                    String resource_type, String details) {

        this.resource_name = resource_name;
        this.default_pay_rate_id=default_pay_rate_id;
        this.location_id = location_id;
        this.phone = phone;
        this.skillset = skillset;
        this.resource_type = resource_type;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", default_pay_rate_id='" + default_pay_rate_id + '\'' +
                ", resource_name='" + resource_name + '\'' +
                ", location_id='" + location_id + '\'' +
                ", phone='" + phone + '\'' +
                ", skillset='" + skillset + '\'' +
                ", resource_type='" + resource_type + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}