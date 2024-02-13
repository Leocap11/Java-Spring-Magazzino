package com.magazzino;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.dao.DataIntegrityViolationException;

import com.magazzino.model.Product;
import com.magazzino.repository.ProductRepository;


public class UpdateProduct {
    private ProductRepository productRepository;

    public UpdateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void UpdateProductData() {
        Scanner scanner = new Scanner(System.in);
        boolean stillInProgress = true;
        while (stillInProgress) {
            System.out.println(
                    "Inserisci il Code del prodotto di cui vuoi aggiornare i dati o scrivi EXIT per tornare indietro.");
            String code = scanner.nextLine();
            if (code.equalsIgnoreCase("EXIT")) {
                stillInProgress = false;
                System.out.println("Stai tornando al menu precedente.");
            } else {
                Optional<Product> productByCode = productRepository.findByCode(code);
                if (productByCode.isPresent()) {

                    Product product = productByCode.get();
                    System.out.println("Scegli quale dato aggiornare.");
                    System.out.println("1- Code \n" +
                            "2- Descrizione \n" +
                            "3-Prezzo Unitario \n" +
                            "4- Quantità \n" +
                            "5- Torna indietro ");

                    Integer scelta = scanner.nextInt();

                    switch (scelta) {
                        case 1:
                            System.out.println("Inserisci un nuovo code.");
                            scanner.nextLine(); // Consuma il newline rimasto dopo nextInt()
                            String newCode = scanner.nextLine();
                            product.setCode(newCode);
                            try {
                                productRepository.save(product);
                                System.out.println("Code aggiornato con successo.");

                            } catch (DataIntegrityViolationException e) {
                                System.out.println(
                                        "Il codice prodotto è già in uso. Si prega di inserire un codice diverso.");
                                // Opzionale: chiedere all'utente se vuole riprovare
                                System.out.println("Vuoi provare con un altro codice? (s/n)");
                                String risposta = scanner.nextLine();
                                if (!risposta.equalsIgnoreCase("s")) {
                                    stillInProgress = true; // Imposta success a true per uscire dal ciclo, anche se non
                                                            // si inserisce il prodotto
                                }

                            }

                            break;
                        case 2:
                        System.out.println("Inserisci una nuova descrizione.");
                        scanner.nextLine(); // Consuma il newline rimasto dopo nextInt()
                        String newDescription = scanner.nextLine();
                        product.setDescription(newDescription);
                        productRepository.save(product);
                        stillInProgress = false;
                        System.out.println("Descrizione aggiornata con successo!");

                            break;
                        case 3:
                        System.out.println("Inserisci un nuovo prezzo unitario.");
                        scanner.nextLine(); // Consuma il newline rimasto dopo nextInt()
                        Double newUnitaryPrice = scanner.nextDouble();
                        product.setUnitaryPrice(newUnitaryPrice);
                        productRepository.save(product);
                        stillInProgress = false;
                        System.out.println("Prezzo unitario aggiornato con successo!");

                            break;
                        case 4:
                        System.out.println("Inserisci una nuova quantità.");
                        scanner.nextLine(); // Consuma il newline rimasto dopo nextInt()
                        Integer newQuantity = scanner.nextInt();
                        product.setQuantity(newQuantity);;
                        productRepository.save(product);
                        stillInProgress = false;
                        System.out.println("Quantità aggiornata con successo!");

                            break;
                        case 5:
                            stillInProgress = false;
                            System.out.println("Stai tornando al menu precedente");
                            break;
                        default:
                            System.out.println("Scelta non valida. Riprova.");
                            break;
                    }

                } else {
                    System.out.println("Entità non trovata con code: " + code);
                }
            }
        }
    }
}
