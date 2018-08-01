package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ProductStockRepository;
import com.shamba.amoi.Repository.StockTransactionRepository;
import com.shamba.amoi.Utils.DateUtil;
import com.shamba.amoi.model.ProductStock;
import com.shamba.amoi.model.StockTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ProductStockController {

    @Autowired
    ProductStockRepository productStockRepository;

    @Autowired
    StockTransactionRepository stockTransactionRepository;


//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }
//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/productStock")
    public List<ProductStock> index() {
        return productStockRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/productStock/{id}")
    public ProductStock show(@PathVariable String id) {
        int Id = Integer.parseInt(id);
        return productStockRepository.getOne(Id);
    }

    @PostMapping("/productStock/search")
    public List<ProductStock> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;
    }

    @PostMapping("/createProductStock")
    public ProductStock create(@RequestBody Map<String, String> body) {
        int product_id = Integer.parseInt(body.get("product_id"));
        int vendor_id = Integer.parseInt(body.get("vendor_id"));
        int distributor_id = Integer.parseInt(body.get("distributor_id"));
        int manufacturer_id = Integer.parseInt(body.get("manufacturer_id"));
        double purchase_quantity = Double.parseDouble(body.get("purchase_quantity"));
        double purchase_price = Double.parseDouble(body.get("purchase_price"));
        String purchase_details = body.get("purchase_details");
        Date purchase_date = DateUtil.stringToDate((body.get("purchase_date")));
        int location_id = Integer.parseInt(body.get("location_id"));
        String location_balance_str = body.get("location_balance");
        double location_balance = location_balance_str == null ? 0 : Double.parseDouble(location_balance_str);
        String mpesa_txn_number = body.get("mpesa_txn_number");
        String receipt_upload = body.get("receipt_upload");
        String stock_order_status = body.get("stock_order_status");

        double location_bal = productStockRepository.findLastStock(product_id, location_id).get(0).getLocation_balance();
        int last_id = productStockRepository.findLastStock(product_id, location_id).get(0).getId();

//        System.out.println("last stock id"+last_id+" last stock"+productStockRepository.
//                findLastStock(product_id,location_id));

        ProductStock productStock = productStockRepository.save(new ProductStock(product_id, vendor_id, distributor_id,
                manufacturer_id, purchase_quantity, purchase_price, purchase_details, purchase_date, location_id,
                location_bal, mpesa_txn_number, receipt_upload, stock_order_status));

        Integer stock_util_id = null;


        if ((productStock != null) && (productStock.getId() > 0)) {

            System.out.println("stock id" + last_id + " last stock" + productStockRepository.
                    findLastStock(product_id, location_id));

            stockTransactionRepository.save(new StockTransaction(productStock.getId(), 9, "inbound",
                    purchase_quantity, (location_bal + purchase_quantity), 0));
        }

        return productStock;
    }

    @PutMapping("/updateProductStock/{id}")
    public ProductStock update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int Id = Integer.parseInt(id);
        ProductStock productStock = productStockRepository.getOne(Id);

        String purchase_details = body.get("purchase_details");
        productStock.getPurchase_details();
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