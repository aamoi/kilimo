package com.shamba.amoi.shambaapp.models.projects;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 23/01/2018.
 */

public class TaskItem {
    public static List<TaskItem> staticTaskItems;
    public static TaskItem selectedTaskItem;
    private int id;
    private int project_id;
    private String task_name;
    private String planned_start_date;
    private String planned_end_date;
    private double planned_days;
    private int phase_id;
    private int planned_persons;
    private double estimated_cost;
    private double estimated_revenue;
    private String actual_start_date;
    private String actual_end_date;
    private double actual_days;
    private double actual_persons;
    private double actual_cost;
    private double actual_revenue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getPlanned_start_date() {
        return planned_start_date;
    }

    public void setPlanned_start_date(String planned_start_date) {
        this.planned_start_date = planned_start_date;
    }

    public String getPlanned_end_date() {
        return planned_end_date;
    }

    public void setPlanned_end_date(String planned_end_date) {
        this.planned_end_date = planned_end_date;
    }

    public double getPlanned_days() {
        return planned_days;
    }

    public void setPlanned_days(double planned_days) {
        this.planned_days = planned_days;
    }

    public int getPhase_id() {
        return phase_id;
    }

    public void setPhase_id(int phase_id) {
        this.phase_id = phase_id;
    }

    public int getPlanned_persons() {
        return planned_persons;
    }

    public void setPlanned_persons(int planned_persons) {
        this.planned_persons = planned_persons;
    }

    public double getEstimated_cost() {
        return estimated_cost;
    }

    public void setEstimated_cost(double estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    public double getEstimated_revenue() {
        return estimated_revenue;
    }

    public void setEstimated_revenue(double estimated_revenue) {
        this.estimated_revenue = estimated_revenue;
    }

    public String getActual_start_date() {
        return actual_start_date;
    }

    public void setActual_start_date(String actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public String getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(String actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public double getActual_days() {
        return actual_days;
    }

    public void setActual_days(double actual_days) {
        this.actual_days = actual_days;
    }

    public double getActual_persons() {
        return actual_persons;
    }

    public void setActual_persons(double actual_persons) {
        this.actual_persons = actual_persons;
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

    public static TaskItem getTaskItemById(Activity activity,int task_id){

       List<TaskItem> taskItemList=getAllTask(activity);

       TaskItem taskItem1=new TaskItem();

        for (TaskItem taskItem:taskItemList) {
            if(taskItem.getId()==task_id)
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

                taskItem.setActual_cost(db_tasks.get(count).getActual_cost());
                taskItem.setActual_days(db_tasks.get(count).getActual_days());
                taskItem.setActual_end_date(db_tasks.get(count).getActual_end_date());
                taskItem.setActual_persons(db_tasks.get(count).getActual_persons());
                taskItem.setActual_revenue(db_tasks.get(count).getActual_revenue());
                taskItem.setActual_start_date(db_tasks.get(count).getActual_start_date());
                taskItem.setEstimated_cost(db_tasks.get(count).getEstimated_cost());
                taskItem.setEstimated_revenue(db_tasks.get(count).getEstimated_revenue());
                taskItem.setId(db_tasks.get(count).getId());
                taskItem.setPhase_id(db_tasks.get(count).getPhase_id());
                taskItem.setPlanned_days(db_tasks.get(count).getPlanned_days());
                taskItem.setPlanned_end_date(db_tasks.get(count).getPlanned_end_date());
                taskItem.setPlanned_persons(db_tasks.get(count).getPlanned_persons());
                taskItem.setPlanned_start_date(db_tasks.get(count).getPlanned_start_date());
                taskItem.setProject_id(db_tasks.get(count).getProject_id());
                taskItem.setTask_name(db_tasks.get(count).getTask_name());

                taskList.add(taskItem);
            }

            TaskItem.staticTaskItems=taskList;
        }
        return taskList;
    }

    @Override
    protected void onPostExecute(List<TaskItem> taskItemList) {
//            super.onPostExecute(masterPlantingPlanItems);
    }
}
