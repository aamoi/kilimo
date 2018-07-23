package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.TaskRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Task;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.shamba.amoi.Utils.JavaUtils.nullableStringToDouble;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/task")
    public List<Task> index() {
        return taskRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/task/{id}")
    public Task show(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        return taskRepository.getOne(Id);
    }

    @PostMapping("/task/search")
    public List<Task> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createTask")
    public Task create(@RequestBody Map<String, String> body) {
        int project_id = Integer.parseInt(body.get("project_id"));
        String task_name=body.get("task_name");
        Date planned_start_date= DateUtil.stringToDate(body.get("planned_start_date"));
        Date planned_end_date= DateUtil.stringToDate(body.get("planned_end_date"));
        double planned_days=Double.parseDouble(body.get("planned_days"));
        int phase_id = Integer.parseInt(body.get("phase_id"));
        int planned_persons = Integer.parseInt(body.get("planned_persons"));
        double estimated_cost=Double.parseDouble(body.get("estimated_cost"));
        double estimated_revenue=Double.parseDouble(body.get("estimated_revenue"));

        @Nullable
        Date actual_start_date= DateUtil.nullableStringToDate(body.get("actual_start_date"));
        @Nullable
        Date actual_end_date= DateUtil.nullableStringToDate(body.get("actual_end_date"));
        @Nullable
        double actual_days=nullableStringToDouble(body.get("actual_days"));
        @Nullable
        double actual_persons=nullableStringToDouble(body.get("actual_persons"));
        @Nullable
        double actual_cost=nullableStringToDouble(body.get("actual_cost"));
        @Nullable
        double actual_revenue=nullableStringToDouble(body.get("actual_revenue"));

        return taskRepository.save(new Task(project_id,task_name,planned_start_date,planned_end_date,planned_days,
                phase_id,planned_persons,estimated_cost,estimated_revenue,actual_start_date,actual_end_date,
                actual_days,actual_persons,actual_cost,actual_revenue));

    }

//    @PutMapping("/updatePlanting/{id}")
//    public Planting update(@PathVariable String id, @RequestBody Map<String, String> body) {
//
//        int planting_id = Integer.parseInt(id);
//        Planting planting=plantingRepository.getOne(planting_id);
//
//        String name = body.get("name");
//        planting.setName(name);
//
//        double seed_quantity = Double.parseDouble(body.get("seed_quantity"));
//        planting.setSeed_quantity(seed_quantity);
//        return plantingRepository.save(planting);
////        return null;
//    }

//    @DeleteMapping("/deletePlanting/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//         plantingRepository.deleteById(planting_id);
//    }

}