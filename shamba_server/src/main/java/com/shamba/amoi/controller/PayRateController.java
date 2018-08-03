package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */
import com.shamba.amoi.Repository.PayRateRepository;
import com.shamba.amoi.model.PayRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class PayRateController {

    @Autowired
    PayRateRepository payRateRepository;

    @GetMapping("/payRate")
    public List<PayRate> index() {
        return payRateRepository.findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/payRate/{id}")
    public PayRate show(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        return payRateRepository.getOne(Id);
    }

    @PostMapping("/payRate/search")
    public List<PayRate> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createPayRate")
    public PayRate create(@RequestBody Map<String, String> body) {
        int id = Integer.parseInt(body.get("id"));
        String name=body.get("name");
        int uom_id = Integer.parseInt(body.get("uom_id"));
        double unit_price=Double.parseDouble(body.get("unit_price"));
        String details=body.get("details");
        String completion_status=body.get("completion_status");

        return payRateRepository.save(new PayRate(name,unit_price,details,uom_id));
    }

    @PutMapping("/updatePayRate/{id}")
    public PayRate update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int pay_rate_id = Integer.parseInt(id);
        PayRate payRate=payRateRepository.getOne(pay_rate_id);

        return payRateRepository.save(payRate);
//        return null;
    }

//    @DeleteMapping("/deletePlanting/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//         plantingRepository.deleteById(planting_id);
//    }

}