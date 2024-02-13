package com.magazzino;

import java.util.Optional;
import java.util.Scanner;

import com.magazzino.model.Product;
import com.magazzino.repository.ProductRepository;

public class SellProduct {
    private ProductRepository productRepository;

    public SellProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void sellProductData(){
        Scanner scanner = new Scanner(System.in);
        boolean sellProductsMenu = true;
        while(sellProductsMenu){
            System.out.println("Scrivi il numero di prodotti che vuoi vendere o scrivi Exit per tornare indietro.");
            String productsNumber = scanner.nextLine();
            if(productsNumber.equalsIgnoreCase("EXIT")){
                sellProductsMenu = false;
                System.out.println("Stai tornando al menu principale.");
            }else{
                int numProducts = Integer.parseInt(productsNumber);
                for(int i = 0; i < numProducts; i++){
                    System.out.println("Inserisci il codice del prodotto che vorresti vendere.");
                    String code = scanner.nextLine();
                    Optional<Product> productByCode = productRepository.findByCode(code);
                    if(productByCode.isPresent()){
                        Product product = productByCode.get(); // Ottieni il prodotto dal contenitore Optional
                        System.out.println("Il prodotto con codice " + product.getCode() + ", cioè: " + product.getDescription() + ",al prezzo unitario di " + product.getUnitaryPrice() + ", è presente in magazzino con una quantità di: " + product.getQuantity());
                        System.out.println("Scegli la quantità che vuoi vendere. (Ricorda non oltre il massimo della quantità disponibile.)");
                        Integer quantityToSell = scanner.nextInt();
                        scanner.nextLine(); 
                        Integer maxAvaibleQuantity = product.getQuantity();
                        if(quantityToSell == maxAvaibleQuantity){
                            Double profit = quantityToSell * product.getUnitaryPrice();
                            System.out.println("Hai venduto" + quantityToSell + product.getDescription() + "a: " + profit);
                            productRepository.delete(product);
                            System.out.println("Avendo venduto la quantità massima il prodotto sarà eliminato");
                            System.out.println("Tornerai al menu principale.");;
                            sellProductsMenu = false;
                        } else if(quantityToSell < maxAvaibleQuantity){
                            Integer updatedQuanity = maxAvaibleQuantity - quantityToSell;
                            product.setQuantity(updatedQuanity);
                            Double profit = quantityToSell * product.getUnitaryPrice();
                            System.out.println("Hai venduto" + quantityToSell + product.getDescription() + "a: " + profit);
                            productRepository.save(product);
                            System.out.println("Il prodotto con codice: " + product.getCode() + product.getDescription() + "ha aggiornato la sua quantità a: " + product.getQuantity());
                            System.out.println("Tornerai al menu principale.");;
                            sellProductsMenu = false;
                        } else {
                            System.out.println("Qunatità non disponibile o errore di battitura.");
                            System.out.println("Tornerai al menu principale.");;
                            sellProductsMenu = false;
                        }
                    }

                }
            }
        }
        
    }
}
