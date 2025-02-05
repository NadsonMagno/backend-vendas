package com.nadsola.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nadsola.ecommerce.dto.ProductDTO;
import com.nadsola.ecommerce.model.entities.Product;
import com.nadsola.ecommerce.repositories.ProductRepository;
import com.nadsola.ecommerce.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        
        

        
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return new ProductDTO(product);

        

    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> listResult = productRepository.findAll(pageable);
        return listResult.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
       Product product = new Product(null, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getImgUrl());

         product = productRepository.save(product);
            return new ProductDTO(product);


    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        
        Product product = productRepository.getReferenceById(id);
               
        copyDTOToEntity(dto, product);

         product = productRepository.save(product);
            return new ProductDTO(product);


    }

    private void copyDTOToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }

    @Transactional
    public void deleteById(Long id) {
       productRepository.deleteById(id);
    }




}
