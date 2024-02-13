package com.magazzino;

import java.util.Scanner;

import org.springframework.dao.DataIntegrityViolationException;

import com.magazzino.model.Product;
import com.magazzino.repository.ProductRepository;

public class InsertProduct {

    private ProductRepository productRepository;
    
    public InsertProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
      public void InsertProductMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean success = false;
    

    do {
        System.out.println("Inserimento di un nuovo prodotto.");
        
        System.out.println("Inserisci un codice:");
        String code = scanner.nextLine();

        System.out.println("Inserisci una descrizione:");
        String description = scanner.nextLine();

        System.out.println("Inserisci un prezzo unitario:");
        Double unitaryPrice = scanner.nextDouble();
        
        System.out.println("Inserisci una quantità:");
        Integer quantity = scanner.nextInt();
        scanner.nextLine(); 

        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setUnitaryPrice(unitaryPrice);
        product.setQuantity(quantity);

        try {
            productRepository.save(product);
            System.out.println("Nuovo prodotto inserito!");
            success = true;
        } catch (DataIntegrityViolationException e) {
            System.out.println("Il codice prodotto è già in uso. Si prega di inserire un codice diverso.");
            // Opzionale: chiedere all'utente se vuole riprovare
            System.out.println("Vuoi provare con un altro codice? (s/n)");
            String risposta = scanner.nextLine();
            if (!risposta.equalsIgnoreCase("s")) {
                success = true; // Imposta success a true per uscire dal ciclo, anche se non si inserisce il prodotto
            }
        }
    } while (!success);
}

}
