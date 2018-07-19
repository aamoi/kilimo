package com.shamba.amoi.model;

/**
 * Created by amoi on 10/07/2018.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "phase")
public class Phase {
    private static final long serialVersionUID = -3009157732242241808L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id ;
    private String  phase_name;
    private String  phase_details;
    private boolean  is_planting_phase;
    private boolean is_poultry_phase;
    private boolean is_fishing_phase;
    private boolean is_dairy_phase;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhase_name() {
        return phase_name;
    }

    public void setPhase_name(String phase_name) {
        this.phase_name = phase_name;
    }

    public String getPhase_details() {
        return phase_details;
    }

    public void setPhase_details(String phase_details) {
        this.phase_details = phase_details;
    }

    public boolean is_planting_phase() {
        return is_planting_phase;
    }

    public void setIs_planting_phase(boolean is_planting_phase) {
        this.is_planting_phase = is_planting_phase;
    }

    public boolean is_poultry_phase() {
        return is_poultry_phase;
    }

    public void setIs_poultry_phase(boolean is_poultry_phase) {
        this.is_poultry_phase = is_poultry_phase;
    }

    public boolean is_fishing_phase() {
        return is_fishing_phase;
    }

    public void setIs_fishing_phase(boolean is_fishing_phase) {
        this.is_fishing_phase = is_fishing_phase;
    }

    public boolean is_dairy_phase() {
        return is_dairy_phase;
    }

    public void setIs_dairy_phase(boolean is_dairy_phase) {
        this.is_dairy_phase = is_dairy_phase;
    }

    public Phase() {  }

    public Phase(String  phase_name,String phase_details,boolean is_planting_phase,boolean is_poultry_phase,
                 boolean is_fishing_phase,boolean is_dairy_phase) {

        this.phase_name = phase_name;
        this.phase_details = phase_details;
        this.is_planting_phase = is_planting_phase;
        this.is_poultry_phase = is_poultry_phase;
        this.is_fishing_phase = is_fishing_phase;
        this.is_dairy_phase = is_dairy_phase;
    }

    @Override
    public String toString() {
        return "Phase{" +
                "id=" + id +
                ", phase_name='" + phase_name + '\'' +
                ", phase_details='" + phase_details + '\'' +
                ", is_planting_phase='" + is_planting_phase + '\'' +
                ", is_poultry_phase='" + is_poultry_phase + '\'' +
                ", is_fishing_phase='" + is_fishing_phase + '\'' +
                ", is_dairy_phase='" + is_dairy_phase  + '\'' + '}';
    }
}
