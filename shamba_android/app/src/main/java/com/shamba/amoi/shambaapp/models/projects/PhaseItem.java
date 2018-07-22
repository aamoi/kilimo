package com.shamba.amoi.shambaapp.models.projects;

import java.util.List;

public class PhaseItem {
    public static List<PhaseItem> staticPhaseItems;
    private int id;
    private String phase_name;
    private String phase_details;
    private boolean is_planting_phase;
    private boolean is_poultry_phase;
    private boolean is_fishing_phase;
    private boolean is_dairy_phase;

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

    public boolean isIs_planting_phase() {
        return is_planting_phase;
    }

    public void setIs_planting_phase(boolean is_planting_phase) {
        this.is_planting_phase = is_planting_phase;
    }

    public boolean isIs_poultry_phase() {
        return is_poultry_phase;
    }

    public void setIs_poultry_phase(boolean is_poultry_phase) {
        this.is_poultry_phase = is_poultry_phase;
    }

    public boolean isIs_fishing_phase() {
        return is_fishing_phase;
    }

    public void setIs_fishing_phase(boolean is_fishing_phase) {
        this.is_fishing_phase = is_fishing_phase;
    }

    public boolean isIs_dairy_phase() {
        return is_dairy_phase;
    }

    public void setIs_dairy_phase(boolean is_dairy_phase) {
        this.is_dairy_phase = is_dairy_phase;
    }
}
