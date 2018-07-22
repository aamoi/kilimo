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

    @Query("select * from Task where id in (:id)")
    public List<Task> getTaskById(String id);

    @Query("select * from Task where project_id in (:project_id)")
    public List<Task> getTasksByProject(int project_id);

    @Query("select * from Task where phase_id in (:phase_id) and project_id in (:project_id)")
    public List<Task> getTasksByProjectPhase(int phase_id, String project_id);

}
