package com.example.productproject.controller;

import com.example.productproject.entity.Product;
import com.example.productproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>("Add Project Successfully!",HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<?> addProductList(@RequestBody List<Product> productList) {
        productService.addProductList(productList);
        return new ResponseEntity<>("Add Products Successfully!",HttpStatus.OK);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty()) {
            return new ResponseEntity<>("No Products", HttpStatus.OK);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        Optional<Product> product = productService.getProductById(productId);
        if(product.isPresent()) {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found!",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public  ResponseEntity<?> setProductById(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> existProduct = productService.setProductById(product,id);
        if(existProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteProductById(@PathVariable int id) {
        Optional<Product> existProduct = productService.deleteProductById(id);
        if(existProduct.isPresent()) {
            return new ResponseEntity<>("Delete Product Successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<?> deleteAllProducts() {
        productService.deleteAllProducts();
        return new ResponseEntity<>("Delete All Products Successfully!", HttpStatus.OK );
    }



}
