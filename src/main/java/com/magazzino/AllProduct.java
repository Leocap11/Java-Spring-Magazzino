package com.magazzino;

import java.util.List;

import com.magazzino.model.Product;
import com.magazzino.repository.ProductRepository;

public class AllProduct {
    private ProductRepository productRepository;
    
    public AllProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void AllProductOutput(){
        System.out.println("Elenco prodotti:");
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ", Codice: " + product.getCode() + ", Descrizione: " + product.getDescription() + ", Prezzo Unitario: " + product.getUnitaryPrice() + ", Quantità: " + product.getQuantity());
        }
    }
    
    
}
