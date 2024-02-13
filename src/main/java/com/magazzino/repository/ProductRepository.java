package com.magazzino.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.magazzino.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
    Optional<Product> findByCode(String code);

    Page<Product> findByCode(String code, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    void deleteByCode(String code);

}
