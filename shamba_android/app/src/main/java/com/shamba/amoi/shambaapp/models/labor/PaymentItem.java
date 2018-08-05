package com.shamba.amoi.shambaapp.models.labor;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.shamba.amoi.shambaapp.BuildConfig;
import com.shamba.amoi.shambaapp.db.DBAdaptor;
import com.shamba.amoi.shambaapp.db.ShambaAppDB;
import com.shamba.amoi.shambaapp.shareResources.CommonHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by amoi on 26/03/2018.
 */
public class PaymentItem {
    public static PaymentItem selectedPaymentItem;
    public static List<PaymentItem> staticPaymentItems;
    int id;
    int resource_id;
    int task_id;
    String task_start_date;
    int pay_rate_id;
    double work_size;
    String due_date;
    double amount_due;
    String payment_date;
    String payment_status;
    double amount_paid;
    double resource_balance_due;
    String details;
    String task_complete_status;

    public String getTask_complete_status() {
        return task_complete_status;
    }

    public void setTask_complete_status(String task_complete_status) {
        this.task_complete_status = task_complete_status;
    }

    public int getPay_rate_id() {
        return pay_rate_id;
    }

    public void setPay_rate_id(int pay_rate_id) {
        this.pay_rate_id = pay_rate_id;
    }

    public double getWork_size() {
        return work_size;
    }

    public void setWork_size(double work_size) {
        this.work_size = work_size;
    }

    public String getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(String task_start_date) {
        this.task_start_date = task_start_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public double getResource_balance_due() {
        return resource_balance_due;
    }

    public void setResource_balance_due(double resource_balance_due) {
        this.resource_balance_due = resource_balance_due;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    /**
     * Get task and resource payment
     * @param activity
     * @return
     */
    public static List<PaymentItem> getPaymentByTaskAndResource(Activity activity,
                                                         int task_id,int resource_id) {
        List<PaymentItem> allPaymentItems = getAllPayments(activity);
        List<PaymentItem> resourcePaymentItems = new ArrayList<>();

        for(int i=0;i<allPaymentItems.size();++i){
            if((allPaymentItems.get(i).getTask_id()==task_id)&&
                    (allPaymentItems.get(i).getResource_id()==resource_id)){
                resourcePaymentItems.add(allPaymentItems.get(i));
            }
        }
        return resourcePaymentItems;
    }
    /**
     * Get task  payment
     * @param activity
     * @return
     */
    public static List<PaymentItem> getPaymentByTask(Activity activity, int task_id) {
        List<PaymentItem> allPaymentItems = getAllPayments(activity);
        List<PaymentItem> resourcePaymentItems = new ArrayList<>();

        for(int i=0;i<allPaymentItems.size();++i){
            if(allPaymentItems.get(i).getTask_id()==task_id){
                resourcePaymentItems.add(allPaymentItems.get(i));
            }
        }
        return resourcePaymentItems;
    }
    /**
     * Get resource  payment
     * @param activity
     * @return
     */
    public static List<PaymentItem> getPaymentByResource(Activity activity, int resource_id) {
        List<PaymentItem> allPaymentItems = getAllPayments(activity);
        List<PaymentItem> resourcePaymentItems = new ArrayList<>();

        for(int i=0;i<allPaymentItems.size();++i){
            if(allPaymentItems.get(i).getResource_id()==resource_id){
                resourcePaymentItems.add(allPaymentItems.get(i));
            }
        }
        return resourcePaymentItems;
    }
    /**
     * Get all payment
     * @param activity
     * @return
     */
    public static List<PaymentItem> getAllPayments(Activity activity) {

        List<PaymentItem> paymentItems = new ArrayList<>();

        try {
            paymentItems = new GetPayments(activity).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return paymentItems;
    }

    /**
     * Get payments from server and save in the android local db.
     */
   static class GetPayments extends AsyncTask<Void, Void, List<PaymentItem>> {

        public Activity activity;

        public GetPayments(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onPreExecute() {
            ShambaAppDB db = new DBAdaptor(activity).getDB();
        }

        @Override
        protected List<PaymentItem> doInBackground(Void... voids) {
            return getAllPaymentsFromServer();
        }

        /**
         * Pools all payments from server application!
         *
         * @return
         */
        private List<PaymentItem> getAllPaymentsFromServer() {

            List<PaymentItem> paymentItems = new ArrayList<>();

            try {
                List<JSONObject> response = CommonHelper.sendGetRequestWithJsonResponse(
                        BuildConfig.SERVER_URL, "payment/", "");

                Log.d("# of payments pooled:- ", String.valueOf(response.size()));
                JSONArray jArray = new JSONArray(response);

                for (int i = 0; i < jArray.length(); ++i) {
                    PaymentItem paymentItem = new PaymentItem();
                    JSONObject jsonObject = jArray.getJSONObject(i);

                    int id = jsonObject.getInt("id");
                    paymentItem.setId(id);

                    int resource_id = jsonObject.getInt("resource_id");
                    paymentItem.setResource_id(resource_id);

                    int task_id = jsonObject.getInt("task_id");
                    paymentItem.setTask_id(task_id);

                    String task_start_date = jsonObject.getString("task_start_date");
                    paymentItem.setTask_start_date(task_start_date);

                    String due_date = jsonObject.getString("due_date");
                    paymentItem.setDue_date(due_date);

                    int pay_rate_id = jsonObject.getInt("pay_rate_id");
                    paymentItem.setPay_rate_id(pay_rate_id);

                    double work_size = jsonObject.getDouble("work_size");
                    paymentItem.setWork_size(work_size);

                    double amount_due = jsonObject.getDouble("amount_due");
                    paymentItem.setAmount_due(amount_due);

                    String payment_date = jsonObject.getString("payment_date");
                    paymentItem.setPayment_date(payment_date);

                    String payment_status = jsonObject.getString("payment_status");
                    paymentItem.setPayment_status(payment_status);

                    double amount_paid = jsonObject.getDouble("amount_paid");
                    paymentItem.setAmount_paid(amount_paid);

                    String details = jsonObject.getString("details");
                    paymentItem.setDetails(details);

                    paymentItems.add(paymentItem);
                }

                PaymentItem.staticPaymentItems = paymentItems;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return paymentItems;
        }
    }
}
