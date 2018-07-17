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
    private int id;
    private String resource_name;
    private String phone;
    private int resource_type_id;
    private int contract_type_id;
    private int pay_rate_id;
    private String comments;
    private String skillset;
    private Date joining_date;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return resource_name;
    }

    public void setName(String name) {
        this.resource_name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getResource_type() {
        return resource_type_id;
    }

    public void setResource_type(int resource_type) {
        this.resource_type_id = resource_type;
    }

    public int getContract_type() {
        return contract_type_id;
    }

    public void setContract_type(int contract_type_id) {this.contract_type_id = contract_type_id;}

    public int getPay_rate() {
        return pay_rate_id;
    }

    public void setPay_rate(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public Resource() {  }

    public Resource( String resource_name,String phone,int resource_type_id,int contract_type_id,int pay_rate_id,
                     String comments,String skillset,Date joining_date) {
        this.resource_name = resource_name;
        this.phone = phone;
        this.resource_type_id = resource_type_id;
        this.contract_type_id = contract_type_id;
        this.pay_rate_id = pay_rate_id;
        this.comments = comments;
        this.skillset = skillset;
        this.joining_date = joining_date;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resource_name='" + resource_name + '\'' +
                ", phone='" + phone + '\'' +
                ", resource_type_id='" + resource_type_id + '\'' +
                ", contract_type_id='" + contract_type_id  + '\'' +
                ", pay_rate_id='" + pay_rate_id  + '\'' +
                ", skill_set='" + skillset  + '\'' +
                ", joining_date='" + joining_date  + '\'' +
                ", comments='" + comments  +
                '}';
    }
}