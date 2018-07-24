package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ProductCategoryRepository;
import com.shamba.amoi.Repository.ProductRepository;
import com.shamba.amoi.Repository.VendorRepository;
import com.shamba.amoi.model.ProductCategory;
import com.shamba.amoi.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductCategoryController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping("/productCategory")
    public List<ProductCategory> index() {
        return productCategoryRepository.findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/productCategory/{id}")
    public ProductCategory show(@PathVariable String id) {
        int category_id = Integer.parseInt(id);
        return productCategoryRepository.getOne(category_id);
    }

    @PostMapping("/productCategory/search")
    public List<ProductCategory> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;
    }

    @PostMapping("/createProductCategory")
    public ProductCategory create(@RequestBody Map<String, String> body) {
        String category_name = body.get("category_name");
        String details = body.get("details");

        return productCategoryRepository.save(new ProductCategory(category_name,details));
    }

    @PutMapping("/updateProductCategory/{id}")
    public ProductCategory update(@PathVariable String id, @RequestBody Map<String, String> body) {
        int category_id = Integer.parseInt(id);
        ProductCategory productCategory = productCategoryRepository.getOne(category_id);
//        String name = body.get("name");
//        product.setName(name);
//        double seed_quantity = Double.parseDouble(body.get("seed_quantity"));
//        planting.setSeed_quantity(seed_quantity);
//        return plantingRepository.save(planting);
        return productCategory;
    }
//    @DeleteMapping("/deleteProduct/{id}")
//    public void delete(@PathVariable String id) {
//        int planting_id = Integer.parseInt(id);
//         plantingRepository.deleteById(planting_id);
//    }


}