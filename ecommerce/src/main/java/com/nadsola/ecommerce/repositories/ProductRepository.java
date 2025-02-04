package com.nadsola.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadsola.ecommerce.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
