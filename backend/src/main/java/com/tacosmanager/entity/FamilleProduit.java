package com.tacosmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "familles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilleProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFamilleProduit;

    @Column(nullable = false)
    private String intitule; // Exemple : Pains, Viandes, Boissons, Sauces

    // Relation Many-to-One avec FastFood
    @ManyToOne
    @JoinColumn(name = "fast_food_id") // Clé étrangère dans la table produits
    private Fastfood fastfood; // Le Fastfood auquel ce produit appartient
}