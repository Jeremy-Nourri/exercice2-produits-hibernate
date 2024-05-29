package org.example.utils;

import org.example.entities.Produit;
import org.example.services.ImageService;
import org.example.services.ProduitService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Ihm {

    Scanner sc = new Scanner(System.in);
    ProduitService ps = new ProduitService();
    public Ihm() {
    }

    public void start() throws ParseException {

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
            System.out.println("8/ Ajouter une image à un produit");
            System.out.println("0/ Quitter ");
            System.out.println("-------------------");
            int entry = sc.nextInt();
            sc.nextLine();
            switch (entry) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    displayProductsByPriceMoreThan();
                    break;
                case 3:
                    displayProductsByDates();
                    break;
                case 4:
                    displayValueOfStockByBrand();
                    break;
                case 5:
                    displayAveragePrice();
                    break;
                case 6:
                    displayProductsByBrand();
                    break;
                case 7:
                    deleteProductsByBrand();
                    break;
                case 8:
                    addPictureToProduct();
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }

        } while (!exit);

        ps.close();
    }

    public void displayAllProducts(){
        List<Produit> produitList = ps.findAll();
        for (Produit p: produitList){
            System.out.println(p);
        }
    }

    public void displayProductsByPriceMoreThan() {
            System.out.println("Veuillez entrer le montant souhaité.");
            int entry = sc.nextInt();
            sc.nextLine();
            List<Produit> produitList = ps.findProductsPriceMoreThan(entry);
            for (Produit p : produitList){
                System.out.println(p.toString());
            }
    }

    public void displayProductsByDates() throws ParseException {
        try {
            System.out.println("Veuillez saisir une premiere date (dd/MM/yyyy) :");
            String s1 = sc.nextLine();
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(s1);
            System.out.println("Veuillez saisir une premiere date (dd/MM/yyyy) :");
            String s2 = sc.nextLine();
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(s2);
            List<Produit> produitList = ps.findProductsBetweenDates(startDate, endDate);
            for (Produit p : produitList) {
                System.out.println(p);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
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
        ps.findProductsByBrand(brand);

    }

    public void addPictureToProduct() {
        System.out.println("Veuillez entrer l'url de votre image.");
        String url = sc.nextLine();

        System.out.println("Veuillez entrer l'id du produit sur lequel vous souhaitez ajouter l'image.");
        int productId = sc.nextInt();

        ImageService imageService = new ImageService(ps);

        imageService.addPictureToProduct(url, productId);
    }
}
