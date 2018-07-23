package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.StockUtilizationRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.ProductStock;
import com.shamba.amoi.model.StockUtilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class StockUtilizationController {

    @Autowired
    StockUtilizationRepository stockUtilization;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/stockUtilization")
    public List<StockUtilization> index() {
        return stockUtilization.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/stockUtilization/{id}")
    public StockUtilization show(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        return stockUtilization.getOne(Id);
    }

    @PostMapping("/stockUtilization/search")
    public List<StockUtilization> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;
    }

    @PostMapping("/createStockUtilization")
    public StockUtilization create(@RequestBody Map<String, String> body) {
        int stock_id = Integer.parseInt(body.get("stock_id"));
        int project_id = Integer.parseInt(body.get("project_id"));
        int phase_id = Integer.parseInt(body.get("phase_id"));
        int task_id = Integer.parseInt(body.get("task_id"));
        double utilized_quantity = Double.parseDouble(body.get("utilized_quantity"));
        Date utilized_date =DateUtil.stringToDate(body.get("utilized_date"));
        String details = body.get("details");

        return stockUtilization.save(new StockUtilization(stock_id,project_id,phase_id,task_id,utilized_quantity,utilized_date,details));
    }

    @PutMapping("/updateStockUtilizationk/{id}")
    public ProductStock update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int Id = Integer.parseInt(id);
        StockUtilization stockUtil = stockUtilization.getOne(Id);

        String details = body.get("details");
        stockUtilization.getOne(Id);
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