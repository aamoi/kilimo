package com.shamba.amoi.shambaapp.models.projects;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 23/01/2018.
 */

public class TaskItem {
    String id;
    String  planting_program_id;
    String phase_identity;
    String task_name;
    double planned_days;
    double actual_days;

    String planned_start_date;
    String actual_start_date;

    String planned_end_date;
    String actual_end_date;

    String planned_people;
    String actual_people;

    String planned_assets;
    String actual_assets;

    String planned_cost;
    String actual_cost;

    String planned_others;
    String actual_others;

    public void setId(String id) {
        this.id = id;
    }

    public void setPhase_identity(String phase_identity) {
        this.phase_identity = phase_identity;
    }

    public void setActual_days(double actual_days) {
        this.actual_days = actual_days;
    }

    public void setActual_end_date(String actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public void setActual_assets(String actual_assets) {
        this.actual_assets = actual_assets;
    }

    public void setPlanned_days(double planned_days) {
        this.planned_days = planned_days;
    }

    public void setActual_cost(String actual_cost) {
        this.actual_cost = actual_cost;
    }

    public void setActual_people(String actual_people) {
        this.actual_people = actual_people;
    }

    public void setActual_start_date(String actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public void setPlanned_assets(String planned_assets) {
        this.planned_assets = planned_assets;
    }

    public void setPlanned_end_date(String planned_end_date) {
        this.planned_end_date = planned_end_date;
    }

    public void setPlanned_start_date(String planned_start_date) {
        this.planned_start_date = planned_start_date;
    }

    public void setPlanting_program_id(String planting_program_id) {
        this.planting_program_id = planting_program_id;
    }

    public void setActual_others(String actual_others) {
        this.actual_others = actual_others;
    }

    public void setPlanned_cost(String planned_cost) {
        this.planned_cost = planned_cost;
    }

    public void setPlanned_others(String planned_others) {
        this.planned_others = planned_others;
    }

    public void setPlanned_people(String planned_people) {
        this.planned_people = planned_people;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getActual_assets() {
        return actual_assets;
    }

    public String getPlanned_assets() {
        return planned_assets;
    }

    public String getId() {
        return id;
    }

    public double getActual_days() {
        return actual_days;
    }

    public double getPlanned_days() {
        return planned_days;
    }

    public String getPlanting_program_id() {
        return planting_program_id;
    }

    public String getActual_cost() {
        return actual_cost;
    }

    public String getActual_end_date() {
        return actual_end_date;
    }

    public String getActual_others() {
        return actual_others;
    }

    public String getActual_people() {
        return actual_people;
    }

    public String getActual_start_date() {
        return actual_start_date;
    }

    public String getPhase_identity() {return phase_identity;}

    public String getPlanned_cost() {
        return planned_cost;
    }

    public String getPlanned_end_date() {
        return planned_end_date;
    }

    public String getPlanned_others() {
        return planned_others;
    }

    public String getPlanned_people() {
        return planned_people;
    }

    public String getPlanned_start_date() {
        return planned_start_date;
    }

    public String getTask_name() {
        return task_name;
    }

    public static TaskItem getTaskItem(Activity activity,String key_task_name){
        return getAllTaskHashMap(activity).get(key_task_name);
    }

    public static TaskItem getTaskItemById(Activity activity,String task_id){

       List<TaskItem> taskItemList=getAllTask(activity);

       TaskItem taskItem1=new TaskItem();

        for (TaskItem taskItem:taskItemList) {
            if(taskItem.getId().equalsIgnoreCase(task_id))
                taskItem1=taskItem;
            break;
        }
        return taskItem1;
    }

    /**
     * Provide public access to all db tasks packaged in hashmap
     * @param activity
     * @return
     */
    public static HashMap<String,TaskItem> getAllTaskHashMap(Activity activity){
        HashMap<String, TaskItem> taskItemHashMap=new HashMap<>();

        List<TaskItem> taskItemList=getAllTask(activity);
        for(int i=0;i<taskItemList.size();++i){
            taskItemHashMap.put(taskItemList.get(i).getTask_name(),
                    taskItemList.get(i));
        }

        Log.d("number of tasks", String.valueOf(taskItemHashMap.size()));
        return taskItemHashMap;

    }

    /**
     * Provides public access to all tasks stored in the SQLite database.
     * @param activity
     * @return
     */
    public static List<TaskItem> getAllTask(Activity activity){
        List<TaskItem> taskItemList=null;

        try {
            taskItemList= new GetTaskList(activity).execute().get();
            Log.d("Projects|", "number of task: "+String.valueOf(taskItemList.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return taskItemList;

    }
}

/**
 * Locally gets all tasks from the SQLite db.
 */
class GetTaskList extends AsyncTask<Void, Void, List<TaskItem>> {

    TaskDao taskDao;
    List<TaskItem> taskList;
    Activity activity;

    public GetTaskList(Activity activity){
        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db= new DBAdaptor(activity).getDB();
        taskDao=db.taskDao();
        taskList=new ArrayList();
    }

    @Override
    protected List<TaskItem> doInBackground(Void... voids) {

        List<Task> db_tasks = taskDao.getAllTasks();

        if (db_tasks.size() > 0){

            for (int count = 0; count < db_tasks.size(); ++count) {
                TaskItem taskItem = new TaskItem();
                taskItem.setActual_assets(db_tasks.get(count).getActual_assets());
                taskItem.setActual_cost(db_tasks.get(count).getActual_cost());
                taskItem.setActual_days(db_tasks.get(count).getActual_days());
                taskItem.setActual_end_date(db_tasks.get(count).getActual_end_date());
                taskItem.setActual_others(db_tasks.get(count).getActual_others());
                taskItem.setActual_people(db_tasks.get(count).getActual_people());
                taskItem.setActual_start_date(db_tasks.get(count).getActual_start_date());
                taskItem.setId(db_tasks.get(count).getTask_id());
                taskItem.setPhase_identity(db_tasks.get(count).getPhase_id());
                taskItem.setPlanned_assets(db_tasks.get(count).getPlanned_assets());
                taskItem.setPlanned_cost(db_tasks.get(count).getPlanned_cost());
                taskItem.setPlanned_days(db_tasks.get(count).getPlanned_days());
                taskItem.setPlanned_end_date(db_tasks.get(count).getPlanned_end_date());
                taskItem.setPlanned_others(db_tasks.get(count).getPlanned_others());
                taskItem.setPlanned_people(db_tasks.get(count).getPlanned_people());
                taskItem.setPlanned_start_date(db_tasks.get(count).getPlanned_start_date());
                taskItem.setPlanting_program_id(db_tasks.get(count).getPlanting_program_id());
                taskItem.setTask_name(db_tasks.get(count).getTask_name());
                taskList.add(taskItem);
            }
        }

        return taskList;
    }

    @Override
    protected void onPostExecute(List<TaskItem> taskItemList) {
//            super.onPostExecute(masterPlantingPlanItems);
    }
}
