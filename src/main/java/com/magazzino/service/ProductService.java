package com.magazzino.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.magazzino.model.Product;
import com.magazzino.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    public Optional<Product> findById( Long id) {
		return productRepository.findById(id);
	}

    public Optional<Product> findByCode(String code) {
		return productRepository.findByCode(code);
	}

    public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

    public Product save(Product product) {
		return productRepository.save(product);
	}

    public Product updateById(Long id, Product productDetails) throws Exception {
        return productRepository.findById(id).map(product -> {
            product.setDescription(productDetails.getDescription());
            // Set other fields you want to update
            return productRepository.save(product);
        }).orElseThrow(() -> new Exception("Product not found for this id :: " + id));
    }

  

	

    public Product updateByCode(String code, Product productDetails) throws Exception {
        return productRepository.findByCode(code).map(product -> {
            product.setDescription(productDetails.getDescription());
            // Set other fields you want to update
            return productRepository.save(product);
        }).orElseThrow(() -> new Exception("Product not found for this code :: " + code));
    }
	
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

    
    @Transactional
    public void deleteByCode(String code) {
        productRepository.deleteByCode(code);
    }

    
   

    
}
