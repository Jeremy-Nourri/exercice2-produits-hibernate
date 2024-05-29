package org.example.services;

import org.example.entities.Produit;
import org.example.interfaces.Repository;

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
}
