package com.shamba.amoi.shambaapp.models.projects;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private double planned_persons;
    private double estimated_cost;
    private double estimated_revenue;
    private String actual_start_date;
    private String actual_end_date;
    private double actual_days;
    private double actual_persons;
    private double actual_cost;
    private double actual_revenue;

    private String required_assets;
    private String required_products;
    private String details;

    private String complete_status;

    public String getComplete_status() {
        return complete_status;
    }

    public void setComplete_status(String complete_status) {
        this.complete_status = complete_status;
    }

    public String getRequired_assets() {
        return required_assets;
    }

    public void setRequired_assets(String required_assets) {
        this.required_assets = required_assets;
    }

    public String getRequired_products() {
        return required_products;
    }

    public void setRequired_products(String required_products) {
        this.required_products = required_products;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

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

    public double getPlanned_persons() {
        return planned_persons;
    }

    public void setPlanned_persons(double planned_persons) {
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

    public static TaskItem getTaskItemById(Activity activity, int task_id) {

        List<TaskItem> taskItemList = getAllTask(activity);

        TaskItem taskItem1 = new TaskItem();

        for (TaskItem taskItem : taskItemList) {
            if (taskItem.getId() == task_id)
                taskItem1 = taskItem;
            break;
        }
        return taskItem1;
    }
    /**
     * Pools all project phase task from local SQL db!
     *
     * @param activity
     * @return
     */
    public static List<TaskItem> getAllProjectPhaseTasks(Activity activity,
                                                         int project_id, int phase_id) {
        Log.d("Projects|", "project id- " + String.valueOf(project_id));
        Log.d("Projects|", "phase id- " + String.valueOf(phase_id));


        List<TaskItem> allTaskItems = new ArrayList<>();

        try {
            allTaskItems = new GetTaskList(activity).execute().get();
            Log.d("Task items|", "number of all project phase task: " +
                    String.valueOf(allTaskItems.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<TaskItem> project_phase_taskItems = new ArrayList<>();
        for (int i = 0; i < allTaskItems.size(); ++i) {
            if ((allTaskItems.get(i).getPhase_id() == phase_id) &&
                    (allTaskItems.get(i).getProject_id() == project_id)) {
                project_phase_taskItems.add(allTaskItems.get(i));
            }
        }

        Log.d("Task items|", "number of project phase task: " +
                String.valueOf(project_phase_taskItems.size()));

        return project_phase_taskItems;
    }

    /**
     * Provides public access to all tasks stored in the SQLite database.
     *
     * @param activity
     * @return
     */
    public static List<TaskItem> getAllTask(Activity activity) {

        List<TaskItem> allTaskItems = new ArrayList<>();

        try {
            allTaskItems = new GetTaskList(activity).execute().get();
            Log.d("Task items|", "number of all tasks: " + String.valueOf(allTaskItems.size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return allTaskItems;
    }

    /**
     * Get all tasks for a project.
     * @param activity
     * @return
     */
    public static List<TaskItem> getProjectTask(Activity activity, int project_id) {

        List<TaskItem> allTaskItems = new ArrayList<>();
        List<TaskItem> phaseTasks = new ArrayList<>();

        allTaskItems = getAllTask(activity);
        for (int i = 0; i < allTaskItems.size(); ++i) {
            if (allTaskItems.get(i).getProject_id() == project_id) {
                phaseTasks.add(allTaskItems.get(i));
            }
        }
        Log.d("Task items|", "number of all project tasks: " +
                String.valueOf(phaseTasks.size()));

        return phaseTasks;
    }

    /**
     * Get all tasks for a phase.
     * @param activity
     * @return
     */
    public static List<TaskItem> getPhaseTask(Activity activity, int phase_id) {

        List<TaskItem> allTaskItems = new ArrayList<>();
        List<TaskItem> phaseTasks = new ArrayList<>();

        allTaskItems = getAllTask(activity);

        Log.d("Task items|", "all  tasks #: " +
                String.valueOf(allTaskItems.size()));


        for (int i = 0; i < allTaskItems.size(); ++i) {
            if (allTaskItems.get(i).getPhase_id() == phase_id) {
                phaseTasks.add(allTaskItems.get(i));
            }
        }
        Log.d("Task items|", "number of all phase tasks: " + String.valueOf(phaseTasks.size()));

        return phaseTasks;
    }

    /**
     * Get all project tasks for a phase.
     * @param taskItems
     * @return
     */
    public static List<TaskItem> getPhaseTask(List<TaskItem> taskItems, int phase_id) {

        Log.d("Task items|", "phase id " + String.valueOf(phase_id));

        List<TaskItem> allTaskItems = new ArrayList<>();
        List<TaskItem> phaseTasks = new ArrayList<>();

        allTaskItems = taskItems;

        Log.d("Task items|", "all  tasks #: " +
                String.valueOf(allTaskItems.size()));

        for (int i = 0; i < allTaskItems.size(); ++i) {
            if (allTaskItems.get(i).getPhase_id() == phase_id) {
                phaseTasks.add(allTaskItems.get(i));
            }
        }
        Log.d("Task items|", "number of all phase tasks: " + String.valueOf(phaseTasks.size()));

        return phaseTasks;
    }

    /**
     * Get all tasks for a phase.
     *
     * @return
     */
    public static List<TaskItem> getPlantingPhaseTask(Activity activity, int project_id, int phase_id) {

        List<TaskItem> allTaskItems = new ArrayList<>();
        List<TaskItem> plantingPhaseTasks = new ArrayList<>();

        allTaskItems = getAllTask(activity);

        for (int i = 0; i < allTaskItems.size(); ++i) {
            if ((allTaskItems.get(i).getProject_id() == project_id) &&
                    (allTaskItems.get(i).getPhase_id() == phase_id)) {
                plantingPhaseTasks.add(allTaskItems.get(i));
            }
        }
        Log.d("Task items|", "number of all planting phase tasks: " +
                String.valueOf(plantingPhaseTasks.size()));

        return plantingPhaseTasks;
    }
}

/**
 * Locally gets all tasks from the SQLite db.
 */
class GetTaskList extends AsyncTask<Void, Void, List<TaskItem>> {

    TaskDao taskDao;
    List<TaskItem> taskList;
    Activity activity;

    public GetTaskList(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        taskDao = db.taskDao();
        taskList = new ArrayList();
    }

    @Override
    protected List<TaskItem> doInBackground(Void... voids) {

        taskList = getAllTasksFromServer();

        List<Task> db_tasks = taskDao.getAllTasks();

        if ((db_tasks.size() > 0) && (taskList.size() == 0)) {

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

            TaskItem.staticTaskItems = taskList;
        }
        return taskList;
    }

    protected List<TaskItem> getAllTasksFromServer() {

        List<TaskItem> taskItems = new ArrayList<>();


        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "task/", "");

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {

                TaskItem taskItem = new TaskItem();

                JSONObject jsonObject = jArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                taskItem.setId(id);

                int project_id = jsonObject.getInt("project_id");
                taskItem.setProject_id(project_id);

                String task_name = jsonObject.getString("task_name");
                taskItem.setTask_name(task_name);

                String planned_start_date = jsonObject.getString("planned_start_date");
                taskItem.setPlanned_start_date(planned_start_date);

                String planned_end_date = jsonObject.getString("planned_end_date");
                taskItem.setPlanned_end_date(planned_end_date);

                double planned_days = jsonObject.getDouble("planned_days");
                taskItem.setPlanned_days(planned_days);

                int phase_id = jsonObject.getInt("phase_id");
                taskItem.setPhase_id(phase_id);

                int planned_persons = jsonObject.getInt("planned_persons");
                taskItem.setPlanned_persons(planned_persons);

                double estimated_cost = jsonObject.getDouble("estimated_cost");
                taskItem.setEstimated_cost(estimated_cost);

                double estimated_revenue = jsonObject.getDouble("estimated_revenue");
                taskItem.setEstimated_revenue(estimated_revenue);

                String actual_start_date = jsonObject.getString("actual_start_date");
                taskItem.setActual_start_date(actual_start_date);

                String actual_end_date = jsonObject.getString("actual_end_date");
                taskItem.setActual_end_date(actual_end_date);

                double actual_days = jsonObject.getDouble("actual_days");
                taskItem.setActual_days(actual_days);

                double actual_persons = jsonObject.getDouble("actual_persons");
                taskItem.setActual_persons(actual_persons);

                double actual_cost = jsonObject.getDouble("actual_cost");
                taskItem.setActual_persons(actual_cost);

                double actual_revenue = jsonObject.getDouble("actual_revenue");
                taskItem.setActual_revenue(actual_revenue);

                String required_assets = jsonObject.getString("required_assets");
                taskItem.setRequired_assets(required_assets);

                String required_products = jsonObject.getString("required_products");
                taskItem.setRequired_products(required_products);

                String details = jsonObject.getString("details");
                taskItem.setDetails(details);

                String completion_status = jsonObject.getString("completion_status");
                taskItem.setComplete_status(completion_status);

                taskItems.add(taskItem);
            }
            TaskItem.staticTaskItems = taskItems;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taskItems;
    }

    @Override
    protected void onPostExecute(List<TaskItem> taskItemList) {
//            super.onPostExecute(masterPlantingPlanItems);
    }
}
