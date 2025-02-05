package com.nadsola.ecommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadsola.ecommerce.dto.ProductDTO;
import com.nadsola.ecommerce.model.entities.Product;
import com.nadsola.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result  = productRepository.findById(id);
        Product product = result.get();

        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
        return productDTO;
    }

}
