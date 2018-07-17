package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.PlantingRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.Utils.JavaUtils;
import com.shamba.amoi.model.Planting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class PlantingController {

    @Autowired
    PlantingRepository plantingRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/planting")
    public List<Planting> index() {
        return plantingRepository.findAll();
    }

    @GetMapping("/planting/{id}")
    public Planting show(@PathVariable String id) {
        int planting_Id = Integer.parseInt(id);
        return plantingRepository.getOne(planting_Id);
    }

    @PostMapping("/planting/search")
    public List<Planting> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createPlanting")
    public Planting create(@RequestBody Map<String, String> body) {
//        double estimated_cost=Double.parseDouble(body.get("estimated_cost"));
        double estimated_cost= JavaUtils.stringToDouble(body.get("estimated_cost"));

        Date planned_harvest_date= DateUtil.stringToDate(body.get("planned_harvest_date"));
        Date planned_preparation_date= DateUtil.stringToDate(body.get("planned_preparation_date"));
//        double estimated_revenue=Double.parseDouble(body.get("estimated_revenue"));
        double estimated_revenue= JavaUtils.stringToDouble(body.get("estimated_revenue"));

//        double seed_quantity=Double.parseDouble(body.get("seed_quantity"));
        double seed_quantity= JavaUtils.stringToDouble(body.get("seed_quantity"));

        Date planned_seedbed_date= DateUtil.stringToDate(body.get("planned_seedbed_date"));
        Date planned_sales_date= DateUtil.stringToDate(body.get("planned_sales_date"));
        Date planned_transplant_date= DateUtil.stringToDate(body.get("planned_transplant_date"));
        int block_id=Integer.parseInt(body.get("block_id"));
        int location_id=Integer.parseInt(body.get("location_id"));
        int product_id=Integer.parseInt(body.get("product_id"));
        String planting_name=body.get("planting_name");
        String planting_details=body.get("planting_details");
        double actual_revenue=JavaUtils.stringToDouble(body.get("actual_revenue"));
        double actual_cost=JavaUtils.stringToDouble(body.get("actual_cost"));
        double estimated_sales_quantity=JavaUtils.stringToDouble(body.get("estimated_sales_quantity"));
        double actual_sales_quantity=JavaUtils.stringToDouble(body.get("actual_sales_quantity"));
        Date actual_harvest_date= JavaUtils.stringToDate(body.get("planned_harvest_date"));
        Date actual_preparation_date= JavaUtils.stringToDate(body.get("actual_preparation_date"));
        Date actual_seedbed_date= JavaUtils.stringToDate(body.get("actual_seedbed_date"));
        Date actual_transplant_date= JavaUtils.stringToDate(body.get("actual_transplant_date"));
        Date actual_sales_date= JavaUtils.stringToDate(body.get("actual_sales_date"));

        return plantingRepository.save(new Planting(estimated_cost,planned_harvest_date,planned_preparation_date,estimated_revenue,
        seed_quantity,planned_seedbed_date,planned_sales_date,planned_transplant_date,block_id,location_id,product_id,
                planting_name,planting_details,actual_cost,actual_revenue, estimated_sales_quantity,actual_sales_quantity,
                actual_harvest_date,actual_preparation_date,actual_seedbed_date,actual_transplant_date,actual_sales_date));
    }

    @PutMapping("/updatePlanting/{id}")
    public Planting update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int planting_id = Integer.parseInt(id);
        Planting planting=plantingRepository.getOne(planting_id);

        String name = body.get("planting_name");
        planting.setPlanting_name(name);

        double seed_quantity = Double.parseDouble(body.get("seed_quantity"));
        planting.setSeed_quantity(seed_quantity);
        return plantingRepository.save(planting);
//        return null;
    }

    @DeleteMapping("/deletePlanting/{id}")
    public void delete(@PathVariable String id) {
        int planting_id = Integer.parseInt(id);
         plantingRepository.deleteById(planting_id);
    }
}