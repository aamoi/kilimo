package com.shamba.amoi.shambaapp.models.labor;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignment;
import com.shamba.amoi.shambaapp.db.labor.TaskAssignmentDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 15/02/2018.
 */

public class TaskAssignmentItem {
    String assignment_id;
    String resource_id;
    String plan_id;
    String phase_id;
    String task_id;
    String pay_rate_id;
    String assignment_start_date;
    String assignment_end_date;
    double quantity_worked;
    double amount_due;
    boolean complete_status;
    String comments;
    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setPay_rate_id(String pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setPhase_id(String phase_id) {
        this.phase_id = phase_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public void setAssignment_end_date(String assignment_end_date) {
        this.assignment_end_date = assignment_end_date;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public void setAssignment_start_date(String assignment_start_date) {
        this.assignment_start_date = assignment_start_date;
    }

    public void setComplete_status(boolean complete_status) {
        this.complete_status = complete_status;
    }

    public void setQuantity_worked(double quantity_worked) {
        this.quantity_worked = quantity_worked;
    }

    public String getComments() {
        return comments;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public double getQuantity_worked() {
        return quantity_worked;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public String getAssignment_end_date() {
        return assignment_end_date;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public String getAssignment_start_date() {
        return assignment_start_date;
    }

    public String getPay_rate_id() {
        return pay_rate_id;
    }

    public String getPhase_id() {
        return phase_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public static List<TaskAssignmentItem> getAllTaskAssignments(Activity activity){

        List<TaskAssignmentItem> assignmentItemList=new ArrayList<>();

        try {
            assignmentItemList=new GetTaskAssignmentList(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Projects|", "number of task assignments from db is: "+assignmentItemList.size());

        return assignmentItemList;
    }
}


/**
 * Locally gets all task assignments from the SQLite db.
 */
class GetTaskAssignmentList extends AsyncTask<Void, Void, List<TaskAssignmentItem>> {

    TaskAssignmentDao taskAssignmentDao;
    List<TaskAssignmentItem> taskAssignmentItemList;
    Activity activity;

    public GetTaskAssignmentList(Activity activity){
        this.activity=activity;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db= new DBAdaptor(activity).getDB();
        taskAssignmentDao=db.taskAssignmentDao();
        taskAssignmentItemList=new ArrayList();
    }

    @Override
    protected List<TaskAssignmentItem> doInBackground(Void... voids) {

        List<TaskAssignment> db_tasks = taskAssignmentDao.getAllTaskAssignment();

        if (db_tasks.size() > 0){

            for (int count = 0; count < db_tasks.size(); ++count) {
                TaskAssignmentItem taskItem = new TaskAssignmentItem();

                taskItem.setAmount_due(db_tasks.get(count).getAmount_due());
                taskItem.setAssignment_end_date(db_tasks.get(count).getAssignment_end_date());
                taskItem.setAssignment_id(db_tasks.get(count).getAssignment_id());
                taskItem.setAssignment_start_date(db_tasks.get(count).getAssignment_start_date());
                taskItem.setComments(db_tasks.get(count).getComments());
                taskItem.setComplete_status(db_tasks.get(count).getComplete_status());
                taskItem.setPay_rate_id(db_tasks.get(count).getPay_rate_id());
                taskItem.setPhase_id(db_tasks.get(count).getPhase_id());
                taskItem.setPhase_id(db_tasks.get(count).getPhase_id());
                taskItem.setPlan_id(db_tasks.get(count).getPlan_id());
                taskItem.setQuantity_worked(db_tasks.get(count).getQuantity_worked());
                taskItem.setResource_id(db_tasks.get(count).getResource_id());
                taskItem.setTask_id(db_tasks.get(count).getTask_id());

                taskAssignmentItemList.add(taskItem);
            }
        }

        return taskAssignmentItemList;
    }

    @Override
    protected void onPostExecute(List<TaskAssignmentItem> taskAssignmentItems) {
//            super.onPostExecute(masterPlantingPlanItems);
    }
}
