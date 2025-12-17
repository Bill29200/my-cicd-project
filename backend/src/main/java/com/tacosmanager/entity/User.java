package com.tacosmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false)
    private String nom;

    private String password;

    @Column(nullable = false)
    private String email;

    private String telephone;

    @Column(nullable = false)
    private Role role;

    // Relation 1:1 avec FastFood pour le gérant
    @OneToOne(mappedBy = "gerant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Fastfood fastfoodGere; // Champ pour le FastFood géré par ce gérant

    // Relation 1:1 avec FastFood pour le serveur
    @OneToOne(mappedBy = "serveur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Fastfood fastfoodServeur; // Champ pour le FastFood où ce serveur travaille

    // Relation Many-to-One avec FastFood pour les clients
    @ManyToOne
    @JoinColumn(name = "fast_food_id") // Clé étrangère dans la table users
    private Fastfood fastfood; // Le FastFood auquel ce client est associé

}