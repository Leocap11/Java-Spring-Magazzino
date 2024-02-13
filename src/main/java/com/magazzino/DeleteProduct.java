package com.magazzino;

import java.util.Optional;
import java.util.Scanner;

import com.magazzino.model.Product;
import com.magazzino.repository.ProductRepository;

public class DeleteProduct {
    private ProductRepository productRepository;

    public DeleteProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteProductData() {
        Scanner scanner = new Scanner(System.in);
        boolean showMenu = true;
        while(showMenu){
            System.out.println("Inserisci il code del prodotto che vuoi eliminare o scrivi EXIT per tornare indietro.");
            String code = scanner.nextLine();
            if (code.equalsIgnoreCase("EXIT")) {
                showMenu = false;
                System.out.println("Stai tornando al menu precedente.");
            } else {
                Optional<Product> productByCode = productRepository.findByCode(code);
                if (productByCode.isPresent()) {
                    // Se il prodotto è stato trovato, lo elimina
                    productRepository.delete(productByCode.get());
                    showMenu = false;
                    System.out.println("Prodotto con code " + code + " eliminato con successo.");
                } else {
                    // Se il prodotto non è stato trovato, stampa un messaggio
                    showMenu = false;
                    System.out.println("Prodotto con code " + code + " non trovato.");
                }
            }
        }
    }
    
}
