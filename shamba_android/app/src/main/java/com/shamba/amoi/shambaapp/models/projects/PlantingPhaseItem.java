package com.shamba.amoi.shambaapp.models.projects;

import com.shamba.amoi.shambaapp.shareResources.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amoi on 30/01/2018.
 */

public class PlantingPhaseItem {
    public static PlantingPhaseItem selectedplantingPhaseItem;

    String phase_id;
    String phase_name;
    String stage;
    String phase_comments;


    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }

    public String getPhase_id() {
        return phase_id;
    }

    public String getPhase_comments() {
        return phase_comments;
    }

    public String getPhase_name() {
        return phase_name;
    }

    public void setPhase_comments(String phase_comments) {
        this.phase_comments = phase_comments;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public void setPhase_name(String phase_name) {
        this.phase_name = phase_name;
    }

    public static PlantingPhaseItem getPlantingPhase(String key_phase_name){
       return getPlantingPhaseItemsHashMap().get(key_phase_name);
    }

    public static HashMap<String, PlantingPhaseItem> getPlantingPhaseItemsHashMap(){

        HashMap<String, PlantingPhaseItem> plantingPhaseItemHashMap=new HashMap<>();

        List<PlantingPhaseItem> plantingPhaseItems=getPlantingPhaseDBList();
        for(int i=0;i<plantingPhaseItems.size();++i){
            plantingPhaseItemHashMap.put(plantingPhaseItems.get(i).getPhase_name(),plantingPhaseItems.get(i));
        }

        return plantingPhaseItemHashMap;
    }

    /**
     * Provides public access to all planting phases from db.
     * Currently its from list.
     * @return
     */
    public static List<PlantingPhaseItem> getPlantingPhaseDBList(){
        String[][] phases_str = {
                {"Preparation", "Early"},{"Seed Bed Management", "Early"},{"Transplanting","Middle"},
                {"Crop Maintenance", "Middle"}, {"Harvesting", "Late"}, {"Post Harvest", "Late"}};

        ArrayList<PlantingPhaseItem> phases = new ArrayList();

        for (int i = 0; i < phases_str.length; i++) {
            PlantingPhaseItem phase = new PlantingPhaseItem();
            phase.setPhase_id(phases_str[i][0]);
            phase.setPhase_name(phases_str[i][0]);
            phase.setStage(phases_str[i][1]);
            phase.setPhase_comments("");
            phases.add(phase);
        }
        return phases;
    }
}
