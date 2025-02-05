package com.nadsola.ecommerce.model.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadsola.ecommerce.model.entities.Product;
import com.nadsola.ecommerce.repositories.ProductRepository;


@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping  
    public String teste(){

      Optional<Product> result  = productRepository.findById(1L);
      Product product = result.get();
        return product.getName();

    }



}
