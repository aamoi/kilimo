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
public interface TaskPaymentDao {
    @Insert
    public void insertTaskPayment(TaskPayment taskPayment);

    @Update
    public void updateTaskPayment(TaskPayment taskPayment);

    @Delete
    public void deleteTaskPayment(TaskPayment taskPayment);

    @Query("select * from TaskPayment where resource_id in (:resource_id) group by task_assignment_id")
    public List<TaskPayment> getAllResourcePayments(String resource_id);

    @Query("select * from TaskPayment where task_payment_id in (:task_payment_id)")
    public List<TaskPayment> getTaskPayment(int task_payment_id);
}
