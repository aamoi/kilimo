package com.shamba.amoi.shambaapp.db.labor;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by amoi on 15/02/2018.
 */
@Dao
public interface TaskAssignmentDao {
    @Insert
    public void insertTaskAssignment(TaskAssignment taskAssignment);

    @Update
    public void updateTaskAssignment(TaskAssignment taskAssignment);

    @Delete
    public void deleteTaskAssignment(TaskAssignment taskAssignment);

    @Query("select * from taskassignment")
    public List<TaskAssignment> getAllTaskAssignment();

    @Query("select * from taskassignment where id in (:id)")
    public List<TaskAssignment> getTaskAssignment(int id);
}
