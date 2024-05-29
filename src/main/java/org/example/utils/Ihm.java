package org.example.utils;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ihm {

    Scanner sc = new Scanner(System.in);
    ProduitService ps = new ProduitService();
    public Ihm() {
    }

    public void start() {

        boolean exit = false;

        do {
            System.out.println("Menu : ");
            System.out.println("1/ Afficher tous les produits");
            System.out.println("2/ Afficher la liste des produits dont le prix est supérieur au montant souhaité");
            System.out.println("3/ Afficher la liste des produits achetés entre deux dates souhaitées");
            System.out.println("4/ Afficher la valeur du stock selon une marque souhaitée");
            System.out.println("5/ Afficher le prix moyen de tous les produits");
            System.out.println("6/ Afficher tous les produits d'une marque");
            System.out.println("7/ Supprimer tous le sproduits d'une marque");
            System.out.println("8/ Quitter ");
            System.out.println("-------------------");
            int entry = sc.nextInt();
            sc.nextLine();
            switch (entry) {
                case 1:
                    ps.findAll();
                    break;
                case 2:
                    displayProductsByPriceMoreThan();
                    break;
                case 3:
                    displayProductsByDates();
                    break;
                case 4:
                    displayValueOfStockByBrand();
                case 5:
                    displayAveragePrice();
                case 6:
                    displayProductsByBrand();
                case 7:
                    deleteProductsByBrand();
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }

        } while (!exit);
    }

//    1. Afficher la totalité des produits
//2. Afficher la liste des produits dont le prix est supérieur à 100 euros
//3. Afficher la liste des produits achetés entre deux dates.

    public void displayProductsByPriceMoreThan() {
            System.out.println("Veuillez entrer le montant souhaité.");
            int entry = sc.nextInt();
            sc.nextLine();
            List<Produit> produitList = ps.findProductsPriceMoreThan(entry);
            for (Produit p: produitList){
                System.out.println(p);
            }
    }

    public void displayProductsByDates() {

            System.out.println("Veuillez entrer une date de début.");
            String startD = sc.nextLine();
            Date startDate = new Date(startD);
            System.out.println("Veuillez entrer une date de fin.");
            String endD = sc.nextLine();
            Date endDate = new Date(endD);
            List<Produit> produitList = ps.findProductsBetweenDates(startDate, endDate);
            for (Produit p: produitList){
                System.out.println(p);
            }
    }

    public void displayValueOfStockByBrand() {
        System.out.println("Veuillez entrer une marque.");
        String brand = sc.nextLine();
        List<Produit> produitList = ps.findProductsByBrand(brand);
        double valueOfStock = ps.valueOfStock(produitList);
        System.out.println("La valeur du stock pour la marque " + brand + " est de " + valueOfStock + " euros");
    }

    public void displayAveragePrice() {
        List<Produit> produitsList = ps.findAll();
        double averagePrice = ps.getAveragePriceOfAllProducts(produitsList);
        System.out.println("Le prix moyen des produits est de : " + averagePrice + " euros");
    }

    public void displayProductsByBrand() {
        System.out.println("Veuillez entrer une marque.");
        String brand = sc.nextLine();
        List<Produit> produitList = ps.findProductsByBrand(brand);
        for (Produit p: produitList){
            System.out.println(p);
        }
    }

    public void deleteProductsByBrand() {
        System.out.println("Veuillez entrer une marque.");
        String brand = sc.nextLine();
        ps.deleteProductsByBrand(brand);
        List<Produit> produitList = ps.findProductsByBrand(brand);
        if (produitList.isEmpty()) {
            System.out.println("Tous les produits de la marque " + brand + " ont été supprimés");
        } else {
            System.out.println("Erreur de suppression des produits de la marque " + brand + ".");

        }


    }


}
