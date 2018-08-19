package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.PaymentRepository;
import com.shamba.amoi.Repository.TaskAssignmentRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Asset;
import com.shamba.amoi.model.Payment;
import com.shamba.amoi.model.TaskAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
public class TaskAssignmentController {

    String class_name=getClass().getSimpleName();

    @Autowired
    TaskAssignmentRepository taskAssignmentRepository;
    @Autowired
    PaymentRepository paymentRepository;

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

        Integer resource_id = Integer.parseInt(body.get("resource_id"));

        String task_id_str=body.get("task_id");
        int task_id = task_id_str!=null?Integer.parseInt(task_id_str):0;

        String service_id_str=body.get("service_id");
        int service_id = service_id_str!=null?Integer.parseInt(service_id_str):0;

        int pay_rate_id = Integer.parseInt(body.get("pay_rate_id"));
        Date assignment_start_date = DateUtil.stringToDate(body.get("assignment_start_date"));
        Date assignment_end_date = DateUtil.stringToDate(body.get("assignment_end_date"));
        double quantity_worked = Double.parseDouble(body.get("quantity_worked"));
        double amount_due = Double.parseDouble(body.get("amount_due"));
        String complete_status = body.get("complete_status");
        String comments = body.get("comments");
//        String payment_status=body.get("payment_status");
        String payment_status = "pending";
//        double amount_paid=Double.parseDouble(body.get("amount_paid"));
        double amount_paid = 0.0;

        TaskAssignment taskAssignment = new TaskAssignment();
        Payment payment = new Payment();

            taskAssignment = taskAssignmentRepository.save(new TaskAssignment(resource_id, task_id,service_id,
                    pay_rate_id,assignment_start_date, assignment_end_date, quantity_worked, amount_due,
                    complete_status,comments, payment_status, amount_paid));

        System.out.println(class_name+"|createTaskAssignment(), taskAssignment:"+taskAssignment.toString());

        payment = paymentRepository.save(new Payment(resource_id, task_id,service_id,assignment_start_date,
                assignment_end_date,pay_rate_id,quantity_worked,amount_due,null,payment_status, 0.0,amount_due,comments));
        System.out.println(class_name+"|createTaskAssignment(), payment:"+payment.toString());

        return taskAssignment;
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