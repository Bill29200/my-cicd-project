package com.tacosmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commandes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @ManyToMany
    @JoinTable(
            name = "commande_produits",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
    )
    private List<Produit> produits;

    @Column(nullable = false)
    private Double prixGlobal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Etat etat; // CREEE, EN_ATTENTE, PRETE, LIVREE

    @Column(nullable = false)
    private LocalDateTime dateCreation;

}