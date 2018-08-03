package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.TaskAssignmentRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Asset;
import com.shamba.amoi.model.TaskAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class TaskAssignmentController {

    @Autowired
    TaskAssignmentRepository taskAssignmentRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/taskAssignment")
    public List<TaskAssignment> index() {
        return taskAssignmentRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/taskAssignment/{id}")
    public TaskAssignment show(@PathVariable String id) {
        int ass_id = Integer.parseInt(id);
        return taskAssignmentRepository.getOne(ass_id);
    }

    @PostMapping("/taskAssignment/search")
    public List<TaskAssignment> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createTaskAssignment")
    public TaskAssignment create(@RequestBody Map<String, String> body) {

        int resource_id = Integer.parseInt(body.get("resource_id"));
        int task_id = Integer.parseInt(body.get("task_id"));
        int pay_rate_id = Integer.parseInt(body.get("pay_rate_id"));
        Date assignment_start_date= DateUtil.stringToDate(body.get("assignment_start_date"));
        Date assignment_end_date= DateUtil.stringToDate(body.get("assignment_end_date"));
        double quantity_worked=Double.parseDouble(body.get("quantity_worked"));
        double amount_due=Double.parseDouble(body.get("amount_due"));
        String complete_status=body.get("complete_status");
        String comments=body.get("comments");
        String payment_status=body.get("payment_status");

        double amount_paid=Double.parseDouble(body.get("amount_paid"));

        return taskAssignmentRepository.save(new TaskAssignment(resource_id,task_id,pay_rate_id,assignment_start_date,
                assignment_end_date,quantity_worked,amount_due,complete_status,comments,payment_status,amount_paid));

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