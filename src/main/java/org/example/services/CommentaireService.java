package org.example.services;

import org.example.entities.Commentaire;
import org.example.entities.Produit;
import org.example.interfaces.Repository;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class CommentaireService extends BaseService implements Repository<Commentaire> {

    private final ProduitService produitService;

    public CommentaireService(ProduitService produitService) {
        super();
        this.produitService = produitService;
    }

    @Override
    public boolean create(Commentaire o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Commentaire o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Commentaire o) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Commentaire findById(int id) {
        session = sessionFactory.openSession();
        Commentaire commentaire = session.get(Commentaire.class,id);
        session.close();
        return commentaire;
    }

    @Override
    public List<Commentaire> findAll() {
        session = sessionFactory.openSession();
        Query<Commentaire> commentaireQuery = session.createQuery("from Commentaire ", Commentaire.class);
        List<Commentaire> commentaireList = commentaireQuery.list();
        session.close();
        return commentaireList;
    }

    public void addCommentToProduct(String content, LocalDate date, int note, int productId) {
        Produit produit = produitService.findById(productId);

        if (produit != null) {
            Commentaire commentaire = Commentaire.builder()
                    .content(content)
                    .date(date)
                    .note(note)
                    .produit(produit)
                    .build();
        } else {
            System.out.println("Produit non trouvé avec l'id : " + productId);
        }
    }
}