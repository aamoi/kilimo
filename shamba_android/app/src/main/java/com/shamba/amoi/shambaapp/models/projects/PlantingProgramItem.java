package com.shamba.amoi.shambaapp.models.projects;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 26/12/2017.
 */

public class PlantingProgramItem {
    public static PlantingProgramItem selectedPlantingProgram;

    public String plan_id;
    public String planting_name;
    public String planting_produce;
    public double seed_quantity;
    public String preparation_date;
    public String seedbed_date;
    public String transplanting_date;
    public String harvesting_date;
    public String sales_date;
    public String planting_location;
    public String planting_block;
    public double planting_cost;
    public double planting_revenue;

    public void setSeed_quantity(double seed_quantity) {
        this.seed_quantity = seed_quantity;
    }

    public void setPlanting_revenue(double planting_revenue) {
        this.planting_revenue = planting_revenue;
    }

    public void setPlanting_cost(double planting_cost) {
        this.planting_cost = planting_cost;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public void setSales_date(String sales_date) {
        this.sales_date = sales_date;
    }

    public void setHarvesting_date(String harvesting_date) {
        this.harvesting_date = harvesting_date;
    }

    public void setTransplanting_date(String transplanting_date) {
        this.transplanting_date = transplanting_date;
    }

    public void setSeedbed_date(String seedbed_date) {
        this.seedbed_date = seedbed_date;
    }

    public void setPlanting_block(String planting_block) {
        this.planting_block = planting_block;
    }

    public void setPlanting_location(String planting_location) {
        this.planting_location = planting_location;
    }

    public void setPlanting_produce(String planting_produce) {
        this.planting_produce = planting_produce;
    }

    public void setPlanting_name(String planting_name) {
        this.planting_name = planting_name;
    }

    public void setPreparation_date(String preparation_date) {
        this.preparation_date = preparation_date;
    }

    public double getSeed_quantity() {
        return seed_quantity;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public String getPreparation_date() {
        return preparation_date;
    }

    public double getPlanting_revenue() {
        return planting_revenue;
    }

    public double getPlanting_cost() {
        return planting_cost;
    }

    public String getSales_date() {
        return sales_date;
    }

    public String getHarvesting_date() {
        return harvesting_date;
    }

    public String getPlanting_block() {
        return planting_block;
    }

    public String getPlanting_location() {
        return planting_location;
    }

    public String getTransplanting_date() {
        return transplanting_date;
    }

    public String getPlanting_produce() {
        return planting_produce;
    }

    public String getSeedbed_date() {
        return seedbed_date;
    }

    public String getPlanting_name() {
        return planting_name;
    }

    /**
     * Returns planting program based on program id supplied.
     * @param activity
     * @param key_plan_id
     * @return
     */

    public static PlantingProgramItem getPlantingProgramById(Activity activity, String key_plan_id) {

        List<PlantingProgramItem> plantingProgramItemList=getAllPlantingPrograms(activity);
        for (PlantingProgramItem plantingProgramItem:plantingProgramItemList) {
            if(plantingProgramItem.plan_id.equalsIgnoreCase(key_plan_id))
                return plantingProgramItem;
        }
        return null;
    }


    /**
     * Returns PlantingProgramItem identified by the name
     * @param activity
     * @param key_plan_name
     * @return
     */
    public static PlantingProgramItem getPlantingProgram(Activity activity, String key_plan_name) {

        Log.d("Projects|PlanItem: ", "getPlantingProgram(), " + "plan name: "+key_plan_name);


        PlantingProgramItem PlantingProgramItem = getPlantingProgramsHashMap(activity).get(key_plan_name);

        if (!PlantingProgramItem.toString().isEmpty())
            Log.d("Projects|PlanItem:", "getPlantingProgram(), " + "plan found.");
        else
            Log.d("Projects|PlanItem:", "getPlantingProgram(), " + "plan not." );

        return getPlantingProgramsHashMap(activity).get(key_plan_name);
    }

    public static HashMap<String, PlantingProgramItem> getPlantingProgramsHashMap(Activity activity) {

        HashMap<String, PlantingProgramItem> plantingProgramItemHashMap = new HashMap<>();

        List<PlantingProgramItem> plantingProgramItemList = getAllPlantingPrograms(activity);
        Log.d("Projects|PlanItem", "getPlantingProgramsHashMap(), number of plans " +
                plantingProgramItemList.size());

        for (int i = 0; i < plantingProgramItemList.size(); ++i) {
            Log.d("Projects|PlanItem", "getPlantingProgramsHashMap(), name " +plantingProgramItemList.get(i).getPlanting_name());

                    plantingProgramItemHashMap.put(plantingProgramItemList.get(i).getPlanting_name(),
                    plantingProgramItemList.get(i));
        }

        return plantingProgramItemHashMap;
    }

    /**
     * Allows public access to all planting programs in the database
     *
     * @param activity
     * @return
     */

    public static List<PlantingProgramItem> getAllPlantingPrograms(Activity activity) {

        List<PlantingProgramItem> programItemList = null;
        try {
            programItemList = new GetPlantingPrograms(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return programItemList;
    }
}

/**
 * This class locally fetches all planting programs stored in the database.
 */
class GetPlantingPrograms extends AsyncTask<Void, Void, List<PlantingProgramItem>> {
    PlantingProgramDao plantingProgramDao;
    PlantingProgram plantingProgram;
    Activity activity;
    ArrayList<PlantingProgramItem> plantingProgramItems;

    public GetPlantingPrograms(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        plantingProgramDao = db.plantingProgramDao();
        plantingProgram = new PlantingProgram();
        plantingProgramItems = new ArrayList<>();
    }

    @Override
    protected List<PlantingProgramItem> doInBackground(Void... voids) {
        List<PlantingProgram> dbList = new ArrayList<>();

        dbList = plantingProgramDao.getAllPlantingPrograms();
        for (int i = 0; i < dbList.size(); ++i) {
            PlantingProgramItem plantingProgramItem = new PlantingProgramItem();
            plantingProgramItem.setHarvesting_date(dbList.get(i).getHarvesting_date());
            plantingProgramItem.setPlan_id(dbList.get(i).getPlan_id());
            plantingProgramItem.setPlanting_block(dbList.get(i).getPlanting_block());
            plantingProgramItem.setPlanting_cost(dbList.get(i).getPlanting_cost());
            plantingProgramItem.setPlanting_location(dbList.get(i).getPlanting_location());
            plantingProgramItem.setPlanting_name(dbList.get(i).getPlanting_name());
            plantingProgramItem.setPlanting_produce(dbList.get(i).getPlanting_produce());
            plantingProgramItem.setPlanting_revenue(dbList.get(i).getPlanting_revenue());
            plantingProgramItem.setPreparation_date(dbList.get(i).getPreparation_date());
            plantingProgramItem.setSales_date(dbList.get(i).getSales_date());
            plantingProgramItem.setSeed_quantity(dbList.get(i).getSeed_quantity());
            plantingProgramItem.setSeedbed_date(dbList.get(i).getSeedbed_date());
            plantingProgramItem.setTransplanting_date(dbList.get(i).getTransplanting_date());

            plantingProgramItems.add(plantingProgramItem);
        }
        Log.d("Planting|", "Number of planting programs in the db is :" +
                String.valueOf(plantingProgramItems.size()));

        return plantingProgramItems;
    }

    public HashMap<String, PlantingProgramItem> GetPlantingProgramsHashMap() {

        HashMap<String, PlantingProgramItem> plantingProgramItemHashMap = new HashMap<>();

        for (int i = 0; i < plantingProgramItems.size(); ++i) {
            plantingProgramItemHashMap.put(plantingProgramItems.get(i).getPlanting_name(),
                    plantingProgramItems.get(i));
        }

        return plantingProgramItemHashMap;

    }

    @Override
    protected void onPostExecute(List<PlantingProgramItem> result) {
    }
}
