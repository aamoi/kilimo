package com.shamba.amoi.controller;
import com.shamba.amoi.Repository.ResourceRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by amoi on 10/07/2018.
 */

@RestController
public class ResourceController {

    @Autowired
    ResourceRepository resourceRepository;

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/resource")
    public List<Resource> index() {
        return resourceRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/resource/{id}")
    public Resource show(@PathVariable String id) {
        int resource_id = Integer.parseInt(id);
        return resourceRepository.getOne(resource_id);
    }

//    @PostMapping("/planting/search")
//    public List<Planting> search(@RequestBody Map<String, String> body) {
//        String searchTerm = body.get("text");
////        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
//        return null;
//
//    }

    @PostMapping("/createResource")
    public Resource create(@RequestBody Map<String, String> body) {
        int id= Integer.parseInt(body.get("id"));
        String resource_name=body.get("resource_name");
        int default_pay_rate_id= Integer.parseInt(body.get("default_pay_rate_id"));
        int location_id= Integer.parseInt(body.get("location_id"));
        String  phone=body.get("phone");
        String skillset= body.get("skillset");
        String resource_type= body.get("resource_type");
        String details= body.get("details");


        return resourceRepository.save(new Resource(resource_name,default_pay_rate_id,location_id,
                phone,skillset,resource_type,details));
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
//
//    @DeleteMapping("/deletePlanting/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//        plantingRepository.deleteById(planting_id);
//    }


}