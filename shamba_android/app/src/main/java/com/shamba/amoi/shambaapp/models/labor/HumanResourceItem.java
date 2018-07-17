package com.shamba.amoi.shambaapp.models.labor;

/**
 * Created by amoi on 27/12/2017.
 */

public class HumanResourceItem {
    private String resource_id;
    private String resource_name;
    private String resource_type;
    private String resource_joining_date;
    private String resource_main_skillset;
    private String resource_contract;
    private String resource_pay_rate;
    private String comments_on_resource;

    public static HumanResourceItem current_hr_resource;

    public void setComments_on_resource(String comments_on_resource) {
        this.comments_on_resource = comments_on_resource;
    }

    public void setResource_contract(String resource_contract) {
        this.resource_contract = resource_contract;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public void setResource_joining_date(String resource_joining_date) {
        this.resource_joining_date = resource_joining_date;
    }

    public void setResource_main_skillset(String resource_main_skillset) {
        this.resource_main_skillset = resource_main_skillset;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public void setResource_pay_rate(String resource_pay_rate) {
        this.resource_pay_rate = resource_pay_rate;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getResource_main_skillset() {
        return resource_main_skillset;
    }

    public String getComments_on_resource() {
        return comments_on_resource;
    }

    public String getResource_contract() {
        return resource_contract;
    }

    public String getResource_id() {
        return resource_id;
    }

    public String getResource_joining_date() {
        return resource_joining_date;
    }

    public String getResource_name() {
        return resource_name;
    }

    public String getResource_pay_rate() {
        return resource_pay_rate;
    }

    public String getResource_type() {
        return resource_type;
    }
}
