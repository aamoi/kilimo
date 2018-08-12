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
    public static List<TaskAssignmentItem> staticTaskAssignmentItem;
    public static TaskAssignmentItem selectedTaskAssignmentItem;
    int id;
    int resource_id;
    int task_id;
    int pay_rate_id;
    String assignment_start_date;
    String assignment_end_date;
    double quantity_worked;
    double amount_due;
    String complete_status;
    String comments;
//    String payment_status;
//    double amount_paid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplete_status() {
        return complete_status;
    }

    public String isComplete_status() {
        return complete_status;
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

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public void setAssignment_end_date(String assignment_end_date) {
        this.assignment_end_date = assignment_end_date;
    }

    public void setAssignment_start_date(String assignment_start_date) {
        this.assignment_start_date = assignment_start_date;
    }

    public void setComplete_status(String complete_status) {
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

    public String getAssignment_end_date() {
        return assignment_end_date;
    }


    public String getAssignment_start_date() {
        return assignment_start_date;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public int getTask_id() {
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
        Log.d("Projects|", "number of task assignments from db is: "+
                assignmentItemList.size());

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
                taskItem.setId(db_tasks.get(count).getId());
                taskItem.setAssignment_start_date(db_tasks.get(count).getAssignment_start_date());
                taskItem.setComments(db_tasks.get(count).getComments());
                taskItem.setComplete_status(db_tasks.get(count).getComplete_status());
                taskItem.setPay_rate_id(db_tasks.get(count).getPay_rate_id());
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
