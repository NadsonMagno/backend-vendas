package com.nadsola.ecommerce.model.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @GetMapping  
    public String teste(){
        return "ola mundo!";
    }



}
