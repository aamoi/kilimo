package com.shamba.amoi.controller;

/**
 * Created by amoi on 09/07/2018.
 */

import com.shamba.amoi.Repository.ProductRepository;
import com.shamba.amoi.model.Planting;
import com.shamba.amoi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

//    @RequestMapping("/")
//    public String index() {
//        return "Hit planting controller";
//    }

//    PlantingMockedData blogMockedData = PlantingMockedData.getInstance();

    @GetMapping("/product")
    public List<Product> index() {
        return productRepository.
                findAll(new Sort(Sort.Order.asc("id")));
    }

    @GetMapping("/product/{id}")
    public Product show(@PathVariable String id) {
        int product_id = Integer.parseInt(id);
        return productRepository.getOne(product_id);
    }

    @PostMapping("/product/search")
    public List<Product> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
//        return plantingRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
        return null;

    }

    @PostMapping("/createProduct")
    public Product create(@RequestBody Map<String, String> body) {
        String name = body.get("product_name");
        boolean is_asset = Boolean.parseBoolean(body.get("is_asset"));
        int category_id = Integer.parseInt(body.get("category_id"));
        int uom_id = Integer.parseInt(body.get("uom_id"));

        return productRepository.save(new Product(name, is_asset, category_id, uom_id));
    }

    @PutMapping("/updateProduct/{id}")
    public Planting update(@PathVariable String id, @RequestBody Map<String, String> body) {

        int planting_id = Integer.parseInt(id);
        Product product = productRepository.getOne(planting_id);

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