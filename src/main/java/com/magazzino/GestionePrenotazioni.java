package com.magazzino;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.magazzino.repository.ProductRepository;


@Component
public class GestionePrenotazioni implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public void run(String... args) throws Exception {
        Menu menuDelMagazzino = new Menu(); // Creazione dell'istanza della classe Menu
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            menuDelMagazzino.mostraMenu(); // Mostra il menu
            int scelta = scanner.nextInt(); // Legge la scelta dell'utente
            
            switch (scelta) {
                case 1:
                InsertProduct productMenu = new InsertProduct(productRepository);
                productMenu.InsertProductMenu();
                    break;
                case 2:
                AllProduct allProduct = new AllProduct(productRepository);
                allProduct.AllProductOutput();
                    break;
                case 3:
                    // Logica per aggiornare i dati di un prodotto
                    UpdateProduct updateProduct = new UpdateProduct(productRepository);
                    updateProduct.UpdateProductData();
                    break;
                case 4:
                    // Logica per rimuovere un prodotto
                    DeleteProduct deleteProduct = new DeleteProduct(productRepository);
                    deleteProduct.deleteProductData();
                    break;
                case 5:
                    // Logica per vendere prodotti
                    SellProduct sellProduct = new SellProduct(productRepository);
                    sellProduct.sellProductData();
                    break;
                case 6:
                    continua = false; // Esce dal ciclo while
                    System.out.println("Uscita dall'applicazione...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
        scanner.close();
    }
}



   

