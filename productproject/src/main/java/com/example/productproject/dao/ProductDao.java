package com.example.productproject.dao;

import com.example.productproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer>{
    Product findByName(String name);
}
