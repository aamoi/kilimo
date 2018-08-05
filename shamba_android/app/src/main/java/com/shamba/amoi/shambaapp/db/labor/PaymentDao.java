package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 14/04/2018.
 */

@Dao
public interface PaymentDao {
    @Insert
    public void insertPayment(Payment payment);

    @Update
    public void updatePayment(Payment payment);

    @Delete
    public void deletePayment(Payment payment);

    @Query("select * from Payment")
    public List<Payment> getAllPayments();

    @Query("select * from Payment where resource_id in (:resource_id)")
    public List<Payment> getPaymentsByResource(int resource_id);

    @Query("select * from Payment where task_id in (:task_id)")
    public List<Payment> getPaymentByTask(int task_id);

    @Query("select * from Payment where task_id in (:task_id) and resource_id in (:resource_id)")
    public List<Payment> getPaymentByResourceTasks(int task_id, int resource_id);
}
