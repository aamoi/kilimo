package com.shamba.amoi.shambaapp.models.projects;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 26/12/2017.
 */

public class PlantingProgramItem {
    public static PlantingProgramItem selectedPlantingProgram;
    public static List<PlantingProgramItem> staticPlantingPrograms;


    public int id;
    private double estimated_cost;
    private String planned_harvest_date;
    private String planned_preparation_date;
    private double estimated_revenue;
    private double seed_quantity;
    private String planned_seedbed_date;
    private String planned_sales_date;
    private String planned_transplant_date;
    private int location_block_id;
    private int product_id;
    private String planting_name;
    private String planting_details;
    private double actual_cost;
    private double actual_revenue;
    private double estimated_sales_quantity;
    private double actual_sales_quantity;
    private String actual_harvest_date;
    private String actual_preparation_date;
    private String actual_seedbed_date;
    private String actual_transplant_date;
    private String actual_sales_date;

    public String getActual_sales_date() {
        return actual_sales_date;
    }

    public void setActual_sales_date(String actual_sales_date) {
        this.actual_sales_date = actual_sales_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public String getPlanned_harvest_date() {
        return planned_harvest_date;
    }

    public void setPlanned_harvest_date(String planned_harvest_date) {
        this.planned_harvest_date = planned_harvest_date;
    }

    public String getPlanned_preparation_date() {
        return planned_preparation_date;
    }

    public void setPlanned_preparation_date(String planned_preparation_date) {
        this.planned_preparation_date = planned_preparation_date;
    }

    public double getEstimated_revenue() {
        return estimated_revenue;
    }

    public void setEstimated_revenue(double estimated_revenue) {
        this.estimated_revenue = estimated_revenue;
    }

    public double getSeed_quantity() {
        return seed_quantity;
    }

    public void setSeed_quantity(double seed_quantity) {
        this.seed_quantity = seed_quantity;
    }

    public String getPlanned_seedbed_date() {
        return planned_seedbed_date;
    }

    public void setPlanned_seedbed_date(String planned_seedbed_date) {
        this.planned_seedbed_date = planned_seedbed_date;
    }

    public String getPlanned_sales_date() {
        return planned_sales_date;
    }

    public void setPlanned_sales_date(String planned_sales_date) {
        this.planned_sales_date = planned_sales_date;
    }

    public String getPlanned_transplant_date() {
        return planned_transplant_date;
    }

    public void setPlanned_transplant_date(String planned_transplant_date) {
        this.planned_transplant_date = planned_transplant_date;
    }

    public int getLocation_block_id() {
        return location_block_id;
    }

    public void setLocation_block_id(int location_block_id) {
        this.location_block_id = location_block_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getPlanting_name() {
        return planting_name;
    }

    public void setPlanting_name(String planting_name) {
        this.planting_name = planting_name;
    }

    public String getPlanting_details() {
        return planting_details;
    }

    public void setPlanting_details(String planting_details) {
        this.planting_details = planting_details;
    }

    public double getActual_cost() {
        return actual_cost;
    }

    public void setActual_cost(double actual_cost) {
        this.actual_cost = actual_cost;
    }

    public double getActual_revenue() {
        return actual_revenue;
    }

    public void setActual_revenue(double actual_revenue) {
        this.actual_revenue = actual_revenue;
    }

    public double getEstimated_sales_quantity() {
        return estimated_sales_quantity;
    }

    public void setEstimated_sales_quantity(double estimated_sales_quantity) {
        this.estimated_sales_quantity = estimated_sales_quantity;
    }

    public double getActual_sales_quantity() {
        return actual_sales_quantity;
    }

    public void setActual_sales_quantity(double actual_sales_quantity) {
        this.actual_sales_quantity = actual_sales_quantity;
    }

    public String getActual_harvest_date() {
        return actual_harvest_date;
    }

    public void setActual_harvest_date(String actual_harvest_date) {
        this.actual_harvest_date = actual_harvest_date;
    }

    public String getActual_preparation_date() {
        return actual_preparation_date;
    }

    public void setActual_preparation_date(String actual_preparation_date) {
        this.actual_preparation_date = actual_preparation_date;
    }

    public String getActual_seedbed_date() {
        return actual_seedbed_date;
    }

    public void setActual_seedbed_date(String actual_seedbed_date) {
        this.actual_seedbed_date = actual_seedbed_date;
    }

    public String getActual_transplant_date() {
        return actual_transplant_date;
    }

    public void setActual_transplant_date(String actual_transplant_date) {
        this.actual_transplant_date = actual_transplant_date;
    }

    /**
     * Returns planting program based on program id supplied.
     * @param activity
     * @param key_plan_id
     * @return
     */

//    public static PlantingProgramItem getPlantingProgramById(Activity activity, String key_plan_id) {
//
//        List<PlantingProgramItem> plantingProgramItemList=getAllPlantingPrograms(activity);
//        for (PlantingProgramItem plantingProgramItem:plantingProgramItemList) {
//            if(plantingProgramItem.id.equalsIgnoreCase(key_plan_id))
//                return plantingProgramItem;
//        }
//        return null;
//    }

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
            Log.d("Projects|PlanItem", "getPlantingProgramsHashMap(), name " +
                    plantingProgramItemList.get(i).getPlanting_name());

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
            plantingProgramItem.setPlanned_harvest_date(dbList.get(i).getPlanned_harvest_date());
            plantingProgramItem.setId(dbList.get(i).getId());
            plantingProgramItem.setEstimated_cost(dbList.get(i).getEstimated_cost());
            plantingProgramItem.setLocation_block_id(dbList.get(i).getLocation_block_id());
            plantingProgramItem.setPlanting_name(dbList.get(i).getPlanting_name());
            plantingProgramItem.setProduct_id(dbList.get(i).getProduct_id());
            plantingProgramItem.setEstimated_revenue(dbList.get(i).getEstimated_revenue());
            plantingProgramItem.setPlanned_preparation_date(dbList.get(i).getPlanned_preparation_date());
            plantingProgramItem.setPlanned_sales_date(dbList.get(i).getPlanned_sales_date());
            plantingProgramItem.setSeed_quantity(dbList.get(i).getSeed_quantity());
            plantingProgramItem.setPlanned_seedbed_date(dbList.get(i).getPlanned_seedbed_date());
            plantingProgramItem.setPlanned_transplant_date(dbList.get(i).getPlanned_transplant_date());

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
