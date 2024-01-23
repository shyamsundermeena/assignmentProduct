package com.example.demo.productRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.productEntity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
