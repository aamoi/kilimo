package com.shamba.amoi.shambaapp.models.labor;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.db.labor.TaskPayment;
import com.shamba.amoi.shambaapp.db.labor.TaskPaymentDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 14/04/2018.
 */

public class TaskPaymentItem {
    int task_payment_id;
    String task_assignment_id;
    String resource_id;
    String task_id;
    String program_id;
    double total_amount;
    double balance_before;
    double amount_paid;
    double balance_after;
    String payment_status;
    String payment_due_date;
    String payment_date;
    String comments;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getProgram_id() {
        return program_id;
    }

    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public String getPayment_due_date() {
        return payment_due_date;
    }

    public void setPayment_due_date(String payment_due_date) {
        this.payment_due_date = payment_due_date;
    }

    public int getTask_payment_id() {
        return task_payment_id;
    }

    public void setTask_payment_id(int task_payment_id) {
        this.task_payment_id = task_payment_id;
    }

    public String getTask_assignment_id() {
        return task_assignment_id;
    }

    public void setTask_assignment_id(String task_assignment_id) {
        this.task_assignment_id = task_assignment_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getBalance_before() {
        return balance_before;
    }

    public void setBalance_before(double balance_before) {
        this.balance_before = balance_before;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getBalance_after() {
        return balance_after;
    }

    public void setBalance_after(double balance_after) {
        this.balance_after = balance_after;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public static List<TaskPaymentItem> getAllTaskPayments(Activity activity,String resource_id){

        List<TaskPaymentItem> assignmentItemList=new ArrayList<>();

        try {
            assignmentItemList=new GetTaskPaymentList(activity,resource_id).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Projects|", "number of task payment from db is: "+assignmentItemList.size());

        return assignmentItemList;
    }

    public static List<TaskPaymentItem> getAllResourceTaskPayments(Activity activity, String resource_id){

        List<TaskPaymentItem> taskPaymentItems=new ArrayList<>();

        try {
            taskPaymentItems=new GetTaskPaymentList(activity,resource_id).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Projects|", "number of task payment from db is: "+taskPaymentItems.size());

        return taskPaymentItems;
    }
    
}



/**
 * Locally gets all resource payments from the SQLite db.
 */
class GetTaskPaymentList extends AsyncTask<Void, Void, List<TaskPaymentItem>> {

    TaskPaymentDao TaskPaymentDao;
    List<TaskPaymentItem> TaskPaymentItemList;
    Activity activity;
    String resource_id;

    public GetTaskPaymentList(Activity activity, String resource_id){
        this.activity=activity;
        this.resource_id=resource_id;
    }

    @Override
    protected void onPreExecute() {
        ShambaAppDB db= new DBAdaptor(activity).getDB();
        TaskPaymentDao=db.taskPaymentDao();
        TaskPaymentItemList=new ArrayList();
    }

    @Override
    protected List<TaskPaymentItem> doInBackground(Void... voids) {

        List<TaskPayment> db_payments = TaskPaymentDao.getAllResourcePayments(resource_id);

        if (db_payments.size() > 0){

            for (int count = 0; count < db_payments.size(); ++count) {
                TaskPaymentItem taskPaymentItem = new TaskPaymentItem();
                taskPaymentItem.setAmount_paid(db_payments.get(count).getAmount_paid());
                taskPaymentItem.setBalance_after(db_payments.get(count).getBalance_after());
                taskPaymentItem.setBalance_before(db_payments.get(count).getBalance_before());
                taskPaymentItem.setComments(db_payments.get(count).getComments());
                taskPaymentItem.setPayment_date(db_payments.get(count).getPayment_date());
                taskPaymentItem.setPayment_status(db_payments.get(count).getPayment_status());
                taskPaymentItem.setResource_id(db_payments.get(count).getResource_id());
                taskPaymentItem.setTask_assignment_id(db_payments.get(count).getTask_assignment_id());
                taskPaymentItem.setTotal_amount(db_payments.get(count).getTotal_amount());
                taskPaymentItem.setProgram_id(db_payments.get(count).getProgram_id());
                taskPaymentItem.setTask_id(db_payments.get(count).getTask_id());
                taskPaymentItem.setPayment_due_date(db_payments.get(count).getPayment_due_date());

                TaskPaymentItemList.add(taskPaymentItem);
            }
        }

        return TaskPaymentItemList;
    }

    @Override
    protected void onPostExecute(List<TaskPaymentItem> TaskPaymentItems) {
//            super.onPostExecute(masterPlantingPlanItems);
    }
}
