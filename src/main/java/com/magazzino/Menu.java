package com.magazzino;


public class Menu {
    public void mostraMenu() { // Rinominato per evitare confusione con i costruttori
        System.out.println("Benvenuto nel menu del magazzino");
        System.out.println("Cosa vuoi fare?\n" +
                "1- Aggiunta di un nuovo prodotto al magazzino\n" +
                "2- Visualizzazione dell'elenco dei prodotti presenti nel magazzino\n" +
                "3- Aggiornamento dei dati di un prodotto presente nel magazzino\n" +
                "4- Rimozione di un prodotto dal magazzino\n" +
                "5- Vendita di uno o più prodotti presenti nel magazzino, aggiornando la quantità disponibile\n" +
                "6- Esci dalla applicazione");
        // Potresti voler aggiungere qui della logica per gestire l'input dell'utente
    }
}
