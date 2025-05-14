package com.udemy.controller;


import com.udemy.model.Product;
import com.udemy.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Endpoints")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public List<Product> findAllProduct() {
        return service.findAllProducts();
    }

    @GetMapping("/find/{id}")
    public Product findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
       return service.createProduct(product);
    }
}
