package com.andradeds.service;

import com.andradeds.model.Product;
import com.andradeds.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ProductRepository repository;

    public Page<Product> findAllProducts(Integer pageNumber, Integer limit){
        Pageable pageable = PageRequest.of(pageNumber,limit);
        return repository.findAll(pageable);
    }

    public Product findById(Integer id) {
        Optional<Product> product = repository.findById(id.longValue());
        
        return product.orElseGet(Product::new);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Product product) {
        return repository.save(product);
    }
}
