package com.org.ibs.controller;


import com.org.ibs.model.Product;
import com.org.ibs.model.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 1. Add a product to the inventory
 2. Get all the available products
 3. Purchase a product with x quantity
*/

@RestController
@RequestMapping("/api")
public class InventoryController {

    public static Map<String, Product> inventory = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);


    @PostMapping ("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
       inventory.put(product.getProductId(), product);
       logger.info(" Added Inventory Item,  Product Id = {} & Details = {}", product.getProductId(), product.toString());
       return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<Map<String, Product>> getProducts() {
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PutMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody Purchase purchase) {

        String productName =  purchase.getProductName();
        int quantity  = purchase.getQuantity();
        int counter  = 0;
        for (Map.Entry<String, Product> entry : inventory.entrySet()) {
            if (counter >= quantity) {
                break;
            }
            if (productName.equalsIgnoreCase("table")) {
                inventory.remove(entry.getKey());
                logger.info(" Purchased Inventory Item,  Product Id = {} & Details = {}", entry.getKey(), entry.getValue().toString());
                counter++;
            }
        }

        return new ResponseEntity<>("Successfully , Purchased " + quantity +" " +  purchase.getProductName() , HttpStatus.OK);
    }

}
