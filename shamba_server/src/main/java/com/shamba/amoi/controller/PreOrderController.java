package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.AssetRepository;
import com.shamba.amoi.Repository.PreOrderRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.Asset;
import com.shamba.amoi.model.PreOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class PreOrderController {

    @Autowired
    PreOrderRepository preOrderRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/preOrder")
    public List<PreOrder> index() {
        return preOrderRepository.findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/preOrder/{id}")
    public PreOrder show(@PathVariable String id) {
        int pre_id = Integer.parseInt(id);
        return preOrderRepository.getOne(pre_id);
    }

    @PostMapping("/preOrder/search")
    public List<PreOrder> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createPreOrder")
    public PreOrder create(@RequestBody Map<String, String> body) {
        String name=body.get("pre_order_name");
        int product_id = Integer.parseInt(body.get("product_id"));
        double quantity=Double.parseDouble(body.get("quantity"));
        double purchase_price=Double.parseDouble(body.get("purchase_price"));
        String details=body.get("details");
        int location_id=Integer.parseInt(body.get("location_id"));
        Date required_date= DateUtil.stringToDate(body.get("required_date"));
        boolean is_delivered=Boolean.parseBoolean(body.get("is_delivered"));

        return preOrderRepository.save(new PreOrder(name,product_id,quantity,details,location_id,required_date,is_delivered));

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