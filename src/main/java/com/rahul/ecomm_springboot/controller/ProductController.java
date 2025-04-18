package com.rahul.ecomm_springboot.controller;

import com.rahul.ecomm_springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rahul.ecomm_springboot.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    //    @GetMapping("/")
//    public String greet(){
//        return "Hello World";
//    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
//        System.out.print(service.getAllProducts());
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product newProduct = service.addNewProduct(product, imageFile);
            return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<String> updateProductData(@PathVariable int productId,
                                                    @RequestPart Product product,
                                                    @RequestPart MultipartFile imageFile) {
        Product product1 = null;
        try {
            product1 = service.updateProduct(productId, product, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (product1 != null)
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        Product product1 = service.getProductById(productId);
        if (product1 != null) {
            service.deleteProductById(productId);
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Failed to delete", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        System.out.println("Searching with"+keyword);
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
