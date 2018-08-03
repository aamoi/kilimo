package com.shamba.amoi.shambaapp.models.labor;

import java.util.List;

/**
 * Created by amoi on 27/12/2017.
 */
public class ResourceItem {
    public static ResourceItem selectedResourceItem;
    public static List<ResourceItem> staticResourceItemList;
    int id;
    String resource_name;
    int default_pay_rate_id;
    int location_id;
    String phone;
    String skillset;
    String resource_type;
    String details;

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

    public int getDefault_pay_rate_id() {
        return default_pay_rate_id;
    }

    public void setDefault_pay_rate_id(int default_pay_rate_id) {
        this.default_pay_rate_id = default_pay_rate_id;
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
}
