package org.example.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String url;
}
