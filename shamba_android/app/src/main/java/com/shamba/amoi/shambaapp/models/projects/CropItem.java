package com.shamba.amoi.shambaapp.models.projects;

/**
 * Created by amoi on 09/02/2018.
 */

public class CropItem {
    int crop_id;
    String crop_name;
    String crop_description;

    public void setCrop_description(String crop_description) {
        this.crop_description = crop_description;
    }

    public void setCrop_id(int crop_id) {
        this.crop_id = crop_id;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public int getCrop_id() {
        return crop_id;
    }

    public String getCrop_description() {
        return crop_description;
    }

    public String getCrop_name() {
        return crop_name;
    }
}
