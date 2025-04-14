package com.rahul.ecomm_springboot.controller;

import com.rahul.ecomm_springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rahul.ecomm_springboot.service.ProductService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
//    @GetMapping("/")
//    public String greet(){
//        return "Hello World";
//    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
//        System.out.print(service.getAllProducts());
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId){
        Product product = service.getProductById(productId);
        if(product!=null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/add-products")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try{
            Product newProduct = service.addNewProduct(product);
            return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
