package com.shamba.amoi.shambaapp.models.labor;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.projects.Task;
import com.shamba.amoi.shambaapp.db.projects.TaskDao;
import com.shamba.amoi.shambaapp.models.projects.TaskItem;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 27/12/2017.
 */
public class ResourceItem {
    public static ResourceItem selectedResourceItem;
    public static List<ResourceItem> staticResourceItemList;
    int id;
    String resource_name;
    int default_pay_rate_id;
    int location_id;
    String phone;
    String skillset;
    String resource_type;
    String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public int getDefault_pay_rate_id() {
        return default_pay_rate_id;
    }

    public void setDefault_pay_rate_id(int default_pay_rate_id) {
        this.default_pay_rate_id = default_pay_rate_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkillset() {
        return skillset;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * Get resource by name.
     *
     * @return
     */
    public static ResourceItem getResourceByName(Activity activity, String name) {

        ResourceItem resourceItem = new ResourceItem();

        List<ResourceItem> resourceItems=new ArrayList<>();
        resourceItems=getAllResources(activity);

        for (int i = 0; i < resourceItems.size(); ++i) {
            if (resourceItems.get(i).getResource_name().equalsIgnoreCase(name))  {
                resourceItem=resourceItems.get(i);
                break;
            }
        }
        Log.d("Resource item|", resourceItem.toString());

        return resourceItem;
    }

    /**
     * Get resource by id.
     *
     * @return
     */
    public static ResourceItem getResourceByID(Activity activity, int resource_id) {

        ResourceItem resourceItem = new ResourceItem();

        List<ResourceItem> resourceItems=new ArrayList<>();
        resourceItems=getAllResources(activity);

        for (int i = 0; i < resourceItems.size(); ++i) {
            if (resourceItems.get(i).getId() == resource_id)  {
                resourceItem=resourceItems.get(i);
                break;
            }
        }
        Log.d("Resource item|", resourceItem.toString());

        return resourceItem;
    }

    /**
     * Get all resources.
     *
     * @return
     */
    public static List<ResourceItem> getAllResources(Activity activity) {

        List<ResourceItem> resourceItems = new ArrayList<>();

        try {
            resourceItems = new GetResourceList(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return resourceItems;
    }
}

/**
 * Locally gets all resources from the SQLite db.
 */
class GetResourceList extends AsyncTask<Void, Void, List<ResourceItem>> {

    TaskDao taskDao;
    List<ResourceItem> resourceItems;
    Activity activity;

    public GetResourceList(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
        taskDao = db.taskDao();
        resourceItems = new ArrayList();
    }

    @Override
    protected List<ResourceItem> doInBackground(Void... voids) {

        resourceItems = getAllResourcesFromServer();

//        List<Task> db_tasks = taskDao.getAllTasks();
//
//        if ((db_tasks.size() > 0) && (taskList.size() == 0)) {
//
//            for (int count = 0; count < db_tasks.size(); ++count) {
//                TaskItem taskItem = new TaskItem();
//
//                taskItem.setActual_cost(db_tasks.get(count).getActual_cost());
//                taskItem.setActual_days(db_tasks.get(count).getActual_days());
//                taskItem.setActual_end_date(db_tasks.get(count).getActual_end_date());
//                taskItem.setActual_persons(db_tasks.get(count).getActual_persons());
//                taskItem.setActual_revenue(db_tasks.get(count).getActual_revenue());
//                taskItem.setActual_start_date(db_tasks.get(count).getActual_start_date());
//                taskItem.setEstimated_cost(db_tasks.get(count).getEstimated_cost());
//                taskItem.setEstimated_revenue(db_tasks.get(count).getEstimated_revenue());
//                taskItem.setId(db_tasks.get(count).getId());
//                taskItem.setPhase_id(db_tasks.get(count).getPhase_id());
//                taskItem.setPlanned_days(db_tasks.get(count).getPlanned_days());
//                taskItem.setPlanned_end_date(db_tasks.get(count).getPlanned_end_date());
//                taskItem.setPlanned_persons(db_tasks.get(count).getPlanned_persons());
//                taskItem.setPlanned_start_date(db_tasks.get(count).getPlanned_start_date());
//                taskItem.setProject_id(db_tasks.get(count).getProject_id());
//                taskItem.setTask_name(db_tasks.get(count).getTask_name());
//
//                taskList.add(taskItem);
//            }
//
//            TaskItem.staticTaskItems = taskList;
//        }
        return resourceItems;
    }

    protected List<ResourceItem> getAllResourcesFromServer() {

        List<ResourceItem> resourceItems = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "resource/", "");

            Log.d("#resources pooled:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                ResourceItem resourceItem = new ResourceItem();
                JSONObject jsonObject = jArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                resourceItem.setId(id);

                int default_pay_rate_id = jsonObject.getInt("default_pay_rate_id");
                resourceItem.setDefault_pay_rate_id(default_pay_rate_id);

                int location_id = jsonObject.getInt("location_id");
                resourceItem.setLocation_id(location_id);

                String resource_name = jsonObject.getString("resource_name");
                resourceItem.setResource_name(resource_name);

                String phone = jsonObject.getString("phone");
                resourceItem.setPhone(phone);
                Log.d("Reosurce phone", phone);


                String skillset = jsonObject.getString("skillset");
                resourceItem.setSkillset(skillset);

                String resource_type = jsonObject.getString("resource_type");
                resourceItem.setResource_type(resource_type);

                String details = jsonObject.getString("details");
                resourceItem.setDetails(details);

                resourceItems.add(resourceItem);
            }

            ResourceItem.staticResourceItemList = resourceItems;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resourceItems;
    }

    @Override
    protected void onPostExecute(List<ResourceItem> resourceItems) {
//            super.onPostExecute(masterPlantingPlanItems);
    }
}
