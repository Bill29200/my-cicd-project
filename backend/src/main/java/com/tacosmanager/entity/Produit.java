package com.tacosmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;

    @Column(nullable = false)
    private String nomProduit;

    @Column(nullable = false)
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "id_famille", nullable = false)
    private FamilleProduit familleProduit;

    @Column(nullable = false)
    private Boolean disponible;

    private String photo;

    // Relation Many-to-One avec FastFood
    @ManyToOne
    @JoinColumn(name = "fast_food_id") // Clé étrangère dans la table produits
    private Fastfood fastfood; // Le Fastfood auquel ce produit appartient




}