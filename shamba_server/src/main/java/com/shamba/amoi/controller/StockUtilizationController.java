package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ProductStockRepository;
import com.shamba.amoi.Repository.StockTransactionRepository;
import com.shamba.amoi.Repository.StockUtilizationRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.ProductStock;
import com.shamba.amoi.model.StockTransaction;
import com.shamba.amoi.model.StockUtilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Transactional
@RestController
public class StockUtilizationController {

    @Autowired
    StockUtilizationRepository stockUtilizationRepository;
    @Autowired
    StockTransactionRepository stockTransactionRepository;
    @Autowired
    ProductStockRepository productStockRepository;

    @GetMapping("/stockUtilization")
    public List<StockUtilization> index() {
        return stockUtilizationRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/stockUtilization/{id}")
    public StockUtilization show(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        return stockUtilizationRepository.getOne(Id);
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
        int asset_id = Integer.parseInt(body.get("asset_id"));
        int task_id = Integer.parseInt(body.get("task_id"));
        double utilized_quantity = Double.parseDouble(body.get("utilized_quantity"));
        Date utilized_date = DateUtil.stringToDate(body.get("utilized_date"));
        String details = body.get("details");

        StockUtilization stockUtilization =null;

        int product_id = 0;
        int location_id = 0;
        double stock_bal=0;

        ProductStock productStock = new ProductStock();
        if (stock_id > 0) {
            productStock = productStockRepository.getOne(stock_id);
            product_id = productStock.getProduct_id();
            location_id = productStock.getLocation_id();

            stock_bal=productStock.getStock_balance();
            productStock.setStock_balance((stock_bal-utilized_quantity));
//            productStockRepository.save(productStock);

            ProductStock last_location_stock_item = new ProductStock();
            double last_location_bal = 0;
            double current_location_bal = 0;

            if (!productStockRepository.findLastLocationStock(product_id, location_id).isEmpty()) {
                last_location_stock_item = productStockRepository.findLastLocationStock(product_id, location_id).get(0);
                last_location_bal = last_location_stock_item.getLocation_balance();
                current_location_bal = last_location_bal - utilized_quantity;
                last_location_stock_item.setLocation_balance(current_location_bal);
            }
//            productStockRepository.save(last_location_stock_item);

            ProductStock last_product_stock = new ProductStock();
            double last_product_bal = 0;
            double current_product_bal = 0;

            if (!productStockRepository.findLastStock(product_id).isEmpty()) {
                last_product_stock = productStockRepository.findLastStock(product_id).get(0);
                last_product_bal = last_product_stock.getOverall_stock_balance();
                current_product_bal = last_product_bal - utilized_quantity;
                last_product_stock.setOverall_stock_balance(current_product_bal);
            }
//            productStockRepository.save(last_product_stock);

            System.out.println("StockUtilizationController|createStockUtilization|"+
                    "about to submit StockUtilization");

            if((current_location_bal>=0)&&(stock_bal>=0)){

                System.out.println("StockUtilizationController|createStockUtilization|"+
                        "about to submit StockUtilization");
                productStockRepository.save(productStock);
                productStockRepository.save(last_location_stock_item);
                productStockRepository.save(last_product_stock);
                stockUtilization = stockUtilizationRepository.save(new StockUtilization(stock_id,task_id, asset_id,
                        utilized_quantity, utilized_date, details));

            }
        }

        return stockUtilization;
    }

    @PutMapping("/updateStockUtilizationk/{id}")
    public ProductStock update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int Id = Integer.parseInt(id);
        StockUtilization stockUtil = stockUtilizationRepository.getOne(Id);

        String details = body.get("details");
        stockUtilizationRepository.getOne(Id);
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