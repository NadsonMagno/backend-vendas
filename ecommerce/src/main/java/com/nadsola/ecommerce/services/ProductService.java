package com.nadsola.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nadsola.ecommerce.dto.ProductDTO;
import com.nadsola.ecommerce.model.entities.Product;
import com.nadsola.ecommerce.repositories.ProductRepository;
import com.nadsola.ecommerce.services.exceptions.DataBaseException;
import com.nadsola.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        Page<Product> listResult = productRepository.seasearchByName(name, pageable);
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
        
       try{
        Product product = productRepository.getReferenceById(id);
               
        copyDTOToEntity(dto, product);

         product = productRepository.save(product);
            return new ProductDTO(product);

       } catch (EntityNotFoundException e) {
           throw new ResourceNotFoundException("Id not found" );
       }

    }

    private void copyDTOToEntity(ProductDTO dto, Product product) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        try{
            productRepository.deleteById(id);

        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" );
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation" );
        }
    }




}
