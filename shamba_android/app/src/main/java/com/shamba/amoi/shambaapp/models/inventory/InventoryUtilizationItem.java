package com.shamba.amoi.shambaapp.models.inventory;

import android.app.Activity;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.inventory.InventoryUtilization;
import com.shamba.amoi.shambaapp.db.inventory.InventoryUtilizationDao;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgram;
import com.shamba.amoi.shambaapp.db.projects.PlantingProgramDao;
import com.shamba.amoi.shambaapp.models.projects.PlantingProgramItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 01/03/2018.
 */

public class InventoryUtilizationItem {

    public String TAG=getClass().getSimpleName().toString()+"|";

    String  utilization_id;
    String  plan_name;
    String  task_phase;
    String  plan_task;
    String  inventory_quantity;
    String  inventory_utilization_date;
    String  utilization_status;
    String  comments;

    public String getUtilization_id() {
        return utilization_id;
    }

    public void setUtilization_id(String utilization_id) {
        this.utilization_id = utilization_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getTask_phase() {
        return task_phase;
    }

    public void setTask_phase(String task_phase) {
        this.task_phase = task_phase;
    }

    public String getPlan_task() {
        return plan_task;
    }

    public void setPlan_task(String plan_task) {
        this.plan_task = plan_task;
    }

    public String getInventory_quantity() {
        return inventory_quantity;
    }

    public void setInventory_quantity(String inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public String getInventory_utilization_date() {
        return inventory_utilization_date;
    }

    public void setInventory_utilization_date(String inventory_utilization_date) {
        this.inventory_utilization_date = inventory_utilization_date;
    }

    public String getUtilization_status() {
        return utilization_status;
    }

    public void setUtilization_status(String utilization_status) {
        this.utilization_status = utilization_status;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Provide public access to save InventoryUtilization item to the database!
     * @param activity
     * @param utilization_id
     * @param plan_name
     * @param task_phase
     * @param plan_task
     * @param inventory_quantity
     * @param inventory_utilization_date
     * @param utilization_status
     * @param comments
     * @return
     */
    public static boolean saveInventoryUtilization(Activity activity, String  utilization_id, String  plan_name,
                                                   String  task_phase, String  plan_task, String  inventory_quantity,
                                                   String  inventory_utilization_date, String  utilization_status,
                                                   String  comments){
        try{
            new SaveInventoryUtilization(activity,utilization_id,plan_name,task_phase,plan_task,inventory_quantity,
                    inventory_utilization_date,utilization_status,comments).execute().get();
            return true;
        }
        catch(Exception e){

            Log.d("Exception saving record","error: "+e);

            return false;
        }
    }

    /**
     * Repackages
     * @param activity
     * @return
     */

    public static  HashMap<String, InventoryUtilizationItem> getInventoryUtilizationItems(Activity activity) {
        List<InventoryUtilizationItem> inventoryUtilizationItems=null;
        try {
            inventoryUtilizationItems= new GetInventoryUtilization(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        HashMap<String, InventoryUtilizationItem> inventoryUtilizationItemHashMap = new HashMap<>();

        for (int i = 0; i < inventoryUtilizationItems.size(); ++i) {
            inventoryUtilizationItemHashMap.put(inventoryUtilizationItems.get(i).getComments(),
                    inventoryUtilizationItems.get(i));

            Log.d(new InventoryUtilizationItem().TAG, inventoryUtilizationItems.get(i).getComments());
        }

        Log.d(new InventoryUtilizationItem().TAG, "# of inventory utilization in the map is :" +
                String.valueOf(inventoryUtilizationItemHashMap.size()));

        return inventoryUtilizationItemHashMap;
    }
}

/**
 * Async task saves an inventory utilization item to the SQLite database.
 */
class SaveInventoryUtilization extends AsyncTask<Void, Void, Integer> {

    public String TAG=getClass().getSimpleName().toString()+"|";

    public Activity activity;
    public String  utilization_id;
    public String  plan_name;
    public String  task_phase;
    public String  plan_task;
    public String  inventory_quantity;
    public String  inventory_utilization_date;
    public String  utilization_status;
    public String  comments;

    InventoryUtilization inventoryUtilization;
    InventoryUtilizationDao inventoryUtilizationDao;

    public SaveInventoryUtilization(Activity activity, String  utilization_id, String  plan_name,
                                    String  task_phase, String  plan_task, String  inventory_quantity,
                                    String  inventory_utilization_date, String  utilization_status,
                                    String  comments){
        this.activity=activity;
        this.utilization_id = utilization_id;
        this.plan_name = plan_name;
        this.task_phase = task_phase;
        this.plan_task = plan_task;
        this.inventory_quantity = inventory_quantity;
        this.inventory_utilization_date = inventory_utilization_date;
        this.utilization_status = utilization_status;
        this.comments = comments;
    }
    @Override
    public void onPreExecute(){
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        inventoryUtilizationDao=db.inventoryUtilizationDao();
        inventoryUtilization=new InventoryUtilization();

        inventoryUtilization.setComments(comments);
        inventoryUtilization.setInventory_quantity(inventory_quantity);
        inventoryUtilization.setInventory_utilization_date(inventory_utilization_date);
        inventoryUtilization.setPlan_name(plan_name);
        inventoryUtilization.setPlan_task(plan_task);
        inventoryUtilization.setTask_phase(task_phase);
        inventoryUtilization.setUtilization_id(utilization_id);
        inventoryUtilization.setUtilization_status(utilization_status);
    }
    @Override
    protected Integer doInBackground(Void... voids) {
        try{
            inventoryUtilizationDao.insertInventoryUtilization(inventoryUtilization);
            Log.d(TAG, "utilization id: "+inventoryUtilization.getUtilization_id());

        }
        catch(Exception e){
            Log.d(TAG, "Error: "+e);
        }
        return null;
    }

    @Override
    public void onPostExecute(Integer i){
    }
}

/**
 * This class locally fetches all inventory utilisation stored in the database.
 */
class GetInventoryUtilization extends AsyncTask<Void, Void, List<InventoryUtilizationItem>> {

    public String TAG=new InventoryUtilizationItem().TAG;


    InventoryUtilizationDao inventoryUtilizationDao;
    InventoryUtilization inventoryUtilization;
    Activity activity;
    ArrayList<InventoryUtilizationItem> inventoryUtilizationItems;

    public GetInventoryUtilization(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        inventoryUtilizationDao = db.inventoryUtilizationDao();
        inventoryUtilization = new InventoryUtilization();
        inventoryUtilizationItems = new ArrayList<>();
    }
    @Override
    protected List<InventoryUtilizationItem> doInBackground(Void... voids) {
        List<InventoryUtilization> dbList = new ArrayList<>();

        dbList = inventoryUtilizationDao.getAllInventoryUtilization();
        for (int i = 0; i < dbList.size(); ++i) {
            InventoryUtilizationItem inventoryUtilizationItem = new InventoryUtilizationItem();
            inventoryUtilizationItems.add(inventoryUtilizationItem);
        }
        Log.d(TAG, "Number of inventory utilization is :" +
                String.valueOf(inventoryUtilizationItems.size()));

        return inventoryUtilizationItems;
    }

    @Override
    protected void onPostExecute(List<InventoryUtilizationItem> result) {
    }
}

/**
 * This class locally fetches all inventory utilisation stored in the database.
 */
class GetDBItems extends AsyncTask<Void, Void, List<?>> {

    public String TAG=new InventoryUtilizationItem().TAG;

    Dao dao;
    Object object;
    Activity activity;
    ArrayList<InventoryUtilizationItem> inventoryUtilizationItems;

    public GetDBItems(Dao dao, Object object, Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();

        inventoryUtilizationItems = new ArrayList<>();
    }
    @Override
    protected List<InventoryUtilizationItem> doInBackground(Void... voids) {
        List<InventoryUtilization> dbList = new ArrayList<>();

//        dbList = inventoryUtilizationDao.getAllInventoryUtilization();
        for (int i = 0; i < dbList.size(); ++i) {
            InventoryUtilizationItem inventoryUtilizationItem = new InventoryUtilizationItem();
            inventoryUtilizationItems.add(inventoryUtilizationItem);
        }
        Log.d(TAG, "Number of inventory utilization is :" +
                String.valueOf(inventoryUtilizationItems.size()));

        return inventoryUtilizationItems;
    }

    @Override
    protected void onPostExecute(List<?> result) {
    }
}