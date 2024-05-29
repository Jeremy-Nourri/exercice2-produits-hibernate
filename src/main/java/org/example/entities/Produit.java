package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String marque;

    private String reference;

    private double prix;

    private int stock;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Commentaire> commentaireListList = new ArrayList<>();

    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                ", dateAchat=" + dateAchat +
                '}';
    }

}