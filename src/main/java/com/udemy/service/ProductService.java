package com.udemy.service;

import com.udemy.model.Product;
import com.udemy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ProductRepository repository;

    public List<Product> findAllProducts(){
        return repository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> product = repository.findById(id.longValue());
        
        return product.orElseGet(Product::new);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }
}
