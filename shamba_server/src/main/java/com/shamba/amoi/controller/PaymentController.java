package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.PaymentRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/payment")
    public List<Payment> index() {
        return paymentRepository.findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/payment/{id}")
    public Payment show(@PathVariable String id) {
        int pay_id = Integer.parseInt(id);
        return paymentRepository.getOne(pay_id);
    }

    @PostMapping("/payment/search")
    public List<Payment> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createPayment")
    public Payment create(@RequestBody Map<String, String> body) {

        int resource_id = Integer.parseInt(body.get("resource_id"));
        int task_id = Integer.parseInt(body.get("task_id"));
        int service_id = Integer.parseInt(body.get("service_id"));
        int pay_rate_id = Integer.parseInt(body.get("pay_rate_id"));
        int work_size = Integer.parseInt(body.get("work_size"));
        Date task_start_date = DateUtil.stringToDate(body.get("task_start_date"));
        Date due_date = DateUtil.stringToDate(body.get("due_date"));
        double amount_due = Double.parseDouble(body.get("amount_due"));
        Date payment_date = DateUtil.nullableStringToDate(body.get("payment_date"));
        String payment_status = body.get("payment_status");
        double amount_paid = Double.parseDouble(body.get("amount_paid"));
        double resource_balance_due =0.0;
        String details = body.get("details");

        return paymentRepository.save(new Payment(resource_id, task_id,service_id,task_start_date, due_date,pay_rate_id,
                work_size,amount_due,payment_date, payment_status,amount_paid, resource_balance_due, details));
    }

    @PutMapping("/updatePayment/{id}")
    public Payment update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int pay_id = Integer.parseInt(id);
        Payment payment = paymentRepository.getOne(pay_id);

//        String name = body.get("name");
//        product.setName(name);
//
//        double seed_quantity = Double.parseDouble(body.get("seed_quantity"));
//        planting.setSeed_quantity(seed_quantity);
//        return plantingRepository.save(planting);
        return null;
    }
//
//    @DeleteMapping("/deleteProduct/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//         plantingRepository.deleteById(planting_id);
//    }


}