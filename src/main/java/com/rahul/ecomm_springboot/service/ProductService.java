package com.rahul.ecomm_springboot.service;

import com.rahul.ecomm_springboot.model.Product;
import com.rahul.ecomm_springboot.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int productId) {
        return productRepo.findById(productId).orElse(null);
    }
    public Product addNewProduct(Product newProduct){
        return productRepo.save(newProduct);
    }
}