package com.shamba.amoi.shambaapp.db.projects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

/**
 * Created by amoi on 23/01/2018.
 */
@Dao
public interface TaskDao {
    @Insert
    public void insertTask(Task task);

    @Update(onConflict = android.arch.persistence.room.OnConflictStrategy.REPLACE)
    public void updateTask(Task... tasks);

    @Delete
    public void deleteTask(Task... task);

    @Query("select * from Task")
    public List<Task> getAllTasks();

    @Query("select * from Task where task_id in (:task_id)")
    public List<Task> getTask(String task_id);

    @Query("select * from Task where planting_program_id in (:planting_program_id)")
    public List<Task> getProgramTasks(String planting_program_id);

    @Query("select * from Task where phase_id in (:phase_id) and planting_program_id in (:planting_program_id)")
    public List<Task> getProgramPhaseTasks(String phase_id, String planting_program_id);

}
