package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.Repository;

import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ProduitService extends BaseService implements Repository<Produit> {

    public ProduitService(){
        super();
    }

    @Override
    public boolean create(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Produit o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Produit findById(int id) {
        session = sessionFactory.openSession();
        Produit produit = session.get(Produit.class,id);
        session.close();
        return produit;
    }

    @Override
    public List<Produit> findAll() {
        session = sessionFactory.openSession();
        List<Produit> produits = session.createQuery("from Produit", Produit.class).list();
        session.close();
        return produits;
    }

    public List<Produit> findProductsPriceMoreThan(int price) {
        session = sessionFactory.openSession();
        List<Produit> produits = session.createQuery("from Produit where prix > :price", Produit.class)
                .setParameter("price", price)
                .list();
        session.close();
        return produits;
    }

    public List<Produit> findProductsBetweenDates(Date startDate, Date endDate) {
        session = sessionFactory.openSession();
        List<Produit> produits = session.createQuery("from Produit where dateAchat between :startDate and :endDate", Produit.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .list();
        session.close();
        return produits;
    }

    public void close(){
        sessionFactory.close();
    }

//    1. Afficher la valeur du stock des produits d'une marque choisie.
//    2. Calculer le prix moyen des produits.
//    3. Récupérer la liste des produits d'une marque choisie.
//    4. Supprimer les produits d'une marque choisie de la table produit.

    public List<Produit> findProductsByBrand(String brand) {
        session = sessionFactory.openSession();
        Query<Produit> produitsQuery = session.createQuery("from Produit where marque = :brand", Produit.class);
        produitsQuery.setParameter("brand", brand);
        List<Produit> produitList = produitsQuery.list();
        session.close();
        return produitList;
    }

    public double valueOfStock(List<Produit> produitsList) {
        double totalValue = 0;
        for (Produit produit : produitsList) {
            totalValue += produit.getPrix() * produit.getStock();
        }
        return totalValue;
    }

    public double getAveragePriceOfAllProducts(List<Produit> produitsList) {
        session = sessionFactory.openSession();
        Query<Double> query = session.createQuery("select avg(prix) from Produit", Double.class);
        return query.getSingleResult();
    }

    public void deleteProductsByBrand(String brand) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query<?> query = session.createQuery("delete from Produit where marque = :brand");
        query.setParameter("brand", brand);

        int rowsAffected = query.executeUpdate();

        session.getTransaction().commit();

        if (rowsAffected > 0) {
            System.out.println(rowsAffected + " produits de la marque " + brand + " ont été supprimés.");
        } else {
            System.out.println("Aucun produit trouvé pour la marque " + brand);
        }
        session.close();
    }






}
