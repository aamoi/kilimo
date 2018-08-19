package com.shamba.amoi.shambaapp.models.labor;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;
import com.shamba.amoi.shambaapp.models.product.StockUtilizationItem;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 15/02/2018.
 */
public class TaskAssignmentItem {
    public static List<TaskAssignmentItem> staticTaskAssignmentItem;
    public static TaskAssignmentItem selectedTaskAssignmentItem;
    int id;
    int resource_id;
    int task_id;
    int service_id;
    int pay_rate_id;
    String assignment_start_date;
    String assignment_end_date;
    double quantity_worked;
    double amount_due;
    String complete_status;
    String comments;

    public static List<TaskAssignmentItem> getAllTaskAssignments(Activity activity) {

        List<TaskAssignmentItem> assignmentItemList = new ArrayList<>();

        try {
            assignmentItemList = new GetTaskAssignmentList(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Projects|", "number of task assignments from db is: " +
                assignmentItemList.size());

        return assignmentItemList;
    }

    /**
     * Get all  resource assignments.
     * @param activity
     * @return
     */
    public static List<TaskAssignmentItem> getAssignmentsByResource(Activity activity, int resource_id) {

        List<TaskAssignmentItem> allTaskAssignments = getAllTaskAssignments(activity);
        List<TaskAssignmentItem> resourceTaskAssignments = new ArrayList<>();

        for (int i = 0; i < allTaskAssignments.size(); ++i) {
            if (allTaskAssignments.get(i).getResource_id() == resource_id) {
                resourceTaskAssignments.add(allTaskAssignments.get(i));
            }
        }

        return resourceTaskAssignments;
    }
    //    String payment_status;
//    double amount_paid;

    /**
     * Get all assignments.
     *
     * @param activity
     * @return
     */
    public static List<TaskAssignmentItem> getAllAssignments(Activity activity) {

        List<TaskAssignmentItem> taskAssignmentItems = new ArrayList<>();

        try {
            taskAssignmentItems = new GetTaskAssignmentList(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return taskAssignmentItems;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getId() {
        return id;
    }

//    public String getPayment_status() {
//        return payment_status;
//    }
//
//    public void setPayment_status(String payment_status) {
//        this.payment_status = payment_status;
//    }

//    public double getAmount_paid() {
//        return amount_paid;
//    }
//
//    public void setAmount_paid(double amount_paid) {
//        this.amount_paid = amount_paid;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplete_status() {
        return complete_status;
    }

    public void setComplete_status(String complete_status) {
        this.complete_status = complete_status;
    }

    public String isComplete_status() {
        return complete_status;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public double getQuantity_worked() {
        return quantity_worked;
    }

    public void setQuantity_worked(double quantity_worked) {
        this.quantity_worked = quantity_worked;
    }

    public String getAssignment_end_date() {
        return assignment_end_date;
    }

    public void setAssignment_end_date(String assignment_end_date) {
        this.assignment_end_date = assignment_end_date;
    }

    public String getAssignment_start_date() {
        return assignment_start_date;
    }

    public void setAssignment_start_date(String assignment_start_date) {
        this.assignment_start_date = assignment_start_date;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}

//
///**
// * Locally gets all task assignments from the SQLite db.
// */
//class GetTaskAssignmentList extends AsyncTask<Void, Void, List<TaskAssignmentItem>> {
//
//    TaskAssignmentDao taskAssignmentDao;
//    List<TaskAssignmentItem> taskAssignmentItemList;
//    Activity activity;
//
//    public GetTaskAssignmentList(Activity activity) {
//        this.activity = activity;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        ShambaAppDB db = new DBAdaptor(activity).getDB();
//        taskAssignmentDao = db.taskAssignmentDao();
//        taskAssignmentItemList = new ArrayList();
//    }
//
//    @Override
//    protected List<TaskAssignmentItem> doInBackground(Void... voids) {
//
//        List<TaskAssignment> db_tasks = taskAssignmentDao.getAllTaskAssignment();
//
//        if (db_tasks.size() > 0) {
//
//            for (int count = 0; count < db_tasks.size(); ++count) {
//                TaskAssignmentItem taskItem = new TaskAssignmentItem();
//
//                taskItem.setAmount_due(db_tasks.get(count).getAmount_due());
//                taskItem.setAssignment_end_date(db_tasks.get(count).getAssignment_end_date());
//                taskItem.setId(db_tasks.get(count).getId());
//                taskItem.setAssignment_start_date(db_tasks.get(count).getAssignment_start_date());
//                taskItem.setComments(db_tasks.get(count).getComments());
//                taskItem.setComplete_status(db_tasks.get(count).getComplete_status());
//                taskItem.setPay_rate_id(db_tasks.get(count).getPay_rate_id());
//                taskItem.setQuantity_worked(db_tasks.get(count).getQuantity_worked());
//                taskItem.setResource_id(db_tasks.get(count).getResource_id());
//                taskItem.setTask_id(db_tasks.get(count).getTask_id());
//
//                taskAssignmentItemList.add(taskItem);
//            }
//        }
//
//        return taskAssignmentItemList;
//    }
//
//    @Override
//    protected void onPostExecute(List<TaskAssignmentItem> taskAssignmentItems) {
////            super.onPostExecute(masterPlantingPlanItems);
//    }
//}

/**
 * Get task assignments from server and android local db.
 */
class GetTaskAssignmentList extends AsyncTask<Void, Void, List<TaskAssignmentItem>> {
    public Activity activity;
    Context context;

    public GetTaskAssignmentList(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPreExecute() {
        ShambaAppDB db = new DBAdaptor(activity).getDB();
    }

    @Override
    protected List<TaskAssignmentItem> doInBackground(Void... voids) {
        return getAllTaskAssignmentsFromServer();
    }

    /**
     * Pools all task assignments from server application!
     *
     * @return
     */
    private List<TaskAssignmentItem> getAllTaskAssignmentsFromServer() {

        List<TaskAssignmentItem> taskAssignmentItems = new ArrayList<>();

        try {
            List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                    BuildConfig.SERVER_URL, "taskAssignment/", "");

            Log.d("# task assignments:- ", String.valueOf(response.size()));

            JSONArray jArray = new JSONArray(response);

            for (int i = 0; i < jArray.length(); ++i) {
                TaskAssignmentItem taskAssignmentItem = new TaskAssignmentItem();

                JSONObject jsonObject = jArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                taskAssignmentItem.setId(id);

                int resource_id = jsonObject.getInt("resource_id");
                taskAssignmentItem.setResource_id(resource_id);

                int task_id = jsonObject.getInt("task_id");
                taskAssignmentItem.setTask_id(task_id);

                int service_id = jsonObject.getInt("service_id");
                taskAssignmentItem.setService_id(service_id);

                int pay_rate_id = jsonObject.getInt("pay_rate_id");
                taskAssignmentItem.setPay_rate_id(pay_rate_id);

                String assignment_start_date = jsonObject.getString("assignment_start_date");
                taskAssignmentItem.setAssignment_start_date(assignment_start_date);

                String assignment_end_date = jsonObject.getString("assignment_end_date");
                taskAssignmentItem.setAssignment_end_date(assignment_end_date);

                double quantity_worked = jsonObject.getDouble("quantity_worked");
                taskAssignmentItem.setQuantity_worked(quantity_worked);

                double amount_due = jsonObject.getDouble("amount_due");
                taskAssignmentItem.setAmount_due(amount_due);

                String complete_status = jsonObject.getString("complete_status");
                taskAssignmentItem.setComplete_status(complete_status);

                String comments = jsonObject.getString("comments");
                taskAssignmentItem.setComments(comments);

                taskAssignmentItems.add(taskAssignmentItem);
            }

            TaskAssignmentItem.staticTaskAssignmentItem = taskAssignmentItems;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return taskAssignmentItems;
    }

    @Override
    public void onPostExecute(List<TaskAssignmentItem> taskAssignmentItems) {
    }
}