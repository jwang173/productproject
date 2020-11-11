package com.example.productproject.service;

import com.example.productproject.dao.ProductDao;
import com.example.productproject.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public void addProduct(Product product) {
        productDao.save(product);
    }

    public void addProductList(List<Product> productList) {
        productDao.saveAll(productList);
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return  productDao.findById(id);
    }

    public Optional<Product> setProductById(Product product, int productId) {
        Optional<Product> existProduct = productDao.findById(productId);
        if(existProduct.isPresent()) {
            product.setId(productId);
            productDao.save(product);
        }
        return existProduct;
    }

    public Optional<Product> deleteProductById(int productId) {
        Optional<Product> existProduct = productDao.findById(productId);
        if(existProduct.isPresent()) {
            productDao.deleteById(productId);
        }
        return  existProduct;
    }

    public void deleteAllProducts() {
        productDao.deleteAll();
    }


}
