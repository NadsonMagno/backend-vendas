package com.nadsola.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadsola.ecommerce.dto.ProductDTO;
import com.nadsola.ecommerce.services.ProductService;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){

      ProductDTO productDTO = productService.findById(id);
      return productDTO;

    }

    @GetMapping
    public Page <ProductDTO> findAll(Pageable pageable){

      Page <ProductDTO> productDTO = productService.findAll(pageable);
      return productDTO;

    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto){

      dto = productService.insert(dto);
      return dto;



    }



}
