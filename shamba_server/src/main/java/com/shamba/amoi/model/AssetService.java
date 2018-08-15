package com.shamba.amoi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by amoi on 10/07/2018.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "asset_service")
public class AssetService {
    private static final long serialVersionUID = -300915773224224909L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int asset_id;
    int service_type_id;
    int service_provider_id;
    Date planned_service_start_date;
    Date planned_service_end_date;
    Date actual_service_start_date;
    Date actual_service_end_date;
    int pay_rate_id;
    double work_size;
    double service_cost;
    boolean service_completed;
    String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public int getService_type_id() {
        return service_type_id;
    }

    public void setService_type_id(int service_type_id) {
        this.service_type_id = service_type_id;
    }

    public int getService_provider_id() {
        return service_provider_id;
    }

    public void setService_provider_id(int service_provider_id) {
        this.service_provider_id = service_provider_id;
    }

    public Date getPlanned_service_start_date() {
        return planned_service_start_date;
    }

    public void setPlanned_service_start_date(Date planned_service_start_date) {
        this.planned_service_start_date = planned_service_start_date;
    }

    public Date getPlanned_service_end_date() {
        return planned_service_end_date;
    }

    public void setPlanned_service_end_date(Date planned_service_end_date) {
        this.planned_service_end_date = planned_service_end_date;
    }

    public Date getActual_service_start_date() {
        return actual_service_start_date;
    }

    public void setActual_service_start_date(Date actual_service_start_date) {
        this.actual_service_start_date = actual_service_start_date;
    }

    public Date getActual_service_end_date() {
        return actual_service_end_date;
    }

    public void setActual_service_end_date(Date actual_service_end_date) {
        this.actual_service_end_date = actual_service_end_date;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public double getWork_size() {
        return work_size;
    }

    public void setWork_size(double work_size) {
        this.work_size = work_size;
    }

    public double getService_cost() {
        return service_cost;
    }

    public void setService_cost(double service_cost) {
        this.service_cost = service_cost;
    }

    public boolean isService_completed() {
        return service_completed;
    }

    public void setService_completed(boolean service_completed) {
        this.service_completed = service_completed;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public AssetService() {
    }

    public AssetService(int asset_id, int service_type_id, int service_provider_id, Date planned_service_start_date,
            Date planned_service_end_date, Date actual_service_start_date, Date actual_service_end_date, int pay_rate_id,
            double work_size, double service_cost, boolean service_completed, String details) {

        this.asset_id = asset_id;
        this.service_type_id = service_type_id;
        this.service_provider_id = service_provider_id;
        this.planned_service_start_date = planned_service_start_date;
        this.planned_service_end_date = planned_service_end_date;
        this.actual_service_start_date = actual_service_start_date;
        this.actual_service_end_date = actual_service_end_date;
        this.pay_rate_id = pay_rate_id;
        this.work_size = work_size;
        this.service_cost = service_cost;
        this.service_completed = service_completed;
        this.details = details;
    }

    @Override
    public String toString() {
        return "AssetService{" +
                ", id='" + id + '\'' +
                ", asset_id='" + asset_id + '\'' +
                ", service_type_id='" + service_type_id + '\'' +
                ", service_provider_id='" + service_provider_id + '\'' +
                ", planned_service_start_date='" + planned_service_start_date + '\'' +
                ", planned_service_end_date='" + planned_service_end_date + '\'' +
                ", actual_service_start_date='" + actual_service_start_date + '\'' +
                ", actual_service_end_date='" + actual_service_end_date + '\'' +
                ", pay_rate_id='" + pay_rate_id + '\'' +
                ", work_size='" + work_size + '\'' +
                ", service_cost='" + service_cost + '\'' +
                ", service_completed='" + service_completed + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
