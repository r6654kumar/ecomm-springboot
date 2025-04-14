package com.rahul.ecomm_springboot.repository;

import com.rahul.ecomm_springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
