package org.example.utils;


import org.example.services.ProduitService;

import java.util.Date;
import java.util.Scanner;

public class Ihm {

    Scanner sc = new Scanner(System.in);
    ProduitService ps = new ProduitService();
    public Ihm() {
    }

    public void start() {


        do {
            System.out.println("Menu : ");
            System.out.println("1/ Afficher tous les produits");
            System.out.println("2/ Afficher la liste des produits dont le prix est supérieur au montant souhaité");
            System.out.println("3/ Afficher la liste des produits achetés entre deux dates souhaitées");
            System.out.println("-------------------");
            int entry = sc.nextInt();
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
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }

        } while (true);
    }

//    1. Afficher la totalité des produits
//2. Afficher la liste des produits dont le prix est supérieur à 100 euros
//3. Afficher la liste des produits achetés entre deux dates.

    public void displayProductsByPriceMoreThan() {
        do {
            System.out.println("Veuillez entrer le montant souhaité.");
            int entry = sc.nextInt();
            sc.nextLine();
            ps.findProductsPriceMoreThan(entry);
        } while(true);
    }

    public void displayProductsByDates() {
        do {
            System.out.println("Veuillez entrer une date de début.");
            String startD = sc.nextLine();
            Date startDate = new Date(startD);
            System.out.println("Veuillez entrer une date de fin.");
            String endD = sc.nextLine();
            Date endDate = new Date(endD);
            ps.findProductsBetweenDates(startDate, endDate);
        } while(true);
    }

}
