package org.example.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int note;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;



}
