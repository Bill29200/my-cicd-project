package com.tacosmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fastfoods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fastfood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fastfoodId;

    @Column(nullable = false)
    private String intitule;

    private String logoImage;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String telephone;
//
//    @Column(nullable = false)
//    private String mail;

//    @Column(nullable = false)
//    private String motdepasse;

    // Relation 1:1 avec le gérant (User avec rôle GERANT)
    @OneToOne
    @JoinColumn(name = "gerant_id", unique = true) // Clé étrangère vers User
    @JsonIgnore
    private User gerant;

    // Relation 1:1 avec le serveur (User avec rôle SERVEUR)
    @OneToOne
    @JoinColumn(name = "serveur_id", unique = true) // Clé étrangère vers User
    @JsonIgnore
    private User serveur;

    // Relation One-to-Many avec les clients
//    @OneToMany(mappedBy = "fastfood", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<User> clients; // Liste des clients associés à ce FastFood

    // Relation One-to-Many avec les produits
//    @OneToMany(mappedBy = "fastfood", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Produit> produits = new ArrayList<>(); // Liste des produits associés à ce FastFood

}