package com.rahul.ecomm_springboot.controller;

import com.rahul.ecomm_springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rahul.ecomm_springboot.service.ProductService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping("/")
    public String greet(){
        return "Hello World";
    }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
//        System.out.print(service.getAllProducts());
        return service.getAllProducts();
    }
    @GetMapping("/products/{productId}")
    public Optional<Product> getProductById(@PathVariable int productId){
        return service.getProductById(productId);
    }
}
