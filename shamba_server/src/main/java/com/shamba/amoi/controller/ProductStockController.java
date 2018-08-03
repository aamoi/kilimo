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

import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ProductStockController {

    @Autowired
    ProductStockRepository productStockRepository;
    @Autowired
    StockTransactionRepository stockTransactionRepository;

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
        double location_balance = 0.0;
        double product_balance = 0.0;

        String mpesa_txn_number = body.get("mpesa_txn_number");
        String receipt_upload = body.get("receipt_upload");
        String stock_order_status = body.get("stock_order_status");

        double previous_location_bal = 0;
        double previous_product__bal = 0;


        ProductStock last_location_stock_item = new ProductStock();
        ProductStock last_stock_item = new ProductStock();


        if (!productStockRepository.findLastStock(product_id).isEmpty()) {
            last_stock_item = productStockRepository.findLastStock(product_id).get(0);
            previous_product__bal = last_stock_item.getOverall_stock_balance();

            System.out.println("Last product stock balance is :"+previous_product__bal);
        }

        if (!productStockRepository.findLastLocationStock(product_id, location_id).isEmpty()) {
            last_location_stock_item = productStockRepository.findLastLocationStock(product_id, location_id).get(0);
            previous_location_bal = last_location_stock_item.getLocation_balance();
            System.out.println("Last location stock balance is :"+previous_location_bal);
        }

        if(stock_order_status.equalsIgnoreCase("Delivered")){
            location_balance = purchase_quantity + previous_location_bal;
            product_balance = purchase_quantity + previous_product__bal;
        }
        else{
            location_balance = previous_location_bal;
            product_balance = previous_product__bal;
        }

        ProductStock productStock = productStockRepository.save(new ProductStock(product_id, vendor_id, distributor_id,
                manufacturer_id, purchase_quantity, purchase_price, purchase_details, purchase_date, location_id,
                location_balance,purchase_quantity,product_balance, mpesa_txn_number, receipt_upload, stock_order_status));

        if ((productStock != null) && (productStock.getId() > 0)) {

            StockTransaction stockTransaction=stockTransactionRepository.save(new StockTransaction(productStock.getId(),
                    9, "inbound",purchase_quantity, product_balance, location_balance));

            System.out.println("ProductStockController | createProductStock | Stock id:" + productStock.getId() +
                    ", product id:" + product_id + ", transaction id:"+stockTransaction.getId());
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